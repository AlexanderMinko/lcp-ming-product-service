package com.lenovo.productservice.repository;

import java.util.Optional;

import com.lenovo.productservice.entity.Category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

  Optional<Category> findByName(String name);

  boolean existsByName(String name);
}
