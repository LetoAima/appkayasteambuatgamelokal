package com.gamecommerce.service;

import com.gamecommerce.config.DBConnection;

import java.sql.*;

public class WalletService {

    public double getBalance(int userId) {
        String sql = "SELECT balance FROM wallets WHERE user_id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getDouble("balance");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean topUp(int userId, double amount) {
        if (amount <= 0) return false;

        String update = "UPDATE wallets SET balance = balance + ? WHERE user_id=?";
        String insertLog = """
            INSERT INTO wallet_transactions(user_id, amount, type, description)
            VALUES (?, ?, 'TOP_UP', 'Top up wallet')
        """;

        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);

            try (PreparedStatement ps1 = c.prepareStatement(update);
                 PreparedStatement ps2 = c.prepareStatement(insertLog)) {

                ps1.setDouble(1, amount);
                ps1.setInt(2, userId);
                ps1.executeUpdate();

                ps2.setInt(1, userId);
                ps2.setDouble(2, amount);
                ps2.executeUpdate();

                c.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean pay(int userId, double totalPrice) {
        String check = "SELECT balance FROM wallets WHERE user_id=? FOR UPDATE";
        String deduct = "UPDATE wallets SET balance = balance - ? WHERE user_id=?";
        String log = """
            INSERT INTO wallet_transactions(user_id, amount, type, description)
            VALUES (?, ?, 'PURCHASE', 'Game purchase')
        """;

        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);

            try (PreparedStatement psCheck = c.prepareStatement(check)) {
                psCheck.setInt(1, userId);
                ResultSet rs = psCheck.executeQuery();

                if (!rs.next() || rs.getDouble("balance") < totalPrice) {
                    c.rollback();
                    return false;
                }
            }

            try (PreparedStatement psDeduct = c.prepareStatement(deduct);
                 PreparedStatement psLog = c.prepareStatement(log)) {

                psDeduct.setDouble(1, totalPrice);
                psDeduct.setInt(2, userId);
                psDeduct.executeUpdate();

                psLog.setInt(1, userId);
                psLog.setDouble(2, totalPrice);
                psLog.executeUpdate();

                c.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}