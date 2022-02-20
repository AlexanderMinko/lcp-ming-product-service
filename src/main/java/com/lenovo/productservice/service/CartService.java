package com.lenovo.productservice.service;

import com.lenovo.productservice.entity.Product;
import com.lenovo.productservice.entity.model.CartItem;
import com.lenovo.productservice.mapper.CartItemMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CartService {

    private final ProductService productService;
    private final CartItemMapper cartItemMapper;

    public List<CartItem> getCartItems(String serializedShoppingCart) {
        if(serializedShoppingCart.trim().length() > 0) {
            List<CartItem> cartItems = new ArrayList<>();
            String[] cookieValues = serializedShoppingCart.split("\\|");
            for(String value : cookieValues) {
                String[] data = value.split("@");
                Product current = productService.getProductById(data[0]);
                CartItem cartItem = cartItemMapper.mapToCartItemFromProduct(current);
                cartItem.setQuantity(Integer.parseInt(data[1]));
                cartItems.add(cartItem);
            }
            log.info("In getCartItems - {} cartItems deserialized", cartItems.size());
            return cartItems;
        } else {
            log.info("In getCartItems - serialized shopping cart is empty!");
            return Collections.emptyList();
        }
    }
}
