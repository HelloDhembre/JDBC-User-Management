package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertUser {
    public static void main(String[] args) {
        // ✅ Use try-with-resources to automatically close Scanner
        try (Scanner scanner = new Scanner(System.in)) {

            // ✅ Get user input
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            // ✅ Check if email already exists
            if (emailExists(email)) {
                System.out.println("❌ Email already exists! Try a different email.");
                return;
            }

            // ✅ Insert new user
            insertUser(name, email);

        } // ✅ Scanner is automatically closed here
    }

    // ✅ Method to insert a new user
    private static void insertUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("✅ " + rowsAffected + " row inserted successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Error inserting user: " + e.getMessage());
        }
    }

    // ✅ Method to check if email already exists
    private static boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Email exists
            }
        } catch (SQLException e) {
            System.out.println("❌ Error checking email: " + e.getMessage());
        }
        return false;
    }
}
