package com.lenovo.productservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class ReviewResponseDto implements Comparable<ReviewResponseDto>{
    private String id;
    private String content;
    private String duration;
    private Instant dateCreated;
    private String reviewerFirstName;
    private String reviewerLastName;
    private String reviewerPhotoUrl;
    private List<ChildReviewResponseDto> childrenReviews;

    @Override
    public int compareTo(ReviewResponseDto o) {
        return o.dateCreated.compareTo(dateCreated);
    }
}
