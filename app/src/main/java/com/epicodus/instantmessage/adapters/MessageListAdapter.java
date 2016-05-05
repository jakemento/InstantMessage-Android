package com.epicodus.instantmessage.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.instantmessage.R;
import com.epicodus.instantmessage.models.Message;
import com.epicodus.instantmessage.ui.MessageDetailActivity;


import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {
    private ArrayList<com.epicodus.instantmessage.models.Message> mMessages = new ArrayList<>();
    private Context mContext;

    public MessageListAdapter(Context context, ArrayList<com.epicodus.instantmessage.models.Message> messages) {
        mContext = context;
        mMessages = messages;
    }

    @Override
    public MessageListAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
        MessageViewHolder viewHolder = new MessageViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MessageListAdapter.MessageViewHolder holder, int position) {
        holder.bindMessage(mMessages.get(position));
    }
    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.messageTextView) TextView mMessageTextView;
        @Bind(R.id.authorTextView) TextView mAuthorTextView;
        private Context mContext;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent  intent = new Intent(mContext, MessageDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("messages", Parcels.wrap(mMessages));
                    mContext.startActivity(intent);
                }
            });
        }
        public void bindMessage(Message message) {
            mMessageTextView.setText(message.getMessage());
            mAuthorTextView.setText(message.getAuthor());
        }
    }
}
