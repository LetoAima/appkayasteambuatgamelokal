import setupDB.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GameService {
    public void tampilkanKatalog() {
        String sql = "SELECT * FROM games";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== KATALOG GAME LOKAL INDONESIA ===");
            System.out.println("ID | Judul Game | Harga");
            System.out.println("-----------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("game_id") + " | " +
                                rs.getString("judul") + " | Rp" +
                                rs.getDouble("harga")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}