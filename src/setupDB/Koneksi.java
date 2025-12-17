package setupDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_game_lokal";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("setupDB.Koneksi ke Database Berhasil!");
            }
        } catch (SQLException e) {
            System.out.println("setupDB.Koneksi Gagal: " + e.getMessage());
        }
    }
}