package com.lenovo.productservice.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lenovo.exception.ProductNotFound;
import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.dto.ProductResponseDto;
import com.lenovo.productservice.repository.CategoryRepository;
import com.lenovo.productservice.repository.ProducerRepository;
import com.lenovo.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProducerRepository producerRepository;

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

  public Product getProductById(String id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFound("product not found with id - " + id));
    log.debug("In getProductById - product: {} found", product);
    return product;
  }

  public List<Product> getProducts(Set<String> ids) {
    return CollectionUtils.isEmpty(ids) ? productRepository.findAll() : productRepository.findProductByIdIn(ids);
  }

  private ProductResponseDto convertFromProductToDto(Product product) {
    var producerId = product.getProducerId();
    var categoryId = product.getCategoryId();
    var producer = producerRepository.findById(producerId)
        .orElseThrow(() -> new RuntimeException("Producer not fount with id: " + producerId));
    var category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new RuntimeException("Category not fount for id: " + categoryId));
    return new ProductResponseDto(product, category, producer);
  }
}
