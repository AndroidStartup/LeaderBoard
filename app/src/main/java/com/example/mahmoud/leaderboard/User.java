package com.example.mahmoud.leaderboard;

import java.util.HashMap;

/**
 * Defines the data structure for User objects.
 */
public class User {
    private String name;
    private String email;
    private String point;
    private HashMap<String, Object> timestampJoined;

    /**
     * Required public constructor
     */
    public User() {
    }

    public User(String name, String email, String point, HashMap<String, Object> timestampJoined) {
        this.name = name;
        this.email = email;
        this.point = point;
        this.timestampJoined = timestampJoined;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPoint() {
        return point;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

}