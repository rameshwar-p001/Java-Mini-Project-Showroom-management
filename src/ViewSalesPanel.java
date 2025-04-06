import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewSalesPanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public ViewSalesPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Sales Records"));

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        model.addColumn("Sale ID");
        model.addColumn("Product");
        model.addColumn("Customer");
        model.addColumn("Quantity");
        model.addColumn("Total Price");
        model.addColumn("Date");

        loadSalesData();

        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadSalesData() {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = """
                SELECT s.id, p.name AS product, c.name AS customer, s.quantity, s.total_price, s.date
                FROM sales s
                JOIN product p ON s.product_id = p.id
                JOIN customer c ON s.customer_id = c.id
                ORDER BY s.date DESC
            """;

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("product"),
                    rs.getString("customer"),
                    rs.getInt("quantity"),
                    rs.getDouble("total_price"),
                    rs.getString("date")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load sales data: " + e.getMessage());
        }
    }
}
