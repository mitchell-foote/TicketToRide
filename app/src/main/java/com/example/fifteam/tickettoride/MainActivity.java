package com.example.fifteam.tickettoride;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fifteam.tickettoride.interfaces.Toaster;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.GameMapView;
import com.example.fifteam.tickettoride.views.LoginView;

public class MainActivity extends AppCompatActivity implements Toaster
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchFragment(new LoginView());
    }

    public void switchFragment(Fragment new_fragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        ClientGamePresenterFacade.getInstance().passToaster(this);

        if (fragment == null) {
            fragment = new_fragment;
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
        else {
            fragment = new_fragment;
            fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    public void makeToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayMessage(String message) {
        makeToast(message);
    }
}
