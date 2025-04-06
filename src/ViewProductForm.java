import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewProductForm extends JFrame {
    JTable table;
    DefaultTableModel model;

    public ViewProductForm() {
        setTitle("View Products");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Brand", "Price", "Stock"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadProducts();

        setVisible(true);
    }

    void loadProducts() {
        try (Connection conn = DatabaseConnection.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                model.addRow(new Object[]{id, name, brand, price, stock});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
