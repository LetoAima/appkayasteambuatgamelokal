package GUI;
import javax.swing.*;
import java.awt.*;

public class LoginPage {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }

    public LoginPage() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Background
        JPanel bg = new JPanel();
        bg.setBackground(new Color(210, 220, 220));
        bg.setLayout(new GridBagLayout());  // center panel

        // Login container panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 45, 55));
        panel.setPreferredSize(new Dimension(450, 350));
        panel.setLayout(null); // manual positioning
        panel.setBorder(BorderFactory.createLineBorder(new Color(45, 45, 55), 10));

        // LOGIN title
        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setBounds(0, 20, 450, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        panel.add(title);

        // Username label
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(60, 90, 200, 20);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        // Username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(60, 115, 330, 30);
        usernameField.setBackground(new Color(45, 45, 55));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        panel.add(usernameField);

        // Password label
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(60, 160, 200, 20);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        panel.add(passLabel);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(60, 185, 330, 30);
        passwordField.setBackground(new Color(45, 45, 55));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        panel.add(passwordField);

        // Login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(175, 245, 100, 40);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(120, 100, 100));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder());
        panel.add(loginBtn);

        // Action: open MainMenu
        loginBtn.addActionListener(e -> {
            frame.dispose(); // close login window

            // BUKA MainMenu
            MainMenu.main(null);
        });

        bg.add(panel);
        frame.add(bg);
        frame.setVisible(true);
    }
}
