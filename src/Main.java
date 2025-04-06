import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Showroom Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Background Image
        JLabel background = new JLabel(new ImageIcon("background.jpg")); // Make sure image is in project folder
        background.setLayout(new BorderLayout());
        frame.setContentPane(background);

        // Top Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 150, 243, 180));
        JLabel titleLabel = new JLabel("\uD83D\uDE97 Showroom Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Sidebar Menu
        JPanel menuPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] menuItems = {
            "Add Product", "View Products", "Add Customer",
            "Sell Product", "View Sales", "Exit"
        };

        Icon[] icons = {
            new ImageIcon("icons/add.png"), new ImageIcon("icons/view.png"),
            new ImageIcon("icons/customer.png"), new ImageIcon("icons/sell.png"),
            new ImageIcon("icons/sales.png"), new ImageIcon("icons/exit.png")
        };

        JButton[] menuButtons = new JButton[menuItems.length];

        for (int i = 0; i < menuItems.length; i++) {
            menuButtons[i] = new JButton(menuItems[i], icons[i]);
            menuButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            menuButtons[i].setBackground(new Color(255, 255, 255, 200));
            menuButtons[i].setFocusPainted(false);
            menuButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            menuButtons[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            menuPanel.add(menuButtons[i]);
        }

        JScrollPane contentPanel = new JScrollPane();
        contentPanel.getViewport().setOpaque(false);
        contentPanel.setOpaque(false);
        contentPanel.setViewportView(new JPanel()); // initially blank

        // Button Actions
        menuButtons[0].addActionListener(e -> contentPanel.setViewportView(new AddProductPanel()));
        menuButtons[1].addActionListener(e -> contentPanel.setViewportView(new ViewProductPanel()));
        menuButtons[2].addActionListener(e -> contentPanel.setViewportView(new AddCustomerPanel()));
        menuButtons[3].addActionListener(e -> contentPanel.setViewportView(new SellProductPanel()));
        menuButtons[4].addActionListener(e -> contentPanel.setViewportView(new ViewSalesPanel()));
        menuButtons[5].addActionListener(e -> System.exit(0));

        // Main Panel Setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // ===== Load Fancy Login First =====
        LoginPanel login = new LoginPanel(frame, mainPanel);
        frame.setContentPane(login);
        frame.setVisible(true);
    }
}