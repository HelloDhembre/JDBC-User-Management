package com.example.jdbc;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("🚀 Starting JDBC Project...");

        // ✅ Insert a new user into the database
        User newUser = new User(0, "Alice Johnson", "alice@example.com", 25, "9876543210", "123 Street, NY");
        UserDAO.insertUser(newUser);

        // ✅ Update an existing user
        UserDAO.updateUser(newUser.getId(), "Alice Smith", "alice.smith@example.com", 26, "9998887776", "456 Avenue, LA");

        // ✅ Retrieve all users from the database
        List<User> users = UserDAO.getAllUsers();
        System.out.println("📋 All Users:");
        for (User user : users) {
            System.out.println(user);
        }

        // ✅ Delete a user
        UserDAO.deleteUser(newUser.getId());

        System.out.println("✅ Exiting program without warnings.");

    }
}
