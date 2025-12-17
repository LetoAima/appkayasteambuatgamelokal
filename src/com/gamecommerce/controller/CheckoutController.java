package com.gamecommerce.controller;

import com.gamecommerce.model.CartItem;
import com.gamecommerce.service.CartService;
import com.gamecommerce.service.OrderService;
import com.gamecommerce.service.WalletService;

import java.util.List;

public class CheckoutController {

    private OrderService orderService;
    private WalletService walletService;
    private CartService cartService;

    public CheckoutController() {
        this.orderService = new OrderService();
        this.walletService = new WalletService();
        this.cartService = new CartService();
    }

    public boolean checkout(int userId, List<CartItem> cartItems) {

        double total = cartService.getTotalPrice(userId);

        // 🔐 CEK SALDO WALLET
        if (!walletService.pay(userId, total)) {
            return false; // saldo tidak cukup
        }

        // 📦 SIMPAN ORDER
        return orderService.checkout(userId, cartItems);
    }
}