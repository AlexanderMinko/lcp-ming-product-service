package com.lenovo.productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.lenovo.productservice.config.AppConfig;
import com.lenovo.productservice.entity.Review;
import com.lenovo.productservice.entity.dto.ChildReviewResponseDto;
import com.lenovo.productservice.entity.dto.ReviewRequestDto;
import com.lenovo.productservice.entity.dto.ReviewResponseDto;
import com.lenovo.productservice.entity.model.Account;
import com.lenovo.productservice.repository.ReviewRepository;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final WebClient webClient;
  private final AppConfig config;

  public Review getReviewById(String id) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Review with not found with id - " + id));
    log.debug("getReviewById - review: {} found", review);
    return review;
  }

  public void createReview(ReviewRequestDto reviewRequestDto) {
    var review = new Review(reviewRequestDto);
    var savedReview = reviewRepository.save(review);
    log.debug("createReview - review with id: {} successfully created", savedReview.getId());
  }

  public Set<ReviewResponseDto> getAllReviewByProductId(String productId) {
    var reviews = reviewRepository.findByProductId(productId);
    var accountIds = reviews.stream().map(Review::getAccountId).collect(Collectors.toSet());
    var accounts = retrieveAccounts(accountIds);
    var reviewResponseDtoList = convertToReviewResponseDtoList(reviews, accounts);
    log.debug("getAllReviewByProductId - found {} reviews by product: {}", reviewResponseDtoList.size(), productId);
    return reviewResponseDtoList;
  }

  public Set<ReviewResponseDto> convertToReviewResponseDtoList(List<Review> reviews, List<Account> accounts) {
    var reviewResponseDtoList = new TreeSet<ReviewResponseDto>();
    populateReviews(reviews, accounts, reviewResponseDtoList);
    populateChildReviews(reviews, accounts, reviewResponseDtoList);
    return reviewResponseDtoList;
  }

  private void populateChildReviews(
      List<Review> reviews,
      List<Account> accounts, Set<ReviewResponseDto> reviewResponseDtoList) {
    reviews.stream()
        .filter(review -> Objects.nonNull(review.getParentId()))
        .forEach(review -> {
          var account = findAccount(accounts, review);
          var childReviewResponseDto = convertToChildReviewResponseDto(review, account);
          var currentReviewResponseDto = reviewResponseDtoList
              .stream()
              .filter(el -> el.getId().equals(review.getParentId()))
              .findFirst()
              .orElseThrow(RuntimeException::new);
          var listOfChildren = currentReviewResponseDto.getChildrenReviews();
          listOfChildren.add(childReviewResponseDto);
          currentReviewResponseDto.setChildrenReviews(listOfChildren);
          reviewResponseDtoList.add(currentReviewResponseDto);
        });
  }

  private void populateReviews(
      List<Review> reviews,
      List<Account> accounts,
      Set<ReviewResponseDto> reviewResponseDtoList) {
    reviews.stream()
        .filter(review -> Objects.isNull(review.getParentId()))
        .forEach(review -> {
          var account = findAccount(accounts, review);
          var reviewResponseDto = convertToReviewResponseDto(review, account);
          reviewResponseDtoList.add(reviewResponseDto);
        });
  }

  private Account findAccount(List<Account> accounts, Review review) {
    return accounts.stream().filter(acc -> acc.getId().equals(review.getAccountId())).findFirst()
        .orElseThrow(() -> new RuntimeException("account not found for id: " + review.getAccountId()));
  }

  public ReviewResponseDto convertToReviewResponseDto(Review review, Account account) {
    return ReviewResponseDto.builder()
        .id(review.getId())
        .content(review.getContent())
        .duration(getDuration(review))
        .dateCreated(review.getCreatedDate())
        .reviewerFirstName(account.getFirstName())
        .reviewerLastName(account.getLastName())
        .reviewerPhotoUrl(account.getPhotoUrl())
        .childrenReviews(new ArrayList<>())
        .build();
  }

  public ChildReviewResponseDto convertToChildReviewResponseDto(Review review, Account account) {
    return ChildReviewResponseDto.builder()
        .id(review.getId())
        .content(review.getContent())
        .duration(getDuration(review))
        .dateCreated(review.getCreatedDate())
        .reviewerFirstName(account.getFirstName())
        .reviewerLastName(account.getLastName())
        .reviewerPhotoUrl(account.getPhotoUrl())
        .build();
  }

  private String getDuration(Review review) {
    return TimeAgo.using(review.getCreatedDate().toEpochMilli());
  }

  private List<Account> retrieveAccounts(Set<String> accountIds) {
    var accounts = webClient
        .post()
        .uri(config.getAccountServiceUrl() + "/accounts")
        .bodyValue(accountIds)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Account>>() {})
        .block();
    log.debug("retrieveAccounts - found: {} accounts", accounts.size());
    return accounts;
  }
}
