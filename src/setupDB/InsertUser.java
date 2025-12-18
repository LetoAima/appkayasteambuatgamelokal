package setupDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertUser {
    public static void main(String[] args) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        // Data User: {Username, Password, Role}
        String[][] dataUsers = {
                {"raihan_oktopus", "pass111"},
                {"hanif_wiawiu", "pass222"},
                {"dunde_dewind", "pass333"},
                {"yoman_iguana", "pass444"},
                {"jilan_wavy", "pass555"},
                {"huda_yuw", "pass666"},
                {"zidan_azz", "pass777"}
        };

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop insert data sesuai format array multidimensi
            for (String[] user : dataUsers) {
                pstmt.setString(1, user[0]); // username
                pstmt.setString(2, user[1]); // password
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Data user berhasil dimasukkan ke database!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}