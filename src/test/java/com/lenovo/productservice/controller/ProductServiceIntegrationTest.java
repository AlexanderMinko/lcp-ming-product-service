package com.lenovo.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.repository.ProductRepository;
import com.lenovo.test.InjectJwt;
import com.lenovo.test.IntegrationTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@IntegrationTest
@InjectJwt
public class ProductServiceIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ProductRepository productRepository;

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String UUID1 = UUID.randomUUID().toString();
  private static final String UUID2 = UUID.randomUUID().toString();
  private static final String UUID3 = UUID.randomUUID().toString();

  @AfterEach
  public void tearDown() {
    productRepository.deleteAll();
  }

  @Test
  void shouldGetListProducts() throws JsonProcessingException {
    productRepository.saveAll(stubProducts());
    webTestClient
        .get()
        .uri("/products/list")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(MAPPER.writeValueAsString(stubProducts()));
  }

  @Test
  void shouldReturnListProductsByProductsIds() throws JsonProcessingException {
    productRepository.saveAll(stubProducts());
    var expected = stubProducts()
        .stream()
        .filter(product -> !UUID3.equals(product.getId()))
        .collect(Collectors.toList());
    webTestClient
        .post()
        .uri("/products/by-ids")
        .bodyValue(List.of(UUID1, UUID2))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(MAPPER.writeValueAsString(expected));
  }

  private List<Product> stubProducts() {
    var product1 = Product.builder()
        .id(UUID1)
        .name("Crash Course in Python")
        .description("Learn Python at your own pace")
        .imageUrl("/images/products/books/book-1000.png")
        .price(14.99)
        .build();
    var product2 = Product.builder()
        .id(UUID2)
        .name("Become a Guru in JavaScript")
        .description("Learn JavaScript at your own pace.")
        .imageUrl("/images/products/books/book-1001.png")
        .price(20.99).build();
    var product3 = Product.builder()
        .id(UUID3)
        .name("Exploring Vue.js")
        .description("Learn Vue.js at your own pace")
        .imageUrl("/images/products/books/book-1002.png")
        .price(13.99)
        .build();
    return List.of(product1, product2, product3);
  }
}
