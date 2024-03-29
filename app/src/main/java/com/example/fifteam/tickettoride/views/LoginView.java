package com.example.fifteam.tickettoride.views;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fifteam.tickettoride.MainActivity;
import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.presenters.LoginPresenter;

public class LoginView extends Fragment {

    EditText usernameField;
    EditText passwordField;
    EditText hostField;
    Button loginButton;
    Button registerButton;
    LoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_login, container, false);

        usernameField = (EditText) v.findViewById(R.id.username_field);
        passwordField = (EditText) v.findViewById(R.id.password_field);
        hostField = (EditText) v.findViewById(R.id.login_host);

        loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String host = hostField.getText().toString();

                presenter.login(username, password, host);
            }
        });

        registerButton = (Button) v.findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String host = hostField.getText().toString();

                presenter.register(username, password, host);
            }
        });

        return v;
    }

    public void displayMessage(String message) {
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void switchToNextView() {
        ((MainActivity)getContext()).switchFragment(new GamesListView());
    }
}
