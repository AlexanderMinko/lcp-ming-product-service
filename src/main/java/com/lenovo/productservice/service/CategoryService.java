package com.lenovo.productservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.model.CategoryDto;
import com.lenovo.productservice.entity.param.CreateCategoryParam;
import com.lenovo.productservice.mapper.CategoryMapper;
import com.lenovo.productservice.repository.CategoryRepository;
import com.lenovo.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryDto> getCategories() {
    var categories = categoryRepository.findAll();
    var categoryDtoList = categories.stream()
        .map(categoryMapper::toCategoryDto)
        .peek(dto -> dto.setCount(productRepository.countAllByCategoryId(dto.getId())))
        .collect(Collectors.toList());
    log.debug("{} categories found", categories.size());
    return categoryDtoList;
  }

  public Category getCategory(String categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new RuntimeException("Category not fount for id: " + categoryId));
  }

  public Category getCategoryByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException(String.format("Category not found by name: %s", name)));
  }

  public Category createCategory(CreateCategoryParam param) {
    validate(param.getName());
    var category = new Category();
    category.setId(UUID.randomUUID().toString());
    category.setName(param.getName());
    category.setDisplayName(param.getDisplayName());
    category.setDescription(param.getDescription());
    var saved = categoryRepository.save(category);
    log.debug("Category successfully saved with ID: {}", saved.getId());
    return saved;
  }

  private void validate(String name) {
    var isExists = categoryRepository.existsByName(name);
    if(isExists) {
      throw new RuntimeException(String.format("Category with name %s already exists", name));
    }
  }
}
