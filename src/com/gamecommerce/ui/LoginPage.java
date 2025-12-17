package com.gamecommerce.ui;

import com.gamecommerce.controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Game Store - Login");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        panel.add(loginBtn);
        panel.add(registerBtn);

        add(panel);

        LoginController controller = new LoginController(this);
        loginBtn.addActionListener(e -> controller.login());

        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });

        setVisible(true);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}