package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.PlayerInfoPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerInfoFragment extends Fragment {

    private PlayerInfoPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PlayerInfoPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_info, container, false);
    }

}
