package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> findBySearchParams(String freeText, String categoryId, String producerId, Pageable pageable);

}
