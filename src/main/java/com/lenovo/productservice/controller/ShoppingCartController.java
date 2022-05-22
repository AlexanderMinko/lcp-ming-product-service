package com.lenovo.productservice.controller;

import java.util.List;

import com.lenovo.productservice.entity.model.CartItem;
import com.lenovo.productservice.service.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

  private final CartService cartService;

  @PostMapping
  public ResponseEntity<List<CartItem>> getShoppingCart(@RequestBody String serializedShoppingCart) {
    return new ResponseEntity<>(cartService.getCartItems(serializedShoppingCart), HttpStatus.OK);
  }
}
