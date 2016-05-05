package com.epicodus.instantmessage.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.instantmessage.models.Message;
import com.epicodus.instantmessage.ui.MessageDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 5/5/16.
 */
public class MessagePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Message> mMessages;

    public MessagePagerAdapter(FragmentManager fm, ArrayList<Message> messages) {
        super(fm);
        mMessages = messages;
    }
    @Override
    public Fragment getItem(int position) {
        return MessageDetailFragment.newInstance(mMessages.get(position));
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mMessages.get(position).getMessage();
    }
}
