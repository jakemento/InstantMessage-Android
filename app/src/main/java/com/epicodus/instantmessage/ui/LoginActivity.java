package com.epicodus.instantmessage.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.instantmessage.Constants;
import com.epicodus.instantmessage.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    private Firebase mFirebaseRef;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mPasswordLoginButton.setOnClickListener(this);

        mEmailEditText.setText("admin@gmail.com");
        mPasswordEditText.setText("admin");

    }

    @Override
    public void onClick(View view) {
        if(view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        if(view == mPasswordLoginButton) {
            loginWithPassword();
        }
    }

    public void loginWithPassword() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.equals("")) {
            mEmailEditText.setError("Please Enter your email");
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Please can't be blank");
        }

        mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(AuthData authData) {
                if (authData != null) {
                    String userId = authData.getUid();
                    mSharedPreferencesEditor.putString(Constants.KEY_UID, userId).apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    String userInfo = authData.toString();
                    Log.d(TAG, "Currently logged in: " + userInfo);
                }
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                switch (firebaseError.getCode()) {
                    case FirebaseError.INVALID_EMAIL:
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        mEmailEditText.setError("Please check that you entered your email correctly");
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        mEmailEditText.setError(firebaseError.getMessage());
                        break;
                    case FirebaseError.NETWORK_ERROR:
                        showErrorToast("There was a problem with the network connection");
                        break;
                    default:
                        showErrorToast(firebaseError.toString());
                }
            }

            private void showErrorToast(String message) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
