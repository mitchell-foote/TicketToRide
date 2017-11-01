package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.model.enums.SharedColor;

/**
 * Created by samks on 11/1/2017.
 */

public class DemoAsyncTask extends AsyncTask<Void,Integer,Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        for(int i = 0; i < 10 ; i++) {
            SystemClock.sleep(3500);
            publishProgress(i);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        ClientGamePresenterFacade facade = ClientGamePresenterFacade.getInstance();
        int i = values[0];
        switch (i){
            case 0:
                facade.claimRouteLocally("5", SharedColor.RED);
                break;
            case 1:
                facade.claimRouteLocally("10", SharedColor.BLUE);
                break;
            case 2:
                facade.claimRouteLocally("15", SharedColor.PURPLE);
                break;
            case 3:
                facade.claimRouteLocally("20", SharedColor.RED);
                break;
            case 4:
                facade.claimRouteLocally("25", SharedColor.GREEN);
                break;
            case 5:
                facade.claimRouteLocally("30", SharedColor.BLUE);
                break;
            case 6:
                facade.claimRouteLocally("35", SharedColor.PURPLE);
                break;
        }
    }
}
