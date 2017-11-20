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
import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.gameModel.classes.Route;
import com.example.model.enums.SharedColor;

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
                Route r = ClientGamePresenterFacade.getInstance().getCurrentlySelectedRoute();
                Toaster t = ClientGamePresenterFacade.getInstance().getGameMapToaster();
                ClientGamePresenterFacade instance = ClientGamePresenterFacade.getInstance();
                switch(color_name) {
                    case "Rainbow":
                        instance.claimRoute(r.getRouteId(), SharedColor.RAINBOW, t);
                        break;
                    case "Red":
                        instance.claimRoute(r.getRouteId(), SharedColor.RED, t);
                        break;
                    case "Orange":
                        instance.claimRoute(r.getRouteId(), SharedColor.ORANGE, t);
                        break;
                    case "Yellow":
                        instance.claimRoute(r.getRouteId(), SharedColor.YELLOW, t);
                        break;
                    case "Green":
                        instance.claimRoute(r.getRouteId(), SharedColor.GREEN, t);
                        break;
                    case "Blue":
                        instance.claimRoute(r.getRouteId(), SharedColor.BLUE, t);
                        break;
                    case "Purple":
                        instance.claimRoute(r.getRouteId(), SharedColor.PURPLE, t);
                        break;
                    case "Black":
                        instance.claimRoute(r.getRouteId(), SharedColor.BLACK, t);
                        break;
                    case "White":
                        instance.claimRoute(r.getRouteId(), SharedColor.WHITE, t);
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
