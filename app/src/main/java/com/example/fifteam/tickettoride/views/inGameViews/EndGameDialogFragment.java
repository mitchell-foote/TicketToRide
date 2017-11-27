package com.example.fifteam.tickettoride.views.inGameViews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fifteam.tickettoride.MainActivity;
import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.gameModel.PlayerGameSummaries.PlayerGameSummary;

import java.util.List;

/**
 * Created by kcwillmore on 11/20/17.
 */

public class EndGameDialogFragment extends DialogFragment {
    private static final int MAX_PLAYERS = 5;

    private TextView[] playerScores;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_endgame, null);
        builder.setView(dialogLayout);

        initializeTextviews(dialogLayout);
        setPlayerScores();

        return builder.create();
    }

    private void initializeTextviews(View v) {
        TextView firstPlaceTextView = (TextView) v.findViewById(R.id.endgame_1st);
        TextView secondPlaceTextView = (TextView) v.findViewById(R.id.endgame_2nd);
        TextView thirdPlaceTextView = (TextView) v.findViewById(R.id.endgame_3rd);
        TextView fourthPlaceTextView = (TextView) v.findViewById(R.id.endgame_4th);
        TextView fifthPlaceTextView = (TextView) v.findViewById(R.id.endgame_5th);

        playerScores = new TextView[]{firstPlaceTextView, secondPlaceTextView, thirdPlaceTextView, fourthPlaceTextView, fifthPlaceTextView};
    }

    private void setPlayerScores() {
        List<PlayerGameSummary> players = ClientGamePresenterFacade.getInstance().getAllPlayersList();

        for (int i = 0; i < players.size(); i++){
            PlayerGameSummary player = players.get(i);
            String name = player.getName();
            int score = player.getPoints();
            playerScores[i].setText(name + "'s final score: " + score);
        }
    }
}
