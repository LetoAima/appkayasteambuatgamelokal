package setupDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertGame {
    public static void main(String[] args) {
        String sql = "INSERT INTO games (judul, deskripsi, harga, developer_id) VALUES (?, ?, ?, ?)";

        // Data Game: {Judul, Deskripsi, Harga, Developer_ID}
        String[][] dataGames = {
                {"Petualangan Nusantara", "RPG bertema budaya lokal", "75000", "1"},
                {"Balap Karung Sim", "Simulator balap karung 3D", "25000", "1"},
                {"Jalan Jalan", "Simulator keliling kota", "5000", "3"},
                {"Candi Quest", "Puzzle misteri candi", "45000", "3"},
                {"Wayang Battle", "Fighting game karakter wayang", "60000", "1"}
        };

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (String[] g : dataGames) {
                pstmt.setString(1, g[0]); // judul
                pstmt.setString(2, g[1]); // deskripsi
                pstmt.setDouble(3, Double.parseDouble(g[2])); // harga (konversi ke double)
                pstmt.setInt(4, Integer.parseInt(g[3]));    // developer_id (konversi ke int)
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Data katalog game berhasil dimasukkan ke database!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}