package setupDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MarketService {
    // Tambah ke Keranjang
    public void addToCart(int userId, int gameId) {
        String sql = "INSERT INTO cart (user_id, game_id) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            pstmt.executeUpdate();
            System.out.println("Game berhasil dimasukkan ke keranjang!");
        } catch (Exception e) {
            System.out.println("Game sudah ada di keranjang.");
        }
    }

    // Tambah ke Wishlist
    public void addToWishlist(int userId, int gameId) {
        String sql = "INSERT INTO wishlist (user_id, game_id) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            pstmt.executeUpdate();
            System.out.println("Game ditambahkan ke Wishlist!");
        } catch (Exception e) {
            System.out.println("Gagal menambahkan ke wishlist.");
        }
    }
}