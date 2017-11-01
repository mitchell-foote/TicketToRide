package com.example.fifteam.tickettoride.views.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fifteam.tickettoride.R;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;
import com.example.model.enums.SharedColor;

import java.util.List;

/**
 * Created by kcwillmore on 11/1/17.
 */

public class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoAdapter.ViewHolder> {

    private Activity context;
    private List<PlayerGameSummary> playerList;

    public PlayerInfoAdapter(Activity context, List<PlayerGameSummary> playerList) {
        this.context = context;
        this.playerList = playerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_info_list_item, parent, false);
        PlayerInfoAdapter.ViewHolder vh = new PlayerInfoAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlayerGameSummary player = playerList.get(position);

        final String playerName = player.getName();
        final String trainCards = String.valueOf(player.getTrainHandSize());
        final String destinations = String.valueOf(player.getNumDestinationCards());
        final String points = String.valueOf(player.getPoints());
        final String trainsRemaining = String.valueOf(player.getTrainsRemaining());

        TextView nameTextView = (TextView) holder.playerInfoLayout.findViewById(R.id.playerInfo_name);
        TextView cardsTextView = (TextView) holder.playerInfoLayout.findViewById(R.id.playerInfo_trainCard);
        TextView destTextView = (TextView) holder.playerInfoLayout.findViewById(R.id.playerInfo_destCards);
        TextView pointTextView = (TextView) holder.playerInfoLayout.findViewById(R.id.playerInfo_points);
        TextView trainTextView = (TextView) holder.playerInfoLayout.findViewById(R.id.playerInfo_trainsRemaining);

        nameTextView.setText(playerName);
        cardsTextView.setText(trainCards);
        destTextView.setText(destinations);
        pointTextView.setText(points);
        trainTextView.setText(trainsRemaining);

        int playerColor = SharedColor.sharedColorToHex(player.getColor());
        nameTextView.setBackgroundColor(playerColor);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout playerInfoLayout;
        public ViewHolder(View v) {
            super(v);
            playerInfoLayout = (LinearLayout) v.findViewById(R.id.playerInfo_item_layout);
        }
    }
}
