package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainCardsFragment extends Fragment {


    public TrainCardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_train_cards, container, false);
    }

}