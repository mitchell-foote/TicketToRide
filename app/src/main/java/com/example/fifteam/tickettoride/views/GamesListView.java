package com.example.fifteam.tickettoride.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fifteam.tickettoride.MainActivity;
import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.GamesListPresenter;
import com.example.model.classes.login.BaseGameSummary;

import java.util.List;

public class GamesListView extends Fragment {

    Button createGameButton;
    Button joinGameButton;
    Button logoutButton;
    ListView gamesList;
    GamesListPresenter presenter;

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
                Toast.makeText(getContext(), "Create Game Fragment coming soon(tm)", Toast.LENGTH_SHORT).show();
            }
        });

        joinGameButton = (Button) v.findViewById(R.id.joinGameButton);
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Joining games functionality coming soon(tm)", Toast.LENGTH_SHORT).show();
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

        return v;
    }

    public void updateList() {
        ((BaseAdapter) gamesList.getAdapter()).notifyDataSetChanged();
    }

    public void logoutViewChange() {
        ((MainActivity)getContext()).switchFragment(new LoginView());
    }

    public void displayMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

            return rowView;
        }
    }
}
