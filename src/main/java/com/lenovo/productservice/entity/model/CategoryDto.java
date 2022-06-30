package com.lenovo.productservice.entity.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto implements Serializable {
  private String id;
  private String name;
  private String displayName;
  private String description;
  private Integer count;
}
