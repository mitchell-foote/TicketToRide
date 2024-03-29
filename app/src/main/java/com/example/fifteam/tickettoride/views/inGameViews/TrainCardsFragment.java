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
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
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

    /**
     * Basic fragment creation, sets presenter for this view
     *
     * @param savedInstanceState
     *
     * @pre none
     *
     * @post the train cards view has a presenter connected to it
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TrainCardsPresenter(this);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     *
     * @pre the xml file for R.layout.fragment_train_cards has imageViews for the 5 train card options and the deck
     *
     * @post the view is inflated with the contents of the xml, the imageViews have onClickListeners
     */
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

    /**
     * @param v the train cards view
     *
     * @pre the view has 6 imageViews named trainCard0-4 and trainCard_deck
     *
     * @post the train card imageViews are remembered and usable by other functions of this view class
     */
    private void initializeCards(View v) {
        trainCard0 = (ImageView) v.findViewById(R.id.trainCard0);
        trainCard1 = (ImageView) v.findViewById(R.id.trainCard1);
        trainCard2 = (ImageView) v.findViewById(R.id.trainCard2);
        trainCard3 = (ImageView) v.findViewById(R.id.trainCard3);
        trainCard4 = (ImageView) v.findViewById(R.id.trainCard4);

        faceUpTrainCards = new ImageView[]{trainCard0, trainCard1, trainCard2, trainCard3, trainCard4};

        trainCardDeck = (ImageView) v.findViewById(R.id.trainCard_deck);
    }

    /**
     * @pre the intializeCards function of this view has already been called
     *
     * @post each of the train card image views have on click listeners attached to them, and they pop a toast and change image when clicked on
     */
    private void setCardListeners() {

        for (int i = 0; i < NUM_OF_FACE_UP_CARDS; i++) {
            final String toastText = "Can't draw card " + i + " yet!";
            final int pos = i;
            faceUpTrainCards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ClientGamePresenterFacade.getInstance().isWaitingForCardUpdate()) {
                        presenter.drawFaceUpCard(pos);
                        ClientGamePresenterFacade.getInstance().setWaitingForCardUpdate(true);
                    }
                }
            });
        }

        trainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCardFromDeck();
            }
        });
    }

    /**
     * @param colors an array of SharedColors equivalent to the five face-up cards that may be drawn
     *
     * @pre the size of the colors array is exactly 5, the number of face up train cards in the game
     *
     * @post the images in the view are set to correspond to the five colors in the array
     */
    public void updateCardImages(SharedColor[] colors) {

        for (int i = 0; i < NUM_OF_FACE_UP_CARDS; i++) {
            int cardResource = colorToResource(colors[i]);
            faceUpTrainCards[i].setImageResource(cardResource);
        }
        ClientGamePresenterFacade.getInstance().setWaitingForCardUpdate(false);
    }

    /**
     * @param color a SharedColor to correspond to a train card image
     *
     * @pre none
     *
     * @post returns the id of the image resource that corresponds to that color of card
     */
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

    public void displayMessage(String message) {
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
