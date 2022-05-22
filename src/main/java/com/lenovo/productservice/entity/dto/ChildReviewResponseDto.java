package com.lenovo.productservice.entity.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildReviewResponseDto {

  private String id;
  private String content;
  private String duration;
  private Instant dateCreated;
  private String reviewerFirstName;
  private String reviewerLastName;
  private String reviewerPhotoUrl;
}
