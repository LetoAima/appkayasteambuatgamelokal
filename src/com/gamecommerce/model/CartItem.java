package com.gamecommerce.model;

public class CartItem {
    private int cartId;
    private int gameId;
    private String title;
    private double price;
    private int quantity;

    public CartItem(int cartId, int gameId, String title, double price, int quantity) {
        this.cartId = cartId;
        this.gameId = gameId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartId() { return cartId; }
    public int getGameId() { return gameId; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return price * quantity; }
}
