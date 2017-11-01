package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.PlayerInfoPresenter;
import com.example.fifteam.tickettoride.views.adapters.PlayerInfoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerInfoFragment extends Fragment {

    private PlayerInfoPresenter presenter;

    private RecyclerView playerInfoList;
    private RecyclerView.Adapter playerInfoAdapter;
    private RecyclerView.LayoutManager playerInfoLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PlayerInfoPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_info, container, false);

        playerInfoList = (RecyclerView) v.findViewById(R.id.player_info_recyclerView);
        playerInfoLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) playerInfoLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        playerInfoList.setLayoutManager(playerInfoLayoutManager);

        playerInfoAdapter = new PlayerInfoAdapter(getActivity(), presenter.getPlayerInfo());
        playerInfoList.setAdapter(playerInfoAdapter);

        return v;
    }

    public void updateAdapter() {
        playerInfoAdapter.notifyDataSetChanged();
    }
}
