package com.example.fifteam.tickettoride.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fifteam.tickettoride.MainActivity;
import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.activities.GameView;
import com.example.fifteam.tickettoride.presenters.GameLobbyPresenter;

import java.util.ArrayList;
import java.util.List;

public class GameLobbyView extends Fragment {

    private static final String NO_PLAYER_MESSAGE = "Waiting for player to join";

    private List<TextView> players;
    private TextView player1TextView;
    private TextView player2TextView;
    private TextView player3TextView;
    private TextView player4TextView;
    private TextView player5TextView;
    private Button leaveGameButton;
    private Button startGameButton;
    GameLobbyPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GameLobbyPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_game_lobby, container, false);

        player1TextView = (TextView) v.findViewById(R.id.player1_textView);
        player2TextView = (TextView) v.findViewById(R.id.player2_textView);
        player3TextView = (TextView) v.findViewById(R.id.player3_textView);
        player4TextView = (TextView) v.findViewById(R.id.player4_textView);
        player5TextView = (TextView) v.findViewById(R.id.player5_textView);

        players = new ArrayList<>();
        players.add(player1TextView);
        players.add(player2TextView);
        players.add(player3TextView);
        players.add(player4TextView);
        players.add(player5TextView);

        setPlayerNames();

        /*
        leaveGameButton = (Button) v.findViewById(R.id.leaveGameButton);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.leaveGame();
            }
        });
        */

        startGameButton = (Button) v.findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "The Game!", Toast.LENGTH_SHORT).show();
                presenter.startGame();
            }
        });
        startGameButton.setEnabled(false); //starts disabled, the presenter will enable if it the game can start

        return v;
    }

    public void setPlayerNames() {
        int i = 0;
        for (String name : presenter.getPlayerNames()) {
            players.get(i++).setText(name);
        }
        while (i < 5) {
            players.get(i++).setText(NO_PLAYER_MESSAGE);
        }
    }

    public void displayMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void leaveGameViewChange() {
        ((MainActivity)getContext()).switchFragment(new GamesListView());
    }

    public void enableStart(boolean canStart) {
        startGameButton.setEnabled(canStart);
    }

    public void switchToGameView() {
        ((MainActivity) getContext()).switchFragment(new TempGameView());
    }

}
