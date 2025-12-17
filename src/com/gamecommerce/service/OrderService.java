package com.gamecommerce.service;

import com.gamecommerce.config.DBConnection;
import com.gamecommerce.model.CartItem;

import java.sql.*;
import java.util.List;

public class OrderService {

    public boolean checkout(int userId, List<CartItem> cartItems) {

        String insertOrderSql =
                "INSERT INTO orders (user_id, total_price) VALUES (?, ?)";

        String insertOrderItemSql =
                "INSERT INTO order_items (order_id, game_id, price, quantity) VALUES (?, ?, ?, ?)";

        String clearCartSql =
                "DELETE FROM cart WHERE user_id = ?";

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 🔥 START TRANSACTION

            // 1️⃣ hitung total
            double total = cartItems.stream()
                    .mapToDouble(CartItem::getSubtotal)
                    .sum();

            // 2️⃣ insert orders
            PreparedStatement orderPs =
                    conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
            orderPs.setInt(1, userId);
            orderPs.setDouble(2, total);
            orderPs.executeUpdate();

            ResultSet rs = orderPs.getGeneratedKeys();
            if (!rs.next()) throw new SQLException("Gagal membuat order");
            int orderId = rs.getInt(1);

            // 3️⃣ insert order_items
            for (CartItem item : cartItems) {

                // order_items
                PreparedStatement itemPs = conn.prepareStatement(insertOrderItemSql);
                itemPs.setInt(1, orderId);
                itemPs.setInt(2, item.getGameId());
                itemPs.setDouble(3, item.getPrice());
                itemPs.setInt(4, item.getQuantity());
                itemPs.executeUpdate();
            }

            // 4️⃣ clear cart
            PreparedStatement clearPs = conn.prepareStatement(clearCartSql);
            clearPs.setInt(1, userId);
            clearPs.executeUpdate();

            conn.commit(); // ✅ COMMIT
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // ❌ ROLLBACK
            } catch (SQLException ignored) {}
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException ignored) {}
        }
    }
}
