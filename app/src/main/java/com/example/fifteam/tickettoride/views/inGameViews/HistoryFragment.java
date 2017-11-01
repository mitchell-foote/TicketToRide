package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.HistoryPresenter;
import com.example.fifteam.tickettoride.views.adapters.ChatAdapter;
import com.example.fifteam.tickettoride.views.adapters.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView historyRecyclerView;
    private RecyclerView.Adapter historyAdapter;
    private RecyclerView.LayoutManager historyLayoutManager;
    private HistoryPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        historyRecyclerView = (RecyclerView) v.findViewById(R.id.history_recycler_view);
        historyLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) historyLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        historyRecyclerView.setLayoutManager(historyLayoutManager);

        List<String> historyList = new ArrayList<>();

        //for testing purposes; this will need to be changed
        historyList.add("Player 1 drew a destination card.");
        historyList.add("Player 2 claimed a route from San Fransisco to Los Angeles.");
        historyList.add("Player 3 drew two train cards.");
        presenter = new HistoryPresenter(this);

        historyAdapter = new HistoryAdapter(getActivity(), presenter.getHistoryEntries());
        historyRecyclerView.setAdapter(historyAdapter);

        return v;
    }

    public void updateAdaptor(){
        historyAdapter.notifyDataSetChanged();
    }

}
