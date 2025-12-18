package setupDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertDeveloper {
    public static void main(String[] args) {
        String sql = "INSERT INTO developers (nama_studio, lokasi) VALUES (?, ?)";

        // Data Developer: {Nama Studio, Lokasi}
        String[][] dataDevs = {
                {"Toge Productions", "Tangerang"},
                {"Stairway Games", "Yogyakarta"},
                {"MassHive Media", "Bandung"},
                {"Gamecom Team", "Jakarta"},
                {"Digital Happiness", "Bandung"},
                {"Mad Production", "Condet"},
                {"Mbut Studio", "Cilincing"}
        };

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (String[] dev : dataDevs) {
                pstmt.setString(1, dev[0]);
                pstmt.setString(2, dev[1]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Data Developer berhasil dimasukkan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
