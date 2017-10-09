package com.example.fifteam.tickettoride.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fifteam.tickettoride.MainActivity;
import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.GamesListPresenter;
import com.example.model.classes.login.BaseGameSummary;

import java.util.List;

public class GamesListView extends Fragment {

    private Button createGameButton;
    private Button joinGameButton;
    private Button logoutButton;
    private ListView gamesList;
    private GamesListPresenter presenter;
    private EditText gameNameEditText;
    private BaseGameSummary selectedGame;
    private int selectedGamePosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GamesListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_games_list, container, false);

        createGameButton = (Button) v.findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameName = gameNameEditText.getText().toString();
                presenter.createGame(gameName);
            }
        });
        createGameButton.setEnabled(false);

        gameNameEditText = (EditText) v.findViewById(R.id.gameNameEditText);
        gameNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String gameName = s.toString();
                createGameButton.setEnabled(gameName.length() > 0);
            }
        });

        joinGameButton = (Button) v.findViewById(R.id.joinGameButton);
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedGame != null) {
                    presenter.joinGame(selectedGame.getId());
                } else {
                    displayMessage("You must select a game first!");
                }
            }
        });

        logoutButton = (Button) v.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });

        gamesList = (ListView) v.findViewById(R.id.gamesListListView);
        GameListAdapter adapter = new GameListAdapter(getContext(), presenter.getGamesList());
        gamesList.setAdapter(adapter);

        gamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGame = (BaseGameSummary) parent.getItemAtPosition(position);
                selectedGamePosition = position;
                parent.setSelection(position);
                view.setBackgroundColor(0xFF00FF00);
                joinGameButton.setEnabled(!selectedGame.isFull());
            }
        });

        return v;
    }

    public void updateList() {
        ((BaseAdapter) gamesList.getAdapter()).notifyDataSetChanged();
    }

    public void logoutViewChange() {
        ((MainActivity)getContext()).switchFragment(new LoginView());
    }

    public void enterGameViewChange() {
        ((MainActivity) getContext()).switchFragment(new GameLobbyView());
    }

    public void displayMessage(String message) {
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private class GameListAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<BaseGameSummary> gamesData;

        public GameListAdapter(Context context, List<BaseGameSummary> items) {
            this.context = context;
            gamesData = items;
            inflater = (LayoutInflater) context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return gamesData.size();
        }

        @Override
        public Object getItem(int position) {
            return gamesData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = inflater.inflate(R.layout.games_list_item, parent, false);

            // Get game name element
            TextView nameTextView = (TextView) rowView.findViewById(R.id.gameListItem_name);

            // Get game creator element
            TextView creatorTextView = (TextView) rowView.findViewById(R.id.gameListItem_creator);

            // Get number of players element
            TextView playersTextView = (TextView) rowView.findViewById(R.id.gameListItem_players);

            // 1
            BaseGameSummary game = (BaseGameSummary) getItem(position);

            nameTextView.setText(game.getGameName());
            creatorTextView.setText("Created by: " + game.getOwner());
            playersTextView.setText(game.getPlayers().size() + "/5");

            if(selectedGame != null){
                if(selectedGame.getId().equals(game.getId())){
                    rowView.setBackgroundColor(0xFF00FF00);

                }
            }

            return rowView;
        }
    }
}
