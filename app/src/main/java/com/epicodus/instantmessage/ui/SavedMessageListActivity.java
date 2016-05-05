package com.epicodus.instantmessage.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.instantmessage.Constants;
import com.epicodus.instantmessage.R;
import com.epicodus.instantmessage.adapters.FirebaseMessageListAdapter;
import com.epicodus.instantmessage.models.Message;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedMessageListActivity extends AppCompatActivity {
    private Query mQuery;
    private Firebase mFirebaseMessagesRef;
    private FirebaseMessageListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mFirebaseMessagesRef = new Firebase(Constants.FIREBASE_URL_MESSAGES);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);

        String message = mFirebaseMessagesRef.child(userUid).toString();
        mQuery = new Firebase(message);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseMessageListAdapter(mQuery, Message.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
