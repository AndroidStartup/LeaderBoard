package com.example.mahmoud.leaderboard;

import android.app.Activity;
import java.util.Date;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import java.util.Date;

/**
 * Created by Mahmoud on 6/25/2016.
 */
public class LeaderboardListAdapter  extends FirebaseListAdapter<User> {

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    public LeaderboardListAdapter(Activity activity, Class<User> modelClass, int modelLayout,
                             Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    /**
     * Protected method that populates the view attached to the adapter (list_view_active_lists)
     * with items inflated from single_active_list.xml
     * populateView also handles data changes and updates the listView accordingly
     */
    @Override
    protected void populateView(View view, User user) {

        /**
         * Grab the needed Textivews and strings
         */
        TextView textViewUserName = (TextView) view.findViewById(R.id.text_view_list_name);
        TextView pointText =(TextView) view.findViewById(R.id.points);

        /* Set the list name and owner */
        textViewUserName.setText(user.getName());
        pointText.setText(user.getPoint());

    }
}