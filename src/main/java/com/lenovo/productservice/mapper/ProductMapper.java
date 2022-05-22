package com.lenovo.productservice.mapper;

import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.model.OrderItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "productId", source = "id")
  OrderItem fromProductToOrderItem(Product product);
}
