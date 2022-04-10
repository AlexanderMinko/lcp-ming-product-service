package com.lenovo.productservice.controller;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.dto.ProductResponseDto;
import com.lenovo.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return ResponseEntity.ok(productService.getProducts(ids));
    }

    @GetMapping
    @PageableAsQueryParam
    public ResponseEntity<Page<ProductResponseDto>> getProducts(
            @Parameter(hidden = true) @PageableDefault(size = 20, sort = {"name"}) Pageable pageable,
            @RequestParam(name = "category_id", required = false) String categoryId,
            @RequestParam(name = "producer_id", required = false) String producerId,
            @RequestParam(name = "free_text", required = false) String freeText) {
        return new ResponseEntity<>(productService.getProducts(freeText, categoryId, producerId, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(productService.getCategories(), HttpStatus.OK);
    }

}
