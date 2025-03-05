package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUser {
    public static void main(String[] args) {
        String sql = "UPDATE users SET email = ? WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "newemail@example.com");
            stmt.setString(2, "John Doe");

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("✅ " + rowsUpdated + " row updated successfully!");

        } catch (SQLException e) {  // ✅ Fix: Proper error handling
            e.printStackTrace();  
        }
    }
}
