package com.lenovo.productservice;

import java.util.List;
import java.util.UUID;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Producer;
import com.lenovo.productservice.entity.Product;

public class TestUtils {

  public static final String UUID1 = UUID.randomUUID().toString();
  public static final String UUID2 = UUID.randomUUID().toString();
  public static final String UUID3 = UUID.randomUUID().toString();

  public static final String CATEGORY_UUID = UUID.randomUUID().toString();
  public static final String PRODUCER_UUID = UUID.randomUUID().toString();

  public static List<Product> stubProducts() {
    return List.of(stubProduct1(), stubProduct2(), stubProduct3());
  }

  public static Product stubProduct1() {
    return Product.builder()
        .id(UUID1)
        .name("Crash Course in Python")
        .description("Learn Python at your own pace")
        .imageUrl("/images/products/books/book-1000.png")
        .price(14.99)
        .build();
  }

  public static Product stubProduct2() {
    return Product.builder()
        .id(UUID2)
        .name("Become a Guru in JavaScript")
        .description("Learn JavaScript at your own pace.")
        .imageUrl("/images/products/books/book-1001.png")
        .price(20.99)
        .build();
  }

  public static Product stubProduct3() {
    return Product.builder()
        .id(UUID3)
        .name("Exploring Vue.js")
        .description("Learn Vue.js at your own pace")
        .imageUrl("/images/products/books/book-1002.png")
        .price(13.99)
        .build();
  }

  public static Category stubCategory() {
    return Category.builder()
        .id(CATEGORY_UUID)
        .name("books")
        .displayName("Books")
        .count(3)
        .build();
  }

  public static Producer stubProducer() {
    return Producer.builder()
        .id(PRODUCER_UUID)
        .name("socket")
        .displayName("Socket")
        .build();
  }

}
