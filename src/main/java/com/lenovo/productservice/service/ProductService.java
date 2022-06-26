package com.lenovo.productservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.lenovo.exception.ProductNotFound;
import com.lenovo.model.FileUri;
import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Producer;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.dto.ProductResponseDto;
import com.lenovo.productservice.entity.param.CreateProductParam;
import com.lenovo.productservice.repository.CategoryRepository;
import com.lenovo.productservice.repository.ProducerRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProducerRepository producerRepository;
  private final MinioStoreService minioStoreService;

  public List<Product> getList() {
    return productRepository.findAll();
  }

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

  public List<Category> getCategories() {
    List<Category> categories = categoryRepository.findAll();
    log.debug("{} categories found", categories.size());
    return categories;
  }

  public List<Producer> getProducers() {
    List<Producer> producers = producerRepository.findAll();
    log.debug("{} producers found", producers.size());
    return producers;
  }

  public Product getProductById(String id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFound("product not found with id - " + id));
    log.debug("In getProductById - product: {} found", product);
    return product;
  }

  public List<Product> getProducts(Set<String> ids) {
    return CollectionUtils.isEmpty(ids) ? productRepository.findAll() : productRepository.findProductByIdIn(ids);
  }

  public void createProduct(CreateProductParam createProductParam, MultipartFile multipartRequest) {
    var category = getCategory(createProductParam.getCategory());
    var product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setName(createProductParam.getName());
    product.setDescription(createProductParam.getDescription());
    product.setPrice(Double.parseDouble(createProductParam.getPrice()));
    product.setProducerId(createProductParam.getProducer());
    product.setCategoryId(createProductParam.getCategory());
    product.setImageUrl(uploadPhoto(multipartRequest, category.getName()));
    var savedProduct = productRepository.save(product);
    System.out.println(productRepository.findById(savedProduct.getId()));
  }

  private String uploadPhoto(final MultipartFile photo, String categoryName) {
    try {
      final var uri = FileUri.builder()
          .folderName("images/products/" + categoryName)
          .extension(FilenameUtils.getExtension(photo.getOriginalFilename()))
          .build();
      final var fileInfoDto = minioStoreService.putObjectToStorage(photo.getInputStream(), photo.getSize(), uri);
      log.info("Uploading file successful, downloadUri: {}", fileInfoDto.getDownloadUri());
      return fileInfoDto.getDownloadUri();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ProductResponseDto convertFromProductToDto(Product product) {
    var producer = getProducer(product.getProducerId());
    var category = getCategory(product.getCategoryId());
    return new ProductResponseDto(product, category, producer);
  }

  private Producer getProducer(String producerId) {
    return producerRepository.findById(producerId)
        .orElseThrow(() -> new RuntimeException("Producer not found with id: " + producerId));
  }

  private Category getCategory(String categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new RuntimeException("Category not fount for id: " + categoryId));
  }
}
