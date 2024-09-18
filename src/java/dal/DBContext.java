package dal;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBContext {
 
     public Connection getConnection()throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
    
    private final String serverName = System.getenv("DB_SERVER") != null ? System.getenv("DB_SERVER") : "localhost";
    private final String dbName = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "MobileShop";
    private final String portNumber = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "1433";
    private final String userID = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "sa";
    private final String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "123";

}
