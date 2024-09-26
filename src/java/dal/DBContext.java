package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public Connection connection;

    private String url = "jdbc:sqlserver://localhost:1433;databaseName=MobileShop;encrypt=false;trustServerCertificate=true";
    private String username = "sa";
    private String password = "123";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server Driver not found", e);
        }

        return DriverManager.getConnection(url, username, password);
    }
}
