package com.example.mahmoud.leaderboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActiveListDetailsActivity extends AppCompatActivity {

    private String mListId;
    static ShoppingList shoppingList;
    private ListView mListView;
    ArrayList<String> status;
    private ActiveListItemAdapter mActiveListItemAdapter;
    String points;
    User user;
    Firebase UserRef;
    boolean created;
    String statusArray[] = {"append", "approved", "rejected>"};
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_list_details);

        created = true;

        mListView = (ListView) findViewById(R.id.coment_list);
        status = new ArrayList<String>(Arrays.asList(statusArray));
        final TextView userName = (TextView) findViewById(R.id.username);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        final EditText comment = (EditText) findViewById(R.id.comment_text);
        Button url = (Button) findViewById(R.id.url_button);
        if (Constants.userType.equals("user")) {
            spinner.setEnabled(false);
        }
        spinner.setSelection(status.indexOf(shoppingList.getStatus()));
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(shoppingList.geturl()));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(ActiveListDetailsActivity.this, "Error in URL", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /* Get the push ID from the extra passed by ShoppingListFragment */
        Intent intent = this.getIntent();
        mListId = intent.getStringExtra(Constants.KEY_LIST_ID);
        if (mListId == null) {
            /* No point in continuing without a valid ID. */
            finish();
            return;
        }
        String userId;
        userId = Constants.user;
        UserRef = new Firebase(Constants.FIREBASE_URL_USERS).
                child(userId);
        ValueEventListener mUserRefListener = UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                if (user != null) {
                    points = user.getPoint();
                    userName.setText(user.getName());
                    if(Constants.userType.equals("leader")){
                        user_name = "Leader";
                    }else
                        user_name = user.getName();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });


//        mActiveListRef = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS).child(Constants.user).child(mListId);
        Firebase listItemsRef = new Firebase(Constants.FIREBASE_URL_SHOPPING_LIST_ITEMS).child(mListId);
        mActiveListItemAdapter = new ActiveListItemAdapter(this, ShoppingListItem.class,
                R.layout.single_active_list_item, listItemsRef, mListId);
        /* Create ActiveListItemAdapter and set to listView */
        mListView.setAdapter(mActiveListItemAdapter);

        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!comment.getText().toString().trim().equals("")) {

                    Firebase itemsRef = new Firebase(Constants.FIREBASE_URL_SHOPPING_LIST_ITEMS).child(mListId);
/* Make a map for the item you are adding */
                    HashMap<String, Object> updatedItemToAddMap = new HashMap<String, Object>();

            /* Save push() to maintain same random Id */
                    Firebase newRef = itemsRef.push();
                    String itemId = newRef.getKey();

            /* Make a POJO for the item and immediately turn it into a HashMap */
                    ShoppingListItem itemToAddObject = new ShoppingListItem(comment.getText().toString().trim(), user_name);
                    HashMap<String, Object> itemToAdd =
                            (HashMap<String, Object>) new ObjectMapper().convertValue(itemToAddObject, Map.class);

            /* Add the item to the update map*/
                    updatedItemToAddMap.put(itemId, itemToAdd);
                    itemsRef.updateChildren(updatedItemToAddMap);
                    comment.setText("");
                }

            }
        });

    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {
            if (created == false) {
                String statusNew = parent.getItemAtPosition(pos).toString();
                String oldStatus = shoppingList.getStatus();
                if (oldStatus.equals("pending")) {
                    if (!statusNew.equals(oldStatus)) {
                        Firebase ListRef = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS).child(Constants.user).
                                child(mListId);

                    /* Make a Hashmap for the specific properties you are changing */
                        HashMap<String, Object> updatedProperties = new HashMap<String, Object>();
                        updatedProperties.put(Constants.FIREBASE_PROPERTY_LIST_STATUS, statusNew);
                    /* Do the update */
                        ListRef.updateChildren(updatedProperties);
                        if ((statusNew.equals("approved"))) {

                            points = (Integer.parseInt(shoppingList.getPoint()) + Integer.parseInt(points)) + "";
                    /* Make a Hashmap for the specific properties you are changing */
                            HashMap<String, Object> updatedpoints = new HashMap<String, Object>();
                            updatedpoints.put(Constants.FIREBASE_PROPERTY_USER_POINT, points);
                    /* Do the update */
                            UserRef.updateChildren(updatedpoints);
                        }
                    }
                    Toast.makeText(parent.getContext(),
                            "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(parent.getContext(),
                            "You Changed It Before!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else created = false;

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

    }
}
