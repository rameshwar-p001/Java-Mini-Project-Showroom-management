import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddProductForm extends JPanel {
    JTextField nameField, brandField, priceField, stockField;

    public AddProductForm() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Add New Product"));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        brandField = new JTextField(15);
        priceField = new JTextField(15);
        stockField = new JTextField(15);
        JButton addButton = new JButton("Add Product");

        String[] labels = {"Product Name:", "Brand:", "Price:", "Stock:"};
        JTextField[] fields = {nameField, brandField, priceField, stockField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addProduct());
    }

    void addProduct() {
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
