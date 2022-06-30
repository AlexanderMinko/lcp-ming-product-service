package com.lenovo.productservice.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {
  private String name;
  private String displayName;
  private String description;
  private String url;
  private Long size;
}
