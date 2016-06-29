package com.example.mahmoud.leaderboard;

import com.firebase.client.Firebase;

/**
 * Created by Mahmoud on 6/19/2016.
 */
public class ShoppingListApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // other setup code
    }

}
