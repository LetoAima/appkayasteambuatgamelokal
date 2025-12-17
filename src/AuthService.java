import setupDB.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login Berhasil! Selamat datang, " + rs.getString("username"));
                return true;
            } else {
                System.out.println("Username atau Password salah.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}