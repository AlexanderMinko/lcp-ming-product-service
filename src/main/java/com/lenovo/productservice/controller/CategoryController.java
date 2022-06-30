package com.lenovo.productservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.model.CategoryDto;
import com.lenovo.productservice.entity.param.CreateCategoryParam;
import com.lenovo.productservice.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryDto> getCategories() {
    return categoryService.getCategories();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Category createCategory(@RequestBody @Valid CreateCategoryParam param) {
    return categoryService.createCategory(param);
  }
}
