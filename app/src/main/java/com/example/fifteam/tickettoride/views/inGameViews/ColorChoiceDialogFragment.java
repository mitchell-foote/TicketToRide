package com.example.fifteam.tickettoride.views.inGameViews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fifteam.tickettoride.R;

import org.w3c.dom.Text;

/**
 * Created by USER on 11/17/2017.
 */

public class ColorChoiceDialogFragment extends DialogFragment {

    TextView routeDetailView;
    TextView routeLengthView;
    TextView routeColorView;
    Spinner colorSelectionSpinner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_color_choice, null);
        builder.setView(dialogLayout);

        routeDetailView = (TextView) dialogLayout.findViewById(R.id.route_detail_view);
        routeLengthView = (TextView) dialogLayout.findViewById(R.id.route_length_view);
        routeColorView = (TextView) dialogLayout.findViewById(R.id.route_color_view);
        colorSelectionSpinner = (Spinner) dialogLayout.findViewById(R.id.color_selection_spinner);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String color_name = (String) colorSelectionSpinner.getSelectedItem();
                switch(color_name) {
                    case "Rainbow":
                        makeToast("You selected Rainbow.");
                        break;
                    case "Red":
                        makeToast("You selected Red.");
                        break;
                    case "Orange":
                        makeToast("You selected Orange.");
                        break;
                    case "Yellow":
                        makeToast("You selected Yellow.");
                        break;
                    case "Green":
                        makeToast("You selected Green.");
                        break;
                    case "Blue":
                        makeToast("You selected Blue.");
                        break;
                    case "Purple":
                        makeToast("You selected Purple.");
                        break;
                    case "Black":
                        makeToast("You selected Black.");
                        break;
                    case "White":
                        makeToast("You selected White.");
                        break;
                }
                ColorChoiceDialogFragment.this.getDialog().dismiss();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
