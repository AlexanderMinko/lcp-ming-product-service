package com.lenovo.productservice.service;

import com.lenovo.exception.ProductNotFound;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.repository.CategoryRepository;
import com.lenovo.productservice.repository.ProductRepository;
import com.lenovo.productservice.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Product> getList() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Product> getProducts(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageMinus(page), size);
        Page<Product> products = productRepository.findAll(pageRequest);
        log.debug("{} products found, page: {}, size: {}", products.getTotalElements(), page, size);
        return products;
    }

    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        log.debug("{} categories found", categories.size());
        return categories;
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsByCategoryId(String id, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageMinus(page), size);
        Page<Product> products = productRepository.findByCategoryId(id, pageRequest);
        log.debug("{} products found, by category id: {}, page: {}, size: {} ", products.getTotalElements(), id, page, size);
        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsByNameContaining(String name, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageMinus(page), size);
        Page<Product> products = productRepository.findByNameContains(name, pageRequest);
        log.debug("In getProductsByNameContaining - {} products found, with name containing: {}", products.getTotalElements(), name);
        return products;
    }

    @Transactional(readOnly = true)
    public Product getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFound("product not found with id - " + id));
        log.debug("In getProductById - product: {} found", product);
        return product;

    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsSortedByPriceAsc(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest
                .of(pageMinus(page), size, Sort.by(Sort.Direction.ASC, "price"));
        Page<Product> products = productRepository.findAll(pageRequest);
        log.debug("{} products found, page: {}, size: {}", products.getTotalElements(), page, size);
        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsSortedByPriceDesc(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest
                .of(pageMinus(page), size, Sort.by(Sort.Direction.DESC, "price"));
        Page<Product> products = productRepository.findAll(pageRequest);
        log.debug("{} products found, page: {}, size: {}", products.getTotalElements(), page, size);
        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsByNameSorted(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageMinus(page), size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Product> products = productRepository.findAll(pageRequest);
        log.debug("{} products found, page: {}, size: {}", products.getTotalElements(), page, size);
        return products;
    }

    public List<Product> getProductsByIds(Set<String> ids) {
        return productRepository.findProductByIdIn(ids);
    }

    //Spring data numbered pages from 0, and this method make page numbering from one
    private Integer pageMinus(Integer page) {
        return page > 0 ? --page : 0;
    }

}
