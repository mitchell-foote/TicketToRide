package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.views.adapters.ChatAdapter;
import com.example.gameModel.classes.ChatEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private RecyclerView chatRecyclerView;
    private RecyclerView.Adapter chatAdapter;
    private RecyclerView.LayoutManager chatLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        chatRecyclerView = (RecyclerView) v.findViewById(R.id.chat_recycler_view);
        chatLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) chatLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        chatRecyclerView.setLayoutManager(chatLayoutManager);

        List<ChatEntry> chatList = new ArrayList<>();

        //for testing purposes; this will need to be changed
        chatList.add(new ChatEntry("Player2", "Hey guys!"));
        chatList.add(new ChatEntry("Player1", "Hey Player2."));
        chatList.add(new ChatEntry("Player3", "I'm going to win!"));

        chatAdapter = new ChatAdapter(getActivity(), chatList);
        chatRecyclerView.setAdapter(chatAdapter);

        return v;
    }

}
