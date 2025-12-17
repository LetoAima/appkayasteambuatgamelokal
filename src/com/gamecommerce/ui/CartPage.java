package com.gamecommerce.ui;

import com.gamecommerce.controller.CartController;
import com.gamecommerce.model.CartItem;
import com.gamecommerce.controller.CheckoutController;
import com.gamecommerce.session.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CartPage extends JFrame {

    private JTable cartTable;
    private DefaultTableModel tableModel;
    private CartController controller;
    private int currentUserId = UserSession.getInstance().getUserId();
    private CheckoutController checkoutController;

    public CartPage() {
        setTitle("Your Cart");
        setSize(600, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new CartController();
        checkoutController = new CheckoutController();

        String[] columns = {"ID", "Game", "Price", "Qty", "Subtotal"};
        tableModel = new DefaultTableModel(columns, 0);
        cartTable = new JTable(tableModel);

        loadCartData();

        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel totalLabel = new JLabel("Total: Rp 0");
        JButton checkoutBtn = new JButton("Checkout");

        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutBtn, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        updateTotal(totalLabel);

        checkoutBtn.addActionListener(e -> {

            List<CartItem> items = controller.getCartItems(currentUserId);

            if (items.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart kosong");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin checkout?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = checkoutController.checkout(currentUserId, items);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Checkout berhasil!");
                    loadCartData(); // cart kosong
                } else {
                    JOptionPane.showMessageDialog(this, "Checkout gagal");
                }
            }
        });

        setVisible(true);
    }

    private void loadCartData() {
        tableModel.setRowCount(0);
        List<CartItem> items = controller.getCartItems(currentUserId);

        for (CartItem item : items) {
            tableModel.addRow(new Object[]{
                    item.getCartId(),
                    item.getTitle(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getSubtotal()
            });
        }
    }

    private void updateTotal(JLabel totalLabel) {
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += (double) tableModel.getValueAt(i, 4);
        }
        totalLabel.setText("Total: Rp " + total);
    }
}
