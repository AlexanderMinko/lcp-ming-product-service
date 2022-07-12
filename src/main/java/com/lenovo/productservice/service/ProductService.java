package com.lenovo.productservice.service;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.lenovo.exception.ProductNotFound;
import com.lenovo.model.FileInfoDto;
import com.lenovo.model.FileUri;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.dto.ProductResponseDto;
import com.lenovo.productservice.entity.model.Video;
import com.lenovo.productservice.entity.param.CreateProductParam;
import com.lenovo.productservice.repository.ProductRepository;
import com.lenovo.service.MinioStoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private final ProducerService producerService;
  private final MinioStoreService minioStoreService;

  public Page<ProductResponseDto> getProducts(
      String freeText,
      String categoryId,
      String producerId,
      Pageable pageable) {
    var products = findBySearchParams(freeText, categoryId, producerId, pageable);
    var converted = products.stream().map(this::convertFromProductToDto).collect(Collectors.toList());
    log.debug("{} products found, page: {}, size: {}", products.getTotalElements(), pageable.getPageNumber(),
        pageable.getPageSize());
    return new PageImpl<>(converted, pageable, products.getTotalElements());
  }

  private Page<Product> findBySearchParams(String freeText, String categoryId, String producerId, Pageable pageable) {
    return StringUtils.isAllEmpty(freeText, categoryId, producerId)
        ? productRepository.findAll(pageable)
        : productRepository.findBySearchParams(freeText, categoryId, producerId, pageable);
  }

  public Integer countAllProductsByCategoryId(String categoryId) {
    return productRepository.countAllByCategoryId(categoryId);
  }

  public Product getProductById(String id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFound("product not found with id - " + id));
    log.debug("Founded product with id: {}", id);
    return product;
  }

  public List<Product> getProducts(Set<String> ids) {
    return CollectionUtils.isEmpty(ids) ? productRepository.findAll() : productRepository.findProductByIdIn(ids);
  }

  public Product createProduct(CreateProductParam createProductParam, MultipartFile multipartRequest) {
    var category = categoryService.getCategory(createProductParam.getCategory());
    var product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setCreatedDate(Instant.now());
    product.setName(createProductParam.getName());
    product.setDescription(createProductParam.getDescription());
    product.setPrice(Double.parseDouble(createProductParam.getPrice()));
    product.setProducerId(createProductParam.getProducer());
    product.setCategoryId(createProductParam.getCategory());
    product.setImageUrl(uploadPhoto(multipartRequest, category.getName()));
    var savedProduct = productRepository.save(product);
    log.debug("Product successfully saved with ID: {}", savedProduct.getId());
    return savedProduct;
  }

  private String uploadPhoto(final MultipartFile photo, String categoryName) {
    try {
      final var uri = FileUri.builder()
          .folderName("images/products/" + categoryName)
          .extension(FilenameUtils.getExtension(photo.getOriginalFilename()))
          .build();
      final var fileInfoDto = minioStoreService.putObjectToPublicStorage(photo.getInputStream(), photo.getSize(), uri);
      log.info("Uploading file successful, downloadUri: {}", fileInfoDto.getDownloadUri());
      return fileInfoDto.getDownloadUri();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ProductResponseDto convertFromProductToDto(Product product) {
    var producer = producerService.getProducer(product.getProducerId());
    var category = categoryService.getCategory(product.getCategoryId());
    return new ProductResponseDto(product, category, producer);
  }

  public void uploadVideos(String id, MultipartFile[] files) {
    var futures = Arrays.stream(files)
        .map(file -> CompletableFuture.runAsync(() -> uploadVideo(id, file)))
        .collect(Collectors.toList());

    futures.forEach(CompletableFuture::join);
  }

  public void uploadVideo(String id, MultipartFile file) {
    var exists = productRepository.existsById(id);
    if (!exists) {
      throw new RuntimeException("file not exists with id: " + id);
    }
    log.info("Uploading file: {}", file.getOriginalFilename());
    try {
      var uri = FileUri.builder()
          .folderName("videos/products/" + id)
          .extension(FilenameUtils.getExtension(file.getOriginalFilename()))
          .build();
      var fileInfoDto = minioStoreService.putObjectToPublicStorage(file.getInputStream(), file.getSize(), uri);
      log.info("Uploaded file: {} successful, downloadUri: {}", file.getOriginalFilename(),
          fileInfoDto.getDownloadUri());
      assignFileToProduct(id, file, fileInfoDto);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private synchronized void assignFileToProduct(String id, MultipartFile file, FileInfoDto fileInfoDto) {
    var product = getProductById(id);
    var video = Video.builder()
        .description("Video of product: " + product.getName())
        .name(file.getOriginalFilename())
        .displayName(file.getOriginalFilename())
        .size(fileInfoDto.getFileSize())
        .url(fileInfoDto.getDownloadUri())
        .build();
    product.addVideo(video);
    productRepository.save(product);
    log.debug("Updated product with id: {}", id);
  }

  public void deleteVideo(String productId, String videoName) {
    var product = getProductById(productId);
    if (CollectionUtils.isEmpty(product.getVideos())) {
      return;
    }
    var videoToRemove = product.getVideos()
        .stream()
        .filter(video -> videoName.equals(video.getName()))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Video does not exist with name: " + videoName));
    product.getVideos().remove(videoToRemove);
    productRepository.save(product);
    log.debug("Updated product with id: {}, removed video with name: {}", productId, videoName);
  }
}
