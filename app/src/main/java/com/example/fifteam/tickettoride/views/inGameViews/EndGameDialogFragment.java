package com.example.fifteam.tickettoride.views.inGameViews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView[] winnerStars;

    private TextView longestRoute;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_endgame, null);
        builder.setView(dialogLayout);

        initializeTextViews(dialogLayout);
        initializeImageViews(dialogLayout);
        longestRoute = (TextView) dialogLayout.findViewById(R.id.endgame_longestRoute);

        setPlayerScores();

        return builder.create();
    }

    private void initializeTextViews(View v) {
        TextView firstPlaceTextView = (TextView) v.findViewById(R.id.endgame_1st);
        TextView secondPlaceTextView = (TextView) v.findViewById(R.id.endgame_2nd);
        TextView thirdPlaceTextView = (TextView) v.findViewById(R.id.endgame_3rd);
        TextView fourthPlaceTextView = (TextView) v.findViewById(R.id.endgame_4th);
        TextView fifthPlaceTextView = (TextView) v.findViewById(R.id.endgame_5th);

        playerScores = new TextView[]{firstPlaceTextView, secondPlaceTextView, thirdPlaceTextView, fourthPlaceTextView, fifthPlaceTextView};
    }

    private void initializeImageViews(View v) {
        ImageView star1 = (ImageView) v.findViewById(R.id.endgame_star1);
        ImageView star2 = (ImageView) v.findViewById(R.id.endgame_star2);
        ImageView star3 = (ImageView) v.findViewById(R.id.endgame_star3);
        ImageView star4 = (ImageView) v.findViewById(R.id.endgame_star4);
        ImageView star5 = (ImageView) v.findViewById(R.id.endgame_star5);

        winnerStars = new ImageView[]{star1, star2, star3, star4, star5};

        for (ImageView star : winnerStars) {
            star.setVisibility(View.INVISIBLE);
        }
    }

    private void setPlayerScores() {
        List<PlayerGameSummary> players = ClientGamePresenterFacade.getInstance().getAllPlayersList();

        int bestscore = players.get(0).getPoints();

        for (int i = 0; i < players.size(); i++){
            PlayerGameSummary player = players.get(i);
            String name = player.getName();
            int score = player.getPoints();

            if (score == bestscore) {
                winnerStars[i].setVisibility(View.VISIBLE);
            } else if (score > bestscore) {
                for (int j = 0; j < i; j++) {
                    winnerStars[j].setVisibility(View.INVISIBLE);
                }
                winnerStars[i].setVisibility(View.VISIBLE);
                bestscore = score;
            } else {
                winnerStars[i].setVisibility(View.INVISIBLE);
            }

            playerScores[i].setText(name + "'s final score: " + score);
        }

        String longestRouteUser = ClientGamePresenterFacade.getInstance().getLongestRouteOwner();
        if (longestRouteUser != null) {
            longestRoute.append(longestRouteUser);
        }
    }
}
