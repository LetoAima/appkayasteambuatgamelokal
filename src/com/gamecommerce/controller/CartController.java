package com.gamecommerce.controller;

import com.gamecommerce.service.CartService;
import com.gamecommerce.model.CartItem;
import java.util.List;

public class CartController {

    private CartService cartService;

    public CartController() {
        this.cartService = new CartService();
    }

    public boolean addToCart(int userId, int gameId) {
        return cartService.addToCart(userId, gameId);
    }

    public List<CartItem> getCartItems(int userId) {
        return cartService.getCartByUser(userId);
    }
}