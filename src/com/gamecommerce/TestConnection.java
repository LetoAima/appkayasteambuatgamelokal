package com.gamecommerce;

import com.gamecommerce.config.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("✅ Koneksi MySQL BERHASIL");
        } else {
            System.out.println("❌ Koneksi MySQL GAGAL");
        }
    }
}