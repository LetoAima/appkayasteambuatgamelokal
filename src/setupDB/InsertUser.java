package setupDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertUser {
    public static void main(String[] args) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        // Data User: {Username, Password, Role}
        String[][] dataUsers = {
                {"raihan_okto", "pass111", "developer"},
                {"hanif_awliya", "pass222", "user"},
                {"dunde_yusuf", "pass333", "developer"},
                {"yomandiguna", "pass444", "user"},
                {"jilan_atrida", "pass555", "user"},
                {"huda_corp", "pass666", "developer"},
                {"zidan_az", "pass777", "user"}
        };

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop insert data sesuai format array multidimensi
            for (String[] user : dataUsers) {
                pstmt.setString(1, user[0]); // username
                pstmt.setString(2, user[1]); // password
                pstmt.setString(3, user[2]); // role
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Data user berhasil dimasukkan ke database!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}