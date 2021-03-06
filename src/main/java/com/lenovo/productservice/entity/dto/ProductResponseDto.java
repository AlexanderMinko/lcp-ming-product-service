package com.lenovo.productservice.entity.dto;

import java.time.Instant;
import java.util.List;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Producer;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.model.Video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

  private String id;
  private String name;
  private String description;
  private String imageUrl;
  private Double price;
  private Instant createdDate;
  private List<Video> videos;
  private Category category;
  private Producer producer;

  public ProductResponseDto(Product product, Category category, Producer producer) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.imageUrl = product.getImageUrl();
    this.price = product.getPrice();
    this.videos = product.getVideos();
    this.category = category;
    this.producer = producer;
  }
}
