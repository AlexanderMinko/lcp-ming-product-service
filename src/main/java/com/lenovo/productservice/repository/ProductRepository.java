package com.lenovo.productservice.repository;

import java.util.Collection;
import java.util.List;

import com.lenovo.productservice.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom {

  Page<Product> findByCategoryId(String id, Pageable pageable);

  List<Product> findProductByIdIn(Collection<String> id);

  Integer countAllByCategoryId(String categoryId);
}
