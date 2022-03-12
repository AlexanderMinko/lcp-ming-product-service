package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByProductId(String id);

    List<Review> findByAccountId(String id);

    List<Review> findByParentId(String id);

}
