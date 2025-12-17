package com.gamecommerce.ui;

import com.gamecommerce.controller.CartController;
import com.gamecommerce.controller.GameController;
import com.gamecommerce.model.Game;
import com.gamecommerce.session.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HomePage extends JFrame {

    private JTable gameTable;
    private DefaultTableModel tableModel;

    private GameController gameController;
    private CartController cartController;

    private int userId;

    // ===== CONSTRUCTOR =====
    public HomePage() {
        checkSession();

        initControllers();
        initFrame();
        initTable();
        initLayout();
        initActions();

        loadGameData();
        setVisible(true);
    }

    // ===== SESSION CHECK =====
    private void checkSession() {
        UserSession session = UserSession.getInstance();
        if (!session.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Silakan login dulu");
            new LoginPage().setVisible(true);
            dispose();
            return;
        }
        userId = session.getUserId();
    }

    // ===== INITIALIZATION =====
    private void initControllers() {
        gameController = new GameController();
        cartController = new CartController();
    }

    private void initFrame() {
        setTitle("Game Store - Home");
        setSize(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void initTable() {
        String[] columns = {"ID", "Title", "Genre", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        gameTable = new JTable(tableModel);
    }

    // ===== UI LAYOUT =====
    private void initLayout() {

        // CENTER - GAME LIST
        add(new JScrollPane(gameTable), BorderLayout.CENTER);

        // TOP PANEL
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addToCartBtn = new JButton("Add to Cart");
        JButton refreshBtn = new JButton("Refresh");

        topPanel.add(addToCartBtn);
        topPanel.add(refreshBtn);
        add(topPanel, BorderLayout.NORTH);

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton walletBtn = new JButton("My Wallet");
        JButton cartBtn = new JButton("View Cart");
        JButton logoutBtn = new JButton("Logout");

        walletBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(walletBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(cartBtn);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(logoutBtn);

        add(rightPanel, BorderLayout.EAST);

        // ACTION BINDING
        addToCartBtn.addActionListener(e -> addToCart());
        refreshBtn.addActionListener(e -> loadGameData());
        cartBtn.addActionListener(e -> new CartPage().setVisible(true));
        walletBtn.addActionListener(e -> new WalletPage());
        logoutBtn.addActionListener(e -> logout());
    }

    // ===== ACTION METHODS =====
    private void initActions() {
        // reserved for future actions
    }

    private void addToCart() {
        int selectedRow = gameTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih game dulu");
            return;
        }

        int gameId = (int) tableModel.getValueAt(selectedRow, 0);
        boolean success = cartController.addToCart(userId, gameId);

        JOptionPane.showMessageDialog(
                this,
                success ? "Game ditambahkan ke cart" : "Gagal menambahkan ke cart"
        );
    }

    private void logout() {
        UserSession.getInstance().clear();
        JOptionPane.showMessageDialog(this, "Logout berhasil");
        new LoginPage().setVisible(true);
        dispose();
    }

    // ===== DATA LOADER =====
    private void loadGameData() {
        tableModel.setRowCount(0);
        List<Game> games = gameController.fetchGames();

        for (Game g : games) {
            tableModel.addRow(new Object[]{
                    g.getId(),
                    g.getTitle(),
                    g.getGenre(),
                    g.getPrice()
            });
        }
    }
}