package com.lenovo.productservice.entity.model;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private String id;
  private Instant createdDate;
  private String email;
  private List<OrderItem> orderItems;
}
