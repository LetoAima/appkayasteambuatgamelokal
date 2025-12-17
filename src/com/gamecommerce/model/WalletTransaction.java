package com.gamecommerce.model;

public class WalletTransaction {
    private int userId;
    private double amount;
    private String type;
    private String description;

    public WalletTransaction(int userId, double amount, String type, String description) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }
}
