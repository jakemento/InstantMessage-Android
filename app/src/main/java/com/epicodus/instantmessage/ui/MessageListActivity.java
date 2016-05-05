package com.epicodus.instantmessage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.instantmessage.R;
import com.epicodus.instantmessage.adapters.MessageListAdapter;
import com.epicodus.instantmessage.models.Message;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private MessageListAdapter mAdapter;

    public ArrayList<Message> mMessages = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        String author = intent.getStringExtra("author");

        getMessages(message, author);

    }

    private void getMessages(String message, String author) {
        mAdapter = new MessageListAdapter(getApplicationContext(), mMessages);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MessageListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

    }
}
