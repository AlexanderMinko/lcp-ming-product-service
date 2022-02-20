package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
