import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SellProductForm extends JFrame {
    JComboBox<String> productBox, customerBox;
    JTextField quantityField;

    public SellProductForm() {
        setTitle("Sell Product");
        setSize(350, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        productBox = new JComboBox<>();
        customerBox = new JComboBox<>();
        quantityField = new JTextField();
        JButton sellButton = new JButton("Sell");

        // Load products and customers into combo boxes
        loadProducts();
        loadCustomers();

        add(new JLabel("Product:"));
        add(productBox);
        add(new JLabel("Customer:"));
        add(customerBox);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel());
        add(sellButton);

        sellButton.addActionListener(e -> sellProduct());

        setVisible(true);
    }

    void loadProducts() {
        try (Connection conn = DatabaseConnection.connect()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT id, name FROM product");
            while (rs.next()) {
                productBox.addItem(rs.getInt("id") + "-" + rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }
    }

    void loadCustomers() {
        try (Connection conn = DatabaseConnection.connect()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT id, name FROM customer");
            while (rs.next()) {
                customerBox.addItem(rs.getInt("id") + "-" + rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
        }
    }

    void sellProduct() {
        String productItem = (String) productBox.getSelectedItem();
        String customerItem = (String) customerBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());

        int productId = Integer.parseInt(productItem.split("-")[0]);
        int customerId = Integer.parseInt(customerItem.split("-")[0]);

        try (Connection conn = DatabaseConnection.connect()) {
            // Get product price & stock
            PreparedStatement ps = conn.prepareStatement("SELECT price, stock FROM product WHERE id = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) throw new Exception("Product not found");
            double price = rs.getDouble("price");
            int stock = rs.getInt("stock");

            if (stock < quantity) {
                JOptionPane.showMessageDialog(this, "Not enough stock!");
                return;
            }

            double totalPrice = quantity * price;

            // Insert into sales
            PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO sales(product_id, customer_id, quantity, total_price, date) VALUES (?, ?, ?, ?, date('now'))"
            );
            insert.setInt(1, productId);
            insert.setInt(2, customerId);
            insert.setInt(3, quantity);
            insert.setDouble(4, totalPrice);
            insert.executeUpdate();

            // Update product stock
            PreparedStatement update = conn.prepareStatement("UPDATE product SET stock = stock - ? WHERE id = ?");
            update.setInt(1, quantity);
            update.setInt(2, productId);
            update.executeUpdate();

            JOptionPane.showMessageDialog(this, "Sale completed! ₹" + totalPrice);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
