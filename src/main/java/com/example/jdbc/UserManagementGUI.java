package com.example.jdbc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserManagementGUI extends JFrame {
    private JTextField nameField, emailField, ageField, phoneField, addressField;
    private JTextArea userListArea;
    
    public UserManagementGUI() {
        setTitle("User Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ✅ Top Panel: Form for inserting/updating users
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new AddUserListener());
        formPanel.add(addButton);

        JButton refreshButton = new JButton("Refresh Users");
        refreshButton.addActionListener(e -> displayUsers());
        formPanel.add(refreshButton);

        add(formPanel, BorderLayout.NORTH);

        // ✅ Middle Panel: Display all users
        userListArea = new JTextArea();
        userListArea.setEditable(false);
        add(new JScrollPane(userListArea), BorderLayout.CENTER);

        // ✅ Bottom Panel: Delete User
        JPanel deletePanel = new JPanel();
        JTextField deleteField = new JTextField(5);
        JButton deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(e -> {
            int userId = Integer.parseInt(deleteField.getText());
            UserDAO.deleteUser(userId);
            displayUsers();
        });
        deletePanel.add(new JLabel("User ID:"));
        deletePanel.add(deleteField);
        deletePanel.add(deleteButton);
        add(deletePanel, BorderLayout.SOUTH);

        // ✅ Load existing users
        displayUsers();
    }

    // ✅ Fetch and Display Users
    private void displayUsers() {
        List<User> users = UserDAO.getAllUsers();
        userListArea.setText("ID | Name | Email | Age | Phone | Address\n");
        userListArea.append("-------------------------------------------\n");
        for (User user : users) {
            userListArea.append(user.getId() + " | " + user.getName() + " | " +
                    user.getEmail() + " | " + user.getAge() + " | " + 
                    user.getPhone() + " | " + user.getAddress() + "\n");
        }
    }

    // ✅ Action Listener for Adding Users
    private class AddUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText());
            String phone = phoneField.getText();
            String address = addressField.getText();

            User newUser = new User(0, name, email, age, phone, address);
            UserDAO.insertUser(newUser);

            // ✅ Refresh User List
            displayUsers();
        }
    }

    // ✅ Launch GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserManagementGUI().setVisible(true);
        });
    }
}
