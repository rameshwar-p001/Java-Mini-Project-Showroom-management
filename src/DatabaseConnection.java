import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Properly escaped path (\\ instead of \)
            String url = "jdbc:sqlite:C:\\Users\\rameshwarpatil\\OneDrive\\Desktop\\Java new project\\showroom management\\showroom.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to database successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
