package com.lenovo.productservice.entity.dto;

import java.util.List;

import com.lenovo.productservice.entity.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

  private String email;
  private List<OrderItem> orderItems;
}
