import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewSalesForm extends JFrame {
    JTable table;
    DefaultTableModel model;

    public ViewSalesForm() {
        setTitle("View Sales");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Product", "Customer", "Qty", "Total ₹", "Date"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadSales();

        setVisible(true);
    }

    void loadSales() {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT sales.id, product.name AS product_name, customer.name AS customer_name, " +
                         "sales.quantity, sales.total_price, sales.date " +
                         "FROM sales " +
                         "JOIN product ON sales.product_id = product.id " +
                         "JOIN customer ON sales.customer_id = customer.id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String product = rs.getString("product_name");
                String customer = rs.getString("customer_name");
                int qty = rs.getInt("quantity");
                double total = rs.getDouble("total_price");
                String date = rs.getString("date");

                model.addRow(new Object[]{id, product, customer, qty, total, date});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
