import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCustomerForm extends JPanel {
    JTextField nameField, contactField, emailField;

    public AddCustomerForm() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Add Customer", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        nameField = new JTextField();
        contactField = new JTextField();
        emailField = new JTextField();

        formPanel.add(new JLabel("Customer Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Contact:"));
        formPanel.add(contactField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        JButton addButton = new JButton("Add Customer");
        formPanel.add(new JLabel());
        formPanel.add(addButton);

        add(formPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addCustomer());
    }

    void addCustomer() {
        String name = nameField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();

        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO customer(name, contact, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, contact);
            stmt.setString(3, email);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
