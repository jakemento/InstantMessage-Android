package com.epicodus.instantmessage.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.instantmessage.Constants;
import com.epicodus.instantmessage.R;
import com.epicodus.instantmessage.models.Message;
import com.firebase.client.Firebase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.messageTextView) TextView mMessageLabel;
    @Bind(R.id.authorTextView) TextView mAuthorLabel;
    @Bind(R.id.saveMessage) Button mSaveMessageButton;
    private SharedPreferences mSharedPreferences;

    private Message mMessage;

    public static MessageDetailFragment newInstance(Message message) {
        MessageDetailFragment messageDetailFragment = new MessageDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("message", Parcels.wrap(message));
        messageDetailFragment.setArguments(args);
        return messageDetailFragment;
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = Parcels.unwrap(getArguments().getParcelable("message"));
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_detail, container, false);
        ButterKnife.bind(this, view);
        mSaveMessageButton.setOnClickListener(this);
        mMessageLabel.setText(mMessage.getMessage());
        mAuthorLabel.setText(mMessage.getAuthor());
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveMessageButton) {
            String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
            Firebase userMessagesFirebaseRef = new Firebase(Constants.FIREBASE_URL_MESSAGES).child(userUid);
            Firebase pushRef = userMessagesFirebaseRef.push();
            String messagePushId = pushRef.getKey();
            mMessage.setPushId(messagePushId);
            userMessagesFirebaseRef.setValue(mMessage);
            Toast.makeText(getContext(),"Saved", Toast.LENGTH_SHORT).show();

//            Firebase ref = new Firebase(Constants.FIREBASE_URL_MESSAGES);
//            ref.push().setValue(mMessage);
//            Toast.makeText(getContext(),"saved",Toast.LENGTH_SHORT).show();
        }
    }
}
