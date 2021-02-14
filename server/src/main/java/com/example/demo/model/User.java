package com.example.demo.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String loginId;
    private String password;
    private String name;

    public User(int id, String loginId, String password, String name) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
