package com.example.fifteam.tickettoride.views.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fifteam.tickettoride.R;
import com.example.gameModel.classes.DestinationCard;

import java.util.List;

/**
 * Created by kcwillmore on 11/1/17.
 */

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private Activity context;
    private List<DestinationCard> destinations;

    public DestinationAdapter(Activity context, List<DestinationCard> myDestinations) {
        this.context = context;
        destinations = myDestinations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.destination_list_item, parent, false);
        DestinationAdapter.ViewHolder vh = new DestinationAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DestinationCard destination = destinations.get(position);
        final String destinationString = destination.toString();

        TextView destinationTextView = (TextView) holder.destinationLayout.findViewById(R.id.owned_destination_textView);

        destinationTextView.setText(destinationString);
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout destinationLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            destinationLayout = (LinearLayout) itemView.findViewById(R.id.destinationOwned_item_layout);
        }
    }
}
