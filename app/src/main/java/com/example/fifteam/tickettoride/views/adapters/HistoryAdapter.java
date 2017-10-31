package com.example.fifteam.tickettoride.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fifteam.tickettoride.R;

import java.util.List;

/**
 * Created by USER on 10/30/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Activity context;
    private List<String> historyList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout historyLayout;
        public ViewHolder(View v) {
            super(v);
            historyLayout = (LinearLayout) v.findViewById(R.id.history_layout);
        }
    }

    public HistoryAdapter(Activity context, List<String> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_view_item, parent, false);
        HistoryAdapter.ViewHolder vh = new HistoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final String history_item = historyList.get(position); //declared final so that listener below can use it

        TextView historyMessage = (TextView) holder.historyLayout.findViewById(R.id.history_message);
        historyMessage.setText(history_item);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

}