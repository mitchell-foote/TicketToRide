package com.example.fifteam.tickettoride.views.inGameViews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.TrainCardsPresenter;
import com.example.model.enums.SharedColor;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainCardsFragment extends Fragment {
    private static final int NUM_OF_FACE_UP_CARDS = 5;

    private TrainCardsPresenter presenter;

    private ImageView[] faceUpTrainCards;
    private ImageView trainCard0;
    private ImageView trainCard1;
    private ImageView trainCard2;
    private ImageView trainCard3;
    private ImageView trainCard4;
    private ImageView trainCardDeck;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TrainCardsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_train_cards, container, false);

        initializeCards(v);
        setCardListeners();
        updateCardImages(presenter.getCardColors());
        return v;
    }

    private void initializeCards(View v) {
        trainCard0 = (ImageView) v.findViewById(R.id.trainCard0);
        trainCard1 = (ImageView) v.findViewById(R.id.trainCard1);
        trainCard2 = (ImageView) v.findViewById(R.id.trainCard2);
        trainCard3 = (ImageView) v.findViewById(R.id.trainCard3);
        trainCard4 = (ImageView) v.findViewById(R.id.trainCard4);

        faceUpTrainCards = new ImageView[]{trainCard0, trainCard1, trainCard2, trainCard3, trainCard4};

        trainCardDeck = (ImageView) v.findViewById(R.id.trainCard_deck);
    }

    private void setCardListeners() {

        for (int i = 0; i < NUM_OF_FACE_UP_CARDS; i++) {
            final String toastText = "Can't draw card " + i + " yet!";
            final int pos = i;
            faceUpTrainCards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //this is for demo purposes
                    faceUpTrainCards[pos].setImageResource(colorToResource(SharedColor.RAINBOW));
                    //Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
                }
            });
        }

        trainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Can't draw card from deck yet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //precondition: the size of sharedColor is equal to the number of face up cards (5)
    public void updateCardImages(SharedColor[] colors) {

        for (int i = 0; i < NUM_OF_FACE_UP_CARDS; i++) {
            int cardResource = colorToResource(colors[i]);
            faceUpTrainCards[i].setImageResource(cardResource);
        }
    }

    private int colorToResource(SharedColor color) {
        switch (color) {
            case RAINBOW:
                return R.drawable.card_locomotive;
            case RED:
                return R.drawable.card_wagon_red;
            case ORANGE:
                return R.drawable.card_wagon_orange;
            case YELLOW:
                return R.drawable.card_wagon_yellow;
            case GREEN:
                return R.drawable.card_wagon_green;
            case BLUE:
                return R.drawable.card_wagon_blue;
            case PURPLE:
                return R.drawable.card_wagon_purple;
            case BLACK:
                return R.drawable.card_wagon_black;
            case WHITE:
                return R.drawable.card_wagon_white;
            default:
                return R.drawable.card_train_back;
        }
    }
}
