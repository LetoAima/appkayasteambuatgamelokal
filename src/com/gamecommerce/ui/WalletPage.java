package com.gamecommerce.ui;

import com.gamecommerce.service.WalletService;
import com.gamecommerce.session.UserSession;

import javax.swing.*;
import java.awt.*;

public class WalletPage extends JFrame {

    private JLabel balanceLabel;
    private JTextField amountField;
    private WalletService walletService;
    private int userId;

    public WalletPage() {
        setTitle("My Wallet");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        walletService = new WalletService();
        userId = UserSession.getInstance().getUserId();

        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        updateBalance();

        JLabel amountLabel = new JLabel("Top Up Amount:");
        amountField = new JTextField();

        JButton topUpBtn = new JButton("Top Up");

        topUpBtn.addActionListener(e -> topUp());

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(balanceLabel);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(topUpBtn);

        add(panel);
        setVisible(true);
    }

    private void updateBalance() {
        double balance = walletService.getBalance(userId);
        balanceLabel.setText("Balance: Rp " + String.format("%,.0f", balance));
    }

    private void topUp() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Nominal harus lebih dari 0");
                return;
            }

            if (walletService.topUp(userId, amount)) {
                JOptionPane.showMessageDialog(this, "Top up berhasil");
                amountField.setText("");
                updateBalance();
            } else {
                JOptionPane.showMessageDialog(this, "Top up gagal");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid");
        }
    }
}