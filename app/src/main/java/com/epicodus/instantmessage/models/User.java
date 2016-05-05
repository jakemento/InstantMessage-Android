package com.epicodus.instantmessage.models;


public class User {
    private String name;
    private String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;

    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
}
