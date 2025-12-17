package setupDB;

import java.sql.Connection;
import java.sql.Statement;

public class SetupDatabase {
    public static void main(String[] args) {
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement()) {

            // Tabel Users: username sudah UNIQUE (Penambahan ditolak jika sama)
            String sqlUser = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "role ENUM('developer', 'user') NOT NULL)";
            stmt.execute(sqlUser);

            // Tabel Games: Tambahkan UNIQUE pada judul untuk mencegah redundansi
            String sqlGame = "CREATE TABLE IF NOT EXISTS games (" +
                    "game_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "judul VARCHAR(100) UNIQUE NOT NULL, " + // Tambahan UNIQUE
                    "deskripsi TEXT, " +
                    "harga DOUBLE, " +
                    "developer_id INT, " +
                    "FOREIGN KEY (developer_id) REFERENCES users(id))";
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