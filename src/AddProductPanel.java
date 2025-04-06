import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddProductPanel extends JPanel {
    private JTextField nameField, brandField, priceField, stockField;

    public AddProductPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f2f2f2"));  // Soft background

        JLabel title = new JLabel("Add New Product", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.white);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        brandField = new JTextField(15);
        priceField = new JTextField(15);
        stockField = new JTextField(15);

        String[] labels = {"Product Name:", "Brand:", "Price:", "Stock:"};
        JTextField[] fields = {nameField, brandField, priceField, stockField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(createLabel(labels[i]), gbc);

            gbc.gridx = 1;
            formPanel.add(wrapTextField(fields[i]), gbc);
        }

        JButton addButton = new JButton("Add Product");
        styleButton(addButton);
        addButton.addActionListener(e -> addProduct());

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private JPanel wrapTextField(JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panel.setBackground(Color.WHITE);
        panel.add(field);
        return panel;
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void addProduct() {
        try {
            String name = nameField.getText();
            String brand = brandField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            try (Connection conn = DatabaseConnection.connect()) {
                String sql = "INSERT INTO product(name, brand, price, stock) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, brand);
                stmt.setDouble(3, price);
                stmt.setInt(4, stock);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Product added successfully!");
                nameField.setText("");
                brandField.setText("");
                priceField.setText("");
                stockField.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
