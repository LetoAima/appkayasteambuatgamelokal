package com.gamecommerce.service;

import com.gamecommerce.config.DBConnection;
import com.gamecommerce.model.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartService {

    public boolean addToCart(int userId, int gameId) {
        String checkSql = "SELECT quantity FROM cart WHERE user_id=? AND game_id=?";
        String insertSql = "INSERT INTO cart (user_id, game_id, quantity) VALUES (?, ?, 1)";
        String updateSql = "UPDATE cart SET quantity = quantity + 1 WHERE user_id=? AND game_id=?";

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, userId);
            checkPs.setInt(2, gameId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, userId);
                updatePs.setInt(2, gameId);
                return updatePs.executeUpdate() > 0;
            } else {
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, userId);
                insertPs.setInt(2, gameId);
                return insertPs.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CartItem> getCartByUser(int userId) {
        List<CartItem> cartItems = new ArrayList<>();

        String sql = """
        SELECT c.id, g.id AS game_id, g.title, g.price, c.quantity
        FROM cart c
        JOIN games g ON c.game_id = g.id
        WHERE c.user_id = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("id"),
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    public double getTotalPrice(int userId) {

        String sql = """
        SELECT SUM(g.price * c.quantity) AS total
        FROM cart c
        JOIN games g ON c.game_id = g.id
        WHERE c.user_id = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
