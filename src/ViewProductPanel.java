import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewProductPanel extends JPanel {
    public ViewProductPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Brand", "Price", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT * FROM product";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
