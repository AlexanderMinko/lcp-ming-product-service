package com.lenovo.productservice.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.lenovo.productservice.entity.Review;
import com.lenovo.productservice.entity.dto.ReviewResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

//    @Mapping(target = "duration", expression = "java(getDuration(review))")
//    @Mapping(target = "reviewerFirstName", expression = "java(review.getAccount().getFirstName())")
//    @Mapping(target = "reviewerLastName", expression = "java(review.getAccount().getLastName())")
//    @Mapping(target = "reviewerPhotoUrl", expression = "java(review.getAccount().getPhotoUrl())")
//    ReviewResponseDto mapFromReviewToReviewResponseDto(Review review);

    default String getDuration(Review review) {
        return TimeAgo.using(review.getCreatedDate().toEpochMilli());
    }
}
