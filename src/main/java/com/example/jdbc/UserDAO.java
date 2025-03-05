package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ✅ Insert a new user into the database
    public static void insertUser(User user) {
        String sql = "INSERT INTO users (name, email, age, phone, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1)); // Get the auto-generated ID
                }
                System.out.println("✅ User inserted: " + user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Retrieve all users from the database
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    rs.getString("phone"),
                    rs.getString("address")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // ✅ Update user details
    public static void updateUser(int id, String newName, String newEmail, int newAge, String newPhone, String newAddress) {
        String sql = "UPDATE users SET name = ?, email = ?, age = ?, phone = ?, address = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setInt(3, newAge);
            stmt.setString(4, newPhone);
            stmt.setString(5, newAddress);
            stmt.setInt(6, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ User updated successfully!");
            } else {
                System.out.println("❌ User not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete a user by ID
    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("✅ User deleted successfully!");
            } else {
                System.out.println("❌ User not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
