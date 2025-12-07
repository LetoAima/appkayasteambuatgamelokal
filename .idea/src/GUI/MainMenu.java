import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("Game Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        // =======================
        // LEFT SIDEBAR
        // =======================
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(45, 45, 55));
        leftPanel.setPreferredSize(new Dimension(200, 700));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel yourGameLabel = new JLabel("Your Game");
        yourGameLabel.setForeground(Color.WHITE);
        yourGameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        yourGameLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        leftPanel.add(yourGameLabel);

        for (int i = 1; i <= 5; i++) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(new Color(55, 55, 65));
            itemPanel.setMaximumSize(new Dimension(180, 40));
            itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JPanel icon = new JPanel();
            icon.setBackground(new Color(120, 100, 100));
            icon.setPreferredSize(new Dimension(30, 30));

            JLabel name = new JLabel("Game " + i);
            name.setForeground(Color.WHITE);

            itemPanel.add(icon);
            itemPanel.add(name);
            leftPanel.add(itemPanel);
        }

        // =======================
        // HEADER RIGHT (wishlist & cart)
        // =======================
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerRight.setBackground(new Color(40, 40, 50));

        JButton wishlistBtn = new JButton("♥");
        JButton cartBtn = new JButton("🛒");

        styleTopButton(wishlistBtn);
        styleTopButton(cartBtn);

        headerRight.add(wishlistBtn);
        headerRight.add(cartBtn);

        // =======================
        // CONTENT RIGHT (Scrollable)
        // =======================
        JPanel contentRight = new JPanel();
        contentRight.setLayout(new BoxLayout(contentRight, BoxLayout.Y_AXIS));
        contentRight.setBackground(new Color(40, 40, 50));

        contentRight.add(headerRight);
        contentRight.add(section("What's New", 3));
        contentRight.add(section("Continue Playing", 6));
        contentRight.add(section("Discover", 2));

        JScrollPane mainScroll = new JScrollPane(contentRight);
        mainScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // =======================
        // MAIN SPLIT LAYOUT
        // =======================
        frame.setLayout(new BorderLayout());
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(mainScroll, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // =======================
    // METHOD: Style top right header buttons
    // =======================
    private static void styleTopButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(60, 60, 70));
        btn.setPreferredSize(new Dimension(60, 40));
    }

    // =======================
    // METHOD: Section block
    // =======================
    private static JPanel section(String title, int itemCount) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(new Color(40, 40, 50));
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        sectionPanel.add(label);

        // Horizontal scroll area
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setBackground(new Color(40, 40, 50));

        for (int i = 0; i < itemCount; i++) {
            JPanel card = new JPanel();
            card.setBackground(new Color(120, 100, 100));
            card.setPreferredSize(new Dimension(250, 150));
            card.setMaximumSize(new Dimension(250, 150));
            card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            row.add(card);
            row.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        JScrollPane hScroll = new JScrollPane(row);
        hScroll.setPreferredSize(new Dimension(900, 180));
        hScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        sectionPanel.add(hScroll);

        return sectionPanel;
    }
}