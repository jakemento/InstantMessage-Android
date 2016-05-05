package com.epicodus.instantmessage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.instantmessage.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {
    @Bind(R.id.messageTextView) TextView mMessageTextView;
    @Bind(R.id.authorTextView) TextView mAuthorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        String author = intent.getStringExtra("author");
        mMessageTextView.setText(message);
        mAuthorTextView.setText(author);
    }
}
