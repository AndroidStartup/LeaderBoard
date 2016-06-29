package com.example.mahmoud.leaderboard;

import java.util.HashMap;

/**
 * Created by Mahmoud on 6/27/2016.
 */
public class Leader {
    String name;
    String email;
    HashMap<String, Object> users;

    public Leader(String name, String email, HashMap<String, Object> users) {
        this.name = name;
        this.email = email;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Object> getUsers() {
        return users;
    }
}
