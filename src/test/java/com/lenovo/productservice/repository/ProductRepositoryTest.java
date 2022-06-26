package com.lenovo.productservice.repository;

import static com.lenovo.productservice.TestUtils.stubProduct1;
import static com.lenovo.productservice.TestUtils.stubProduct2;
import static com.lenovo.productservice.TestUtils.stubProduct3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void shouldReturnProductsByIds() {
    //given
    var product1 = stubProduct1();
    var product2 = stubProduct2();
    var product3 = stubProduct3();
    var expected = List.of(product1, product2);
    var productsIds = List.of(product1.getId(), product2.getId());
    productRepository.saveAll(List.of(product1, product2, product3));

    //when
    var actual = productRepository.findProductByIdIn(productsIds);

    //then
    assertThat(actual.size()).isEqualTo(2);
    assertThat(actual).containsAll(expected);
  }
}
