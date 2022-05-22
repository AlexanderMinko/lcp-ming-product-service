package com.lenovo.productservice.repository;

import java.util.List;

import com.lenovo.productservice.entity.Review;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

  List<Review> findByProductId(String id);

  List<Review> findByAccountId(String id);

  List<Review> findByParentId(String id);
}
