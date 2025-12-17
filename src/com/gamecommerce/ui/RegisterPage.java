package com.gamecommerce.ui;

import com.gamecommerce.service.AuthService;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JFrame {

    public RegisterPage() {
        setTitle("Game Store - Register");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        panel.add(new JLabel("Username"));
        panel.add(usernameField);

        panel.add(new JLabel("Email"));
        panel.add(emailField);

        panel.add(new JLabel("Password"));
        panel.add(passwordField);

        JButton registerBtn = new JButton("Register");
        panel.add(new JLabel());
        panel.add(registerBtn);

        add(panel);

        registerBtn.addActionListener(e -> {
            AuthService authService = new AuthService();

            boolean success = authService.register(
                    usernameField.getText(),
                    emailField.getText(),
                    new String(passwordField.getPassword())
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Registrasi berhasil");
                dispose();
                new LoginPage();
            } else {
                JOptionPane.showMessageDialog(this, "Registrasi gagal");
            }
        });

        setVisible(true);
    }
}