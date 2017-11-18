package com.example.fifteam.tickettoride.views.inGameViews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fifteam.tickettoride.R;

/**
 * Created by USER on 11/15/2017.
 */

public class TurnChoiceDialogFragment extends DialogFragment {

    Button drawTrainCardsButton;
    Button drawDestinationCardsButton;
    Button claimRouteButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_turn_choice, null);
        builder.setView(dialogLayout);

        TurnChoiceDialogFragment.this.getDialog().setCanceledOnTouchOutside(false);

        drawTrainCardsButton = (Button) dialogLayout.findViewById(R.id.draw_train_cards_button);
        drawTrainCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TurnChoiceDialogFragment.this.getDialog().dismiss();
            }
        });

        drawDestinationCardsButton = (Button) dialogLayout.findViewById(R.id.draw_destination_cards_button);
        drawDestinationCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TurnChoiceDialogFragment.this.getDialog().dismiss();
            }
        });

        claimRouteButton = (Button) dialogLayout.findViewById(R.id.claim_route_button);
        claimRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TurnChoiceDialogFragment.this.getDialog().dismiss();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
