package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.DestinationCardsPresenter;
import com.example.fifteam.tickettoride.views.adapters.DestinationAdapter;
import com.example.gameModel.classes.DestinationCard;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationCardsFragment extends Fragment {

    private DestinationCardsPresenter presenter;

    private RecyclerView myDestinations;
    private RecyclerView.Adapter myDestinationsAdapter;
    private RecyclerView.LayoutManager myDestinationsLayoutManager;

    private TextView myDestLabel;
    private TextView option0Text;
    private TextView option1Text;
    private TextView option2Text;

    private CheckBox option0CheckBox;
    private CheckBox option1CheckBox;
    private CheckBox option2CheckBox;

    private Button selectDestinationsButton;

//    private TextView hackText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DestinationCardsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_destination_cards, container, false);

        myDestinations = (RecyclerView) v.findViewById(R.id.your_destinations_recyclerView);
        myDestinationsLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) myDestinationsLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        myDestinations.setLayoutManager(myDestinationsLayoutManager);
        myDestinationsAdapter = new DestinationAdapter(getActivity(), presenter.getMyDestinations());
        myDestinations.setAdapter(myDestinationsAdapter);

        option0Text = (TextView) v.findViewById(R.id.destination_option_textView0);
        option1Text = (TextView) v.findViewById(R.id.destination_option_textView1);
        option2Text = (TextView) v.findViewById(R.id.destination_option_textView2);

        option0CheckBox = (CheckBox) v.findViewById(R.id.destination_option_checkBox0);
        option1CheckBox = (CheckBox) v.findViewById(R.id.destination_option_checkBox1);
        option2CheckBox = (CheckBox) v.findViewById(R.id.destination_option_checkBox2);

        myDestLabel = (TextView) v.findViewById(R.id.your_destinations_label);

        setCheckboxListeners();

        selectDestinationsButton = (Button) v.findViewById(R.id.destination_card_select_button);
        selectDestinationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectDestinations(option0CheckBox.isChecked(), option1CheckBox.isChecked(), option2CheckBox.isChecked());
            }
        });

        setIsSelectingDestinations(false);
        presenter.checkForDestinations();
        myDestLabel.setText("Your Destinations: (" + presenter.getMyDestinations().size() + ")");

//        hackText = (TextView) v.findViewById(R.id.destination_hack);
//        setHackText(presenter.getMyDestinations());

        return v;
    }

//    private void setHackText(List<DestinationCard> myDestinations) {
//        StringBuilder sb = new StringBuilder();
//        for (DestinationCard dest :
//                myDestinations) {
//            sb.append(dest.toString());
//            sb.append(" ");
//        }
//        hackText.setText(sb.toString());
//    }

    private void setCheckboxListeners() {
        option0CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectDestinationsButton.setEnabled(enoughDestinationsSelected());
            }
        });

        option1CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectDestinationsButton.setEnabled(enoughDestinationsSelected());
            }
        });

        option2CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectDestinationsButton.setEnabled(enoughDestinationsSelected());
            }
        });
    }

    public void setIsSelectingDestinations(boolean enable) {
        option0CheckBox.setEnabled(enable);
        option1CheckBox.setEnabled(enable);
        option2CheckBox.setEnabled(enable);

        if (enable) {
            selectDestinationsButton.setEnabled(numberDestinationsSelected() >= 2);
        } else {
            setDestinationText("X", "X", "X");
            selectDestinationsButton.setEnabled(false);
        }
    }

    public int numberDestinationsSelected() {
        int count = 0;
        if (option0CheckBox.isChecked()) count++;
        if (option1CheckBox.isChecked()) count++;
        if (option2CheckBox.isChecked()) count++;
        return count;
    }

    public boolean enoughDestinationsSelected() {
        return numberDestinationsSelected() >= 2;
    }

    public void setDestinationText(String destination0, String destination1, String destination2) {
        option0Text.setText(destination0);
        option1Text.setText(destination1);
        option2Text.setText(destination2);
    }

    public void updateAdapter() {
        myDestinationsAdapter = new DestinationAdapter(getActivity(), presenter.getMyDestinations());
        myDestLabel.setText("Your Destinations: (" + myDestinationsAdapter.getItemCount() + ")");
//        myDestLabel.setText("Your Destinations: (" + presenter.getMyDestinations().size() + ")");
//        setHackText(presenter.getMyDestinations());
    }
}
