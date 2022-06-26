package com.lenovo.productservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lenovo.productservice.repository.CategoryRepository;
import com.lenovo.productservice.repository.ProducerRepository;
import com.lenovo.productservice.repository.ProductRepository;
import com.lenovo.test.InjectJwt;
import com.lenovo.test.IntegrationTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lenovo.productservice.TestUtils.CATEGORY_UUID;
import static com.lenovo.productservice.TestUtils.PRODUCER_UUID;
import static com.lenovo.productservice.TestUtils.UUID1;
import static com.lenovo.productservice.TestUtils.UUID2;
import static com.lenovo.productservice.TestUtils.UUID3;
import static com.lenovo.productservice.TestUtils.stubProduct1;
import static com.lenovo.productservice.TestUtils.stubProduct2;
import static com.lenovo.productservice.TestUtils.stubProduct3;

import static com.lenovo.productservice.TestUtils.stubCategory;
import static com.lenovo.productservice.TestUtils.stubProducer;

import static com.lenovo.productservice.TestUtils.stubProducts;

@IntegrationTest
@InjectJwt
public class ProductServiceIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProducerRepository producerRepository;

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String PRODUCT_SERVICE_URL = "/products";

  @AfterEach
  public void tearDown() {
    productRepository.deleteAll();
  }

  //-----------------------------------------GET /list------------------------------------------------------------------

  @Test
  void shouldReturnListProducts() throws JsonProcessingException {
    productRepository.saveAll(stubProducts());
    webTestClient
        .get()
        .uri(PRODUCT_SERVICE_URL + "/list")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(MAPPER.writeValueAsString(stubProducts()));
  }

  //-----------------------------------------GET /by-ids----------------------------------------------------------------

  @Test
  void shouldReturnListProductsByProductsIds() throws JsonProcessingException {
    productRepository.saveAll(stubProducts());
    var expected = stubProducts()
        .stream()
        .filter(product -> !UUID3.equals(product.getId()))
        .collect(Collectors.toList());
    webTestClient
        .post()
        .uri(PRODUCT_SERVICE_URL + "/by-ids")
        .bodyValue(List.of(UUID1, UUID2))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(MAPPER.writeValueAsString(expected));
  }

  //-----------------------------------------GET /----------------------------------------------------------------------

  @ParameterizedTest
  @MethodSource("getProductSearchParamsTestCases")
  void shouldReturnListProductsByRequestParams(String uri, String id, String name) {
    var booksCategory = stubCategory();
    var socketProducer = stubProducer();
    var product1 = stubProduct1();
    var product2 = stubProduct2();
    var product3 = stubProduct3();
    var products = List.of(product1, product2, product3);
    products.forEach(product -> {
      product.setCategoryId(booksCategory.getId());
      product.setProducerId(socketProducer.getId());
    });

    categoryRepository.save(booksCategory);
    producerRepository.save(socketProducer);
    productRepository.saveAll(products);

    webTestClient
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.content[0].id").isEqualTo(id)
        .jsonPath("$.content[0].name").isEqualTo(name)
        .jsonPath("$.content[0].category.id").isEqualTo(booksCategory.getId())
        .jsonPath("$.content[0].producer.id").isEqualTo(socketProducer.getId())
        .jsonPath("$.content[1].id").doesNotExist();
  }

  private static String getProductUri(String categoryId, String producerId, String freeText) {
    var uri = UriComponentsBuilder.fromUriString(PRODUCT_SERVICE_URL);
    uri.queryParamIfPresent("category_id", Optional.ofNullable(categoryId));
    uri.queryParamIfPresent("producer_id", Optional.ofNullable(producerId));
    uri.queryParamIfPresent("free_text", Optional.ofNullable(freeText));
    uri.queryParam("page", 0);
    uri.queryParam("size", 20);
    return uri.build().toUriString();
  }

  private static Stream<Arguments> getProductSearchParamsTestCases() {
    return Stream.of(
        Arguments.of(getProductUri(CATEGORY_UUID, PRODUCER_UUID, "Python"), UUID1, "Crash Course in Python"),
        Arguments.of(getProductUri(CATEGORY_UUID, null, "Java"), UUID2, "Become a Guru in JavaScript"),
        Arguments.of(getProductUri(null, PRODUCER_UUID, "Vue"), UUID3, "Exploring Vue.js"),
        Arguments.of(getProductUri(null, null, "Vue"), UUID3, "Exploring Vue.js")
    );
  }
}
