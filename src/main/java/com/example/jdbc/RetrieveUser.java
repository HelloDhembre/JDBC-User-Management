package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveUser {
    public static void main(String[] args) {
        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("🔹 ID: " + id + ", Name: " + name + ", Email: " + email);
            }

        } catch (SQLException e) {  // ✅ Fix: Ensure correct exception handling
            e.printStackTrace();  
        }
    }
}
