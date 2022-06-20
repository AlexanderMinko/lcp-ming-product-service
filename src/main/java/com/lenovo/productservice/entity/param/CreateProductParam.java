package com.lenovo.productservice.entity.param;

import lombok.Data;

@Data
public class CreateProductParam {
  private String name;
  private String description;
  private String price;
  private String category;
  private String producer;
}
