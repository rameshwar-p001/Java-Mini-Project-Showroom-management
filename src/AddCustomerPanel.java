import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCustomerPanel extends JPanel {
    JTextField nameField, contactField, emailField;

    public AddCustomerPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Add New Customer"));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        contactField = new JTextField(15);
        emailField = new JTextField(15);
        JButton addButton = new JButton("Add Customer");

        String[] labels = {"Customer Name:", "Contact:", "Email:"};
        JTextField[] fields = {nameField, contactField, emailField};

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

        addButton.addActionListener(e -> addCustomer());
    }

    void addCustomer() {
        try {
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
                nameField.setText("");
                contactField.setText("");
                emailField.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
