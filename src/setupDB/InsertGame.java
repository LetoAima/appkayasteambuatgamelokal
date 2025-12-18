package setupDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertGame {
    public static void main(String[] args) {
        String sql = "INSERT INTO games (judul, deskripsi, harga, developer_id) VALUES (?, ?, ?, ?)";

        // Data Game: {Judul, Deskripsi, Harga, Developer_ID}
        String[][] dataGames = {
                {"A Space for The Unbound", "Petualangan naratif bertema supranatural di pedesaan Indonesia.", "99000", "1"},
                {"Coffe Talk", "Simulator menyeduh kopi dan mendengarkan cerita di Seattle alternatif.", "84900", "1"},
                {"When The Past Was Around", "Game puzzle point-and-click tentang cinta dan kehilangan.", "74000", "1"},
                {"Coral Island", "Simulator pertanian modern dengan misi melestarikan terumbu karang.", "245999", "2"},
                {"Potion Permit", "RPG simulasi menjadi ahli kimia di kota kecil Moonbury.", "165999", "3"},
                {"Troublemaker", "Game aksi petualangan tentang kehidupan sekolah dan turnamen bela diri.", "135000", "4"},
                {"Troublemaker 2: Beyond Dream", "Sekuel aksi tentang impian dan persahabatan di kota Jayakarta.", "211300", "4"},
                {"DreadOut", "Game horor supranatural tentang siswi SMA yang terjebak di kota mati.", "130999", "5"},
                {"DreadOut 2", "Kelanjutan horor Linda dengan aksi eksplorasi kota yang lebih luas.", "165999", "5"},
                {"Mad Goes MAD", "Perjalanan Ahmad memenangkan Battle Royale antar calon anggota HIMA", "349990", "6"},
                {"Tyan Ahli Rambut", "Menceritakan perjuangan Tyan melindungi kuil Sisilinsing", "160000", "7"}
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