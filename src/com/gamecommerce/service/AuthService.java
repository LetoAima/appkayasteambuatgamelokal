package com.gamecommerce.service;

import com.gamecommerce.config.DBConnection;
import com.gamecommerce.model.User;

import java.sql.*;

public class AuthService {

    // LOGIN
    public User login(String email, String password) {

        String sql = "SELECT id, username, email FROM users WHERE email=? AND password=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim());
            ps.setString(2, password.trim());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // REGISTER
    public boolean register(String username, String email, String password) {

        String userSql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        String walletSql = "INSERT INTO wallets(user_id, balance) VALUES (?, 0)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement userPs =
                    conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);

            userPs.setString(1, username);
            userPs.setString(2, email);
            userPs.setString(3, password);
            userPs.executeUpdate();

            ResultSet rs = userPs.getGeneratedKeys();
            if (!rs.next()) {
                conn.rollback();
                return false;
            }

            int userId = rs.getInt(1);

            PreparedStatement walletPs = conn.prepareStatement(walletSql);
            walletPs.setInt(1, userId);
            walletPs.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email sudah terdaftar");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}