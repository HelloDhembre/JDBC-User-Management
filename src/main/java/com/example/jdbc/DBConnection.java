package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_example"; // Check if database exists
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "Pass@123"; // Your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) { // ✅ Correctly handling errors
            e.printStackTrace();  // ✅ This will print the error details
            return null; // Return null if connection fails
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connected to MySQL database successfully!");
            } else {
                System.out.println("❌ Failed to connect to MySQL.");
            }
        } catch (Exception e) { // Catch general exceptions
            e.printStackTrace(); 
        }
    }
}
