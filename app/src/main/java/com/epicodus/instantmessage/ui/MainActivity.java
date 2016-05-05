package com.epicodus.instantmessage.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.instantmessage.Constants;
import com.epicodus.instantmessage.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase mSavedMessageRef;
    private ValueEventListener mSavedMessageRefListener;
    @Bind(R.id.messageEditText) EditText mMessageEditText;
    @Bind(R.id.authorEditText) EditText mAuthorEditText;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.savedMessagesButton) Button mSavedMessagesButton;




    private Firebase mFirebaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mSubmitButton.setOnClickListener(this);
        mSavedMessageRef = new Firebase(Constants.FIREBASE_URL_SEARCHED_MESSAGE);
        mSavedMessagesButton.setOnClickListener(this);

        mSavedMessageRefListener = mSavedMessageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mSavedMessageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String messages = dataSnapshot.getValue().toString();
                Log.d("Message Updated", messages);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            Intent intent = new Intent(MainActivity.this, MessageListActivity.class);
            String message = mMessageEditText.getText().toString();
            String author = mAuthorEditText.getText().toString();
            saveMessageToFirebase(message);
            intent.putExtra("message", message);
            intent.putExtra("author", author);
            startActivity(intent);
        }

        if (v == mSavedMessagesButton) {
            Intent intent = new Intent(MainActivity.this, SavedMessageListActivity.class);
            startActivity(intent);
        }
    }
            public void saveMessageToFirebase(String message) {
                Firebase savedMessageRef = new Firebase(Constants.FIREBASE_URL_SEARCHED_MESSAGE);
                savedMessageRef.push().setValue(message);

            }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    protected void logout() {
        mFirebaseRef.unauth();
        takeUserToLoginScreenOnUnAuth();
    }

    private void takeUserToLoginScreenOnUnAuth() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}


