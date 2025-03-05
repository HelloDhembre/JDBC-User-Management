package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUser {
    public static void main(String[] args) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "John Doe");
            stmt.setString(2, "john.doe@example.com");

            int rowsAffected = stmt.executeUpdate();
            System.out.println("✅ " + rowsAffected + " row inserted successfully!");

        } catch (SQLException e) {  // ✅ Proper exception handling
            e.printStackTrace();  
        }
    }
}
