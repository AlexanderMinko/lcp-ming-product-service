package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Page<Product> findByCategoryId(String id, Pageable pageable);

    Page<Product> findByNameContains(String name, Pageable pageable);

    List<Product> findProductByIdIn(Collection<String> id);
}
