package com.gamecommerce.controller;

import com.gamecommerce.service.AuthService;
import com.gamecommerce.ui.LoginPage;
import com.gamecommerce.ui.HomePage;
import com.gamecommerce.session.UserSession;
import com.gamecommerce.model.User;

import javax.swing.*;

public class LoginController {

    private LoginPage view;
    private AuthService authService;

    public LoginController(LoginPage view) {
        this.view = view;
        this.authService = new AuthService();
    }

    public void login() {
        String email = view.getEmail();
        String password = view.getPassword();

        User user = authService.login(email, password); // 🔥 ambil User

        if (user != null) {
            UserSession.getInstance().setUser(user); // 🔥 SET SESSION
            JOptionPane.showMessageDialog(view, "Login berhasil");
            view.dispose();
            new HomePage().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Login gagal");
        }
    }
}
