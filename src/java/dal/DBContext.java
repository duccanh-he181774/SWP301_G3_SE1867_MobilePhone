package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=MobileShop";
    private final String username = "yourUsername";
    private final String password = "yourPassword";

    public Connection getConnection() throws SQLException {
        try {
            // Load the SQL Server JDBC Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server Driver not found", e);
        }
        return DriverManager.getConnection(url, username, password);
    }
}
