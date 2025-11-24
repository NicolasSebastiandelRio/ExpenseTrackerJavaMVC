package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=expenses_db;encrypt=true;trustServerCertificate=true;";
    private static final String user = "HP_NICOLAS\nicol";
    private static final String password = "";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("JDBC Driver loaded successfully, Connecting to DB....");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        }
        return conn;
    }

    public static void close(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close PreparedStatement: " + e.getMessage());
        }
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("Resources closed successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to close resources: " + e.getMessage());
        }
    }
}