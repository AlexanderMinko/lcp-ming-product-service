package com.lenovo.productservice.entity.dto;

import com.lenovo.productservice.entity.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

    private String email;
    private List<OrderItem> orderItems;

}
