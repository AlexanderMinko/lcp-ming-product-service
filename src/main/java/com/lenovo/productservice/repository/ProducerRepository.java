package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Producer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProducerRepository extends MongoRepository<Producer, String> {

  boolean existsByName(String name);

}
