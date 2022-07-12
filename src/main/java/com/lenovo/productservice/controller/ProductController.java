package com.lenovo.productservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.dto.ProductResponseDto;
import com.lenovo.productservice.entity.param.CreateProductParam;
import com.lenovo.productservice.service.ProductService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.simpleframework.xml.Path;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  @PageableAsQueryParam
  @ResponseStatus(HttpStatus.OK)
  public Page<ProductResponseDto> getProducts(
      @Parameter(hidden = true) @PageableDefault(size = 20, sort = {"name"}) Pageable pageable,
      @RequestParam(name = "category_id", required = false) String categoryId,
      @RequestParam(name = "producer_id", required = false) String producerId,
      @RequestParam(name = "free_text", required = false) String freeText) {
    return productService.getProducts(freeText, categoryId, producerId, pageable);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductById(@PathVariable String id) {
    return productService.getProductById(id);
  }

  @PostMapping("/by-ids")
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProductsByIds(@RequestBody(required = false) Set<String> ids) {
    return productService.getProducts(ids);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(
      @RequestPart("createParamJson") CreateProductParam createProductParam,
      @RequestPart("imageFile") MultipartFile multipartRequest) {
    return productService.createProduct(createProductParam, multipartRequest);
  }

  @PostMapping("/{id}/videos")
  @ResponseStatus(HttpStatus.OK)
  public void uploadVideos(@PathVariable String id, @RequestPart("videoFile") MultipartFile[] multipartFiles) {
    Arrays.stream(multipartFiles).forEach(file -> System.out.println(file.getOriginalFilename()));
    productService.uploadVideos(id, multipartFiles);
  }

  @PostMapping("/{id}/video")
  @ResponseStatus(HttpStatus.OK)
  public void uploadVideo(@PathVariable String id, @RequestPart("videoFile") MultipartFile multipartFiles) {
    productService.uploadVideo(id, multipartFiles);
  }

  @DeleteMapping("/{id}/video/{video-name}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteVideo(@PathVariable("id") String productId, @PathVariable("video-name") String videoName) {
    productService.deleteVideo(productId, videoName);
  }

}
