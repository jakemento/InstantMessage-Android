package com.epicodus.instantmessage.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.instantmessage.R;
import com.epicodus.instantmessage.models.Message;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageDetailFragment extends Fragment {
    @Bind(R.id.messageTextView) TextView mMessageLabel;
    @Bind(R.id.authorTextView) TextView mAuthorLabel;
    @Bind(R.id.saveMessage) Button mSaveMessageButton;

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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_detail, container, false);
        ButterKnife.bind(this, view);

        mMessageLabel.setText(mMessage.getMessage());
        mAuthorLabel.setText(mMessage.getAuthor());
        return view;
    }
}
