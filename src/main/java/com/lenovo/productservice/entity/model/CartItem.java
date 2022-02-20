package com.lenovo.productservice.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private Double price;
    private Integer quantity;

}
