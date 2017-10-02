package com.example.fifteam.tickettoride;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fifteam.tickettoride.views.LoginView;

public class MainActivity extends AppCompatActivity
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
}
