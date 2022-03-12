package com.lenovo.productservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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
