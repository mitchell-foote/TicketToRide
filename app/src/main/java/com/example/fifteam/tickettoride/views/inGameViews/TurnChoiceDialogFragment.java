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
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.model.facadeEnums.TurnType;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.GamePresenter;

import java.nio.channels.ClosedByInterruptException;

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

        drawTrainCardsButton = (Button) dialogLayout.findViewById(R.id.draw_train_cards_button);
        drawTrainCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientGamePresenterFacade.getInstance().setTurnChoice(TurnType.DrawTrainCard);
                ClientGamePresenterFacade.getInstance().setTurnChoiceDialogCurrentlyDisplayed(false);
                TurnChoiceDialogFragment.this.getDialog().dismiss();
            }
        });

        drawDestinationCardsButton = (Button) dialogLayout.findViewById(R.id.draw_destination_cards_button);
        drawDestinationCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientGamePresenterFacade.getInstance().setTurnChoice(TurnType.DrawDestCard);
                ClientGamePresenterFacade.getInstance().setTurnChoiceDialogCurrentlyDisplayed(false);
                TurnChoiceDialogFragment.this.getDialog().dismiss();
            }
        });

        claimRouteButton = (Button) dialogLayout.findViewById(R.id.claim_route_button);
        claimRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ClientGamePresenterFacade.getInstance().canClaimAnyRoutes()) {
                    Toast.makeText(getActivity(), "You can't claim any routes!", Toast.LENGTH_SHORT).show();
                    claimRouteButton.setEnabled(false);
                } else {
                    ClientGamePresenterFacade.getInstance().setTurnChoice(TurnType.ClaimRoute);
                    ClientGamePresenterFacade.getInstance().setTurnChoiceDialogCurrentlyDisplayed(false);
                    TurnChoiceDialogFragment.this.getDialog().dismiss();
                }
            }
        });

        // Create the AlertDialog object and return it
        AlertDialog dialogToReturn = builder.create();
        dialogToReturn.setCancelable(false);
        return dialogToReturn;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
