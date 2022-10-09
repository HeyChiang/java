package com.chiang.protocol.data;

import lombok.Data;

@Data
public class User {
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private String userName;
    private String password;
}
