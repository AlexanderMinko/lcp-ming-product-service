package com.lenovo.productservice.service;

import static java.util.Collections.emptySet;

import static com.lenovo.productservice.TestUtils.UUID1;
import static com.lenovo.productservice.TestUtils.UUID2;
import static com.lenovo.productservice.TestUtils.stubCategory;
import static com.lenovo.productservice.TestUtils.stubProducer;
import static com.lenovo.productservice.TestUtils.stubProduct1;
import static com.lenovo.productservice.TestUtils.stubProduct2;
import static com.lenovo.productservice.TestUtils.stubProducts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.lenovo.productservice.repository.CategoryRepository;
import com.lenovo.productservice.repository.ProducerRepository;
import com.lenovo.productservice.repository.ProductRepository;
import com.lenovo.service.MinioStoreService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private ProducerRepository producerRepository;

  @Mock
  private MinioStoreService minioStoreService;


  //-----------------------------------------getProducts(Set<String> ids)-----------------------------------------------

  @Test
  void shouldReturnProductsByIds() {

    //given
    var product1 = stubProduct1();
    var product2 = stubProduct2();
    var expected = List.of(product1, product2);
    given(productRepository.findProductByIdIn(anySet())).willReturn(expected);

    //when
    var actual = productService.getProducts(Set.of(product1.getId(), product2.getId()));

    //then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnAllProductsWithoutIds() {

    //given
    var expected = stubProducts();
    given(productRepository.findAll()).willReturn(expected);

    //when
    var actual = productService.getProducts(emptySet());

    //then
    assertThat(actual).isEqualTo(expected);
  }

  //---------getProducts(String freeText, String categoryId, String producerId, Pageable pageable)----------------------

  @Test
  void shouldReturnProducerById() {

    //given
    var producer = stubProducer();
    var category = stubCategory();
    var pageable = PageRequest.of(0, 20);
    var product = stubProduct2();
    product.setCategoryId(category.getId());
    product.setProducerId(producer.getId());
    var products = List.of(product);
    given(categoryRepository.findById(anyString())).willReturn(Optional.of(category));
    given(producerRepository.findById(anyString())).willReturn(Optional.of(producer));
    given(productRepository.findBySearchParams(anyString(), anyString(), anyString(), any(Pageable.class)))
        .willReturn(new PageImpl<>(products, pageable, products.size()));

    //when
    var actual = productService.getProducts("Java", category.getId(), producer.getId(),pageable);

    //then
    assertThat(actual.getTotalElements()).isEqualTo(1);
    var productDto = actual.getContent().get(0);
    assertThat(productDto.getId()).isEqualTo(product.getId());
    assertThat(productDto.getCategory().getId()).isEqualTo(category.getId());
    assertThat(productDto.getProducer().getId()).isEqualTo(producer.getId());
  }

  private static Stream<Arguments> getProductSearchIds() {
    var productsSearched = List.of(stubProduct1(), stubProduct2());
    var productsAll = stubProducts();
    var ids = Set.of(UUID1, UUID2);
    return Stream.of(
        Arguments.of(productsSearched, ids),
        Arguments.of(productsAll, emptySet())
    );
  }
}
