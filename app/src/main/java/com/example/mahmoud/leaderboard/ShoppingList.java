package com.example.mahmoud.leaderboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by Mahmoud on 6/20/2016.
 */
public class ShoppingList {
    String selectedActivity;
    String owner;
    String url;
    String point;
    String status;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;

    public ShoppingList(String selectedActivity, String owner, String url, String point, String status, HashMap<String, Object> timestampCreated) {
        this.selectedActivity = selectedActivity;
        this.owner = owner;
        this.url = url;
        this.point = point;
        this.status = status;
        this.timestampCreated = timestampCreated;
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;
    }

    public ShoppingList() {

    }

    public String getselectedActivity() {
        return selectedActivity;
    }

    public String getOwner() {
        return owner;
    }

    public String geturl() {
        return url;
    }

    public String getPoint() {
        return point;
    }

    public String getStatus() {
        return status;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }


    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    @JsonIgnore
    public long getTimestampLastChangedLong() {

        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    @JsonIgnore
    public long getTimestampCreatedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }
}
