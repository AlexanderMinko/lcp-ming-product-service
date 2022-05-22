package com.lenovo.productservice.controller;

import java.util.Collection;

import com.lenovo.productservice.entity.dto.ReviewRequestDto;
import com.lenovo.productservice.entity.dto.ReviewResponseDto;
import com.lenovo.productservice.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("reviews")
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<String> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
    reviewService.createReview(reviewRequestDto);
    return new ResponseEntity<>("Review successfully created!", HttpStatus.CREATED);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<Collection<ReviewResponseDto>> getAllReviewsByProductId(@PathVariable String id) {
    return new ResponseEntity<>(reviewService.getAllReviewByProductId(id), HttpStatus.OK);
  }
}
