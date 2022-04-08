package com.lenovo.productservice.controller;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.dto.ProductResponseDto;
import com.lenovo.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() {
        return new ResponseEntity<>(productService.getList(), HttpStatus.OK);
    }

    @PostMapping("/by-ids")
    public ResponseEntity<List<Product>> getProductsByIds(@RequestBody Set<String> ids) {
        return ResponseEntity.ok(productService.getProductsByIds(ids));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(@RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(productService.getProducts(page, size), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(productService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(@RequestParam String id, @RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(productService.getProductsByCategoryId(id, page, size), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> getProductsByNameContaining(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(productService.getProductsByNameContaining(name, page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/by-price-asc")
    public ResponseEntity<Page<Product>> getProductsSorterByPriceAsc(@RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(productService.getProductsSortedByPriceAsc(page, size), HttpStatus.OK);
    }

    @GetMapping("/by-price-desc")
    public ResponseEntity<Page<Product>> getProductsSorterByPriceDesc(@RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(productService.getProductsSortedByPriceDesc(page, size), HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<Page<Product>> getProductsByNameSorted(@RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(productService.getProductsByNameSorted(page, size), HttpStatus.OK);
    }

}
