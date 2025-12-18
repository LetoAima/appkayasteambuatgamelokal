package setupDB;

import java.sql.Connection;
import java.sql.Statement;

public class SetupDatabase {
    public static void main(String[] args) {
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement()) {

            // 1. Tabel User (Hanya untuk pelanggan/pembeli)
            String sqlUser = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL)";
            stmt.execute(sqlUser);

            // 2. Tabel Developer (Data studio/pengembang, tidak login)
            String sqlDev = "CREATE TABLE IF NOT EXISTS developers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nama_studio VARCHAR(100) UNIQUE NOT NULL, " +
                    "lokasi VARCHAR(100))";
            stmt.execute(sqlDev);

            // 3. Tabel Games (Merujuk ke developers)
            String sqlGame = "CREATE TABLE IF NOT EXISTS games (" +
                    "game_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "judul VARCHAR(100) UNIQUE NOT NULL, " +
                    "deskripsi TEXT, " +
                    "harga DOUBLE, " +
                    "developer_id INT, " +
                    "FOREIGN KEY (developer_id) REFERENCES developers(id))";
            stmt.execute(sqlGame);

            // Tabel Keranjang (Sistem keranjang - Fitur 03)
            String sqlCart = "CREATE TABLE IF NOT EXISTS cart (" +
                    "user_id INT, game_id INT, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id), " +
                    "FOREIGN KEY (game_id) REFERENCES games(game_id), " +
                    "PRIMARY KEY (user_id, game_id))";
            stmt.execute(sqlCart);

            // Tabel Wishlist (Fitur 04)
            String sqlWishlist = "CREATE TABLE IF NOT EXISTS wishlist (" +
                    "user_id INT, game_id INT, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id), " +
                    "FOREIGN KEY (game_id) REFERENCES games(game_id), " +
                    "PRIMARY KEY (user_id, game_id))";
            stmt.execute(sqlWishlist);

            System.out.println("Database berhasil disiapkan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}