package com.example.sfedymob;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String name;
    public User() {
        email = "";
        name = "Guest";
    }
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
