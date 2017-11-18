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

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
