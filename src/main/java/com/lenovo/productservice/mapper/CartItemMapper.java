package com.lenovo.productservice.mapper;

import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "quantity", constant = "1")
    CartItem mapToCartItemFromProduct(Product product);

}
