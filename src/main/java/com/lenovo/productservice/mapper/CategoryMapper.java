package com.lenovo.productservice.mapper;

import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.model.CategoryDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryDto toCategoryDto(Category category);
}
