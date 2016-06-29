package com.example.mahmoud.leaderboard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;


public class Leaderboard extends Fragment {
    private ListView mListView;
    private LeaderboardListAdapter mLeaderboardListAdapter;

    /**
     * Create fragment and pass bundle with data as its' arguments
     */
    public static Leaderboard newInstance() {
        Leaderboard fragment = new Leaderboard();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Leaderboard() {
        /* Required empty public constructor*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        /**
         * Link layout elements from XML and setup the toolbar
         */
        initializeScreen(rootView);
/**
 * Create Firebase references
 */
        Firebase activeListsRef = new Firebase(Constants.FIREBASE_URL_USERS);

        /**
         * Add ValueEventListeners to Firebase references
         * to control get data and control behavior and visibility of elements
         */
        mLeaderboardListAdapter = new LeaderboardListAdapter(getActivity(), User.class,
                R.layout.single_active_list, activeListsRef);

        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mLeaderboardListAdapter);
        /**
         * Set interactive bits, such as click events/adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!Constants.userType.equals("user")) {
                    User selectedList = mLeaderboardListAdapter.getItem(position);
                    if (selectedList != null) {
                        String listId = mLeaderboardListAdapter.getRef(position).getKey();
                        Constants.user = listId;
                        MyActivites.create();
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_meals_list);
        View footer = getActivity().getLayoutInflater().inflate(R.layout.footer_empty, null);
        mListView.addFooterView(footer);
    }
}
