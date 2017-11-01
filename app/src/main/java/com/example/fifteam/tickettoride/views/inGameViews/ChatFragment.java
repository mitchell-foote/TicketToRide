package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.ChatPresenter;
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
    private EditText chatText;
    private Button sendChat;
    private ChatPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChatPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        chatRecyclerView = (RecyclerView) v.findViewById(R.id.chat_recycler_view);
        chatLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) chatLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        chatRecyclerView.setLayoutManager(chatLayoutManager);
        chatText = (EditText) v.findViewById(R.id.chat_entry_field);
        sendChat = (Button) v.findViewById(R.id.submit_chat_button);

        sendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatValue = chatText.getText().toString();

                presenter.sendChat(chatValue);
            }
        });

        List<ChatEntry> chatList = new ArrayList<>();

        //for testing purposes; this will need to be changed
        chatList.add(new ChatEntry("Player2", "Hey guys!"));
        chatList.add(new ChatEntry("Player1", "Hey Player2."));
        chatList.add(new ChatEntry("Player3", "I'm going to win!"));
        presenter = new ChatPresenter(this);

        chatAdapter = new ChatAdapter(getActivity(), presenter.getChats());
        chatRecyclerView.setAdapter(chatAdapter);

        return v;
    }

    public void updateAdapter(){
        chatAdapter.notifyDataSetChanged();
    }

}
