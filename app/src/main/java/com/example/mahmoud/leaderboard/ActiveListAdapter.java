package com.example.mahmoud.leaderboard;

import android.app.Activity;
import java.util.Date;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;


/**
 * Created by Mahmoud on 6/22/2016.
 */
public class ActiveListAdapter extends FirebaseListAdapter<ShoppingList> {

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    public ActiveListAdapter(Activity activity, Class<ShoppingList> modelClass, int modelLayout,
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
    protected void populateView(View view, ShoppingList list) {

        /**
         * Grab the needed Textivews and strings
         */
        list.getOwner();
//        Constants.user;
        if (!Constants.user.equals("")) {
            if (list.getOwner().equals(Constants.user)) {
                TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
                TextView timeText = (TextView) view.findViewById(R.id.text_view_edit_time);
                TextView pointText = (TextView) view.findViewById(R.id.points);
                TextView status = (TextView) view.findViewById(R.id.status);

        /* Set the list name and owner */
                textViewListName.setText(list.getselectedActivity());
                timeText.setText(Constants.SIMPLE_DATE_FORMAT.format(
                        new Date(list.getTimestampLastChangedLong())));
                pointText.setText(list.getPoint());
                status.setText(list.getStatus());
            }
        }

    }
}