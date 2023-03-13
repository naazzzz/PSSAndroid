package com.example.myapplication;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

public class User extends SugarRecord {

    @Column(name = "id",unique = true,notNull = true)
    int id;

    @Column(name = "username",unique = true,notNull = true)
    String username;
    @Column(name = "pass",unique = false,notNull = true)
    String pass;
    @Column(name = "email",unique = false,notNull = true)
    String email;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }

    public User() {
    }

}
