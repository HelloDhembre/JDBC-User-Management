package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
    public static void main(String[] args) {
        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "John Doe");

            int rowsDeleted = stmt.executeUpdate();
            System.out.println("❌ " + rowsDeleted + " row deleted successfully!");

        } catch (SQLException e) {  // ✅ Fix: Proper error handling
            e.printStackTrace();  
        }
    }
}
