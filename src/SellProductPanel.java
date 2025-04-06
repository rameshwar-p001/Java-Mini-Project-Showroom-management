import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SellProductPanel extends JPanel {
    JComboBox<String> productDropdown, customerDropdown;
    JTextField quantityField;

    public SellProductPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Sell Product"));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        productDropdown = new JComboBox<>();
        customerDropdown = new JComboBox<>();
        quantityField = new JTextField(15);
        JButton sellButton = new JButton("Sell");

        populateDropdowns();

        String[] labels = {"Select Product:", "Select Customer:", "Quantity:"};
        Component[] fields = {productDropdown, customerDropdown, quantityField};

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
        formPanel.add(sellButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        sellButton.addActionListener(e -> sellProduct());
    }

    void populateDropdowns() {
        try (Connection conn = DatabaseConnection.connect()) {
            Statement stmt = conn.createStatement();

            ResultSet products = stmt.executeQuery("SELECT id, name FROM product");
            while (products.next()) {
                productDropdown.addItem(products.getInt("id") + " - " + products.getString("name"));
            }

            ResultSet customers = stmt.executeQuery("SELECT id, name FROM customer");
            while (customers.next()) {
                customerDropdown.addItem(customers.getInt("id") + " - " + customers.getString("name"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading dropdowns: " + e.getMessage());
        }
    }

    void sellProduct() {
        try {
            int productId = Integer.parseInt(productDropdown.getSelectedItem().toString().split(" - ")[0]);
            int customerId = Integer.parseInt(customerDropdown.getSelectedItem().toString().split(" - ")[0]);
            int quantity = Integer.parseInt(quantityField.getText());

            try (Connection conn = DatabaseConnection.connect()) {
                String getPriceSQL = "SELECT price FROM product WHERE id = ?";
                PreparedStatement getPriceStmt = conn.prepareStatement(getPriceSQL);
                getPriceStmt.setInt(1, productId);
                ResultSet rs = getPriceStmt.executeQuery();

                if (rs.next()) {
                    double price = rs.getDouble("price");
                    double totalPrice = quantity * price;

                    String insertSale = "INSERT INTO sales(product_id, customer_id, quantity, total_price, date) VALUES (?, ?, ?, ?, datetime('now'))";
                    PreparedStatement stmt = conn.prepareStatement(insertSale);
                    stmt.setInt(1, productId);
                    stmt.setInt(2, customerId);
                    stmt.setInt(3, quantity);
                    stmt.setDouble(4, totalPrice);

                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Product sold successfully!");
                    quantityField.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
