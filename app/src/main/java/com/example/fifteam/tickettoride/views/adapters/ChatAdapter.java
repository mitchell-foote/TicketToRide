package com.example.fifteam.tickettoride.views.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fifteam.tickettoride.R;
import com.example.gameModel.classes.ChatEntry;

import java.util.List;

/**
 * Created by USER on 10/30/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Activity context;
    private List<ChatEntry> chatList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout chatLayout;
        public ViewHolder(View v) {
            super(v);
            chatLayout = (LinearLayout) v.findViewById(R.id.chat_layout);
        }
    }

    public ChatAdapter(Activity context, List<ChatEntry> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_view_item, parent, false);
        ChatAdapter.ViewHolder vh = new ChatAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        final ChatEntry chat_item = chatList.get(position); //declared final so that listener below can use it

        TextView chatPlayerName = (TextView) holder.chatLayout.findViewById(R.id.chat_player_name);
        chatPlayerName.setText(chat_item.getName() + ": ");

        TextView historyMessage = (TextView) holder.chatLayout.findViewById(R.id.chat_message);
        historyMessage.setText(chat_item.getContent());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

}