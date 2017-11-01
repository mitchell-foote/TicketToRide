package com.example.fifteam.tickettoride.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.views.GameMapView;
import com.example.fifteam.tickettoride.views.TempGameView;
import com.example.fifteam.tickettoride.views.inGameViews.ChatFragment;
import com.example.fifteam.tickettoride.views.inGameViews.DestinationCardsFragment;
import com.example.fifteam.tickettoride.views.inGameViews.HistoryFragment;
import com.example.fifteam.tickettoride.views.inGameViews.PlayerInfoFragment;
import com.example.fifteam.tickettoride.views.inGameViews.TrainCardsFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//TO BE USED LATER
public class GameView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageButton playerInfoButton;
    private ImageButton trainCardButton;
    private ImageButton destinationCardButton;
    private ImageButton chatButton;
    private ImageButton historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_game_fragment_holder);
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.game_map_container);
//        mapFragment.getMapAsync(this);

        TempGameView testFragment = new TempGameView();
        GameMapView mapFragment = new GameMapView();

        FragmentManager manager=getSupportFragmentManager();//create an instance of fragment manager
        FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction

        transaction.add(R.id.game_misc_container, testFragment, "frag_misc_tag");
        transaction.add(R.id.game_map_container, mapFragment, "frag_map_tag");
        transaction.commit();

        initializeButtons();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(40, -111);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void initializeButtons() {
        playerInfoButton = (ImageButton) findViewById(R.id.tab_button_player_info);
        playerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment playerInfo = new PlayerInfoFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.game_misc_container, playerInfo);
                ft.commit();
            }
        });

        trainCardButton = (ImageButton) findViewById(R.id.tab_button_train_cards);
        trainCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment trainCards = new TrainCardsFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.game_misc_container, trainCards);
                ft.commit();
            }
        });

        destinationCardButton = (ImageButton) findViewById(R.id.tab_button_destination_cards);
        destinationCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment destCards = new DestinationCardsFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.game_misc_container, destCards);
                ft.commit();
            }
        });

        chatButton = (ImageButton) findViewById(R.id.tab_button_chat);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment chat = new ChatFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.game_misc_container, chat);
                ft.commit();
            }
        });

        historyButton = (ImageButton) findViewById(R.id.tab_button_history);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment history = new HistoryFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.game_misc_container, history);
                ft.commit();
            }
        });
    }
}
