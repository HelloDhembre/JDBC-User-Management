package com.example.jdbc;

public class User {
    private int id;
    private String name;
    private String email;
    private int age;
    private String phone;
    private String address;

    // ✅ Constructor with all fields
    public User(int id, String name, String email, int age, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    // ✅ Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // ✅ toString() method for easy printing
    @Override
    public String toString() {
        return "User { id=" + id + ", name='" + name + "', email='" + email + "', age=" + age +
               ", phone='" + phone + "', address='" + address + "' }";
    }
}
