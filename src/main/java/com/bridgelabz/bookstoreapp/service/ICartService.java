package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDto;
import com.bridgelabz.bookstoreapp.entity.Cart;

import java.util.List;

public interface ICartService {

    Cart addToCart(long userId, CartDto cartDto);
    String deleteById(int cartid);

    String changeCartQty(long userId, int cartId, CartDto quantity);

    List<Cart> findAll();

    List<Cart> getCartDetailsByUserId(long userId);


}
