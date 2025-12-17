package setupDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TrendingService {
    public void lihatTrending() {
        // Query untuk mencari game yang paling banyak muncul di tabel keranjang
        String sql = "SELECT g.judul, COUNT(c.game_id) AS total_peminat " +
                "FROM games g JOIN cart c ON g.game_id = c.game_id " +
                "GROUP BY g.judul ORDER BY total_peminat DESC LIMIT 5";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- GAME LOKAL SEDANG TRENDING ---");
            while (rs.next()) {
                System.out.println(rs.getString("judul") + " | Peminat: " + rs.getInt("total_peminat"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}