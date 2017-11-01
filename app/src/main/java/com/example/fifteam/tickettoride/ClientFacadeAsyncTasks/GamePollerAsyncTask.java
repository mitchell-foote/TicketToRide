package com.example.fifteam.tickettoride.ClientFacadeAsyncTasks;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.example.fifteam.tickettoride.model.ClientGameCommandFacade;
import com.example.fifteam.tickettoride.model.ClientGameModel;
import com.example.fifteam.tickettoride.serverCommunications.GameServerProxy;
import com.example.gameCommunication.commands.classes.commandRunners.ClientCommandRunner;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;

import java.util.List;

/**
 * Created by samks on 10/31/2017.
 */

public class GamePollerAsyncTask extends AsyncTask<Void,Void,List<CommandContainer>> {

    @Override
    protected List<CommandContainer> doInBackground(Void... voids) {
        GameServerProxy proxy = new GameServerProxy();
        ClientGameModel model = ClientGameModel.getInstance();
        SystemClock.sleep(500);
        List<CommandContainer> commands = proxy.getClientCommands(model.getLastExecutedHash(),
                model.getAuthToken(),model.getGameID());

        return commands;
    }

    @Override
    protected void onPostExecute(List<CommandContainer> commandContainers) {

        if(commandContainers != null) {
            ClientGameCommandFacade facade = ClientGameCommandFacade.getInstance();
            ClientCommandRunner runner = new ClientCommandRunner(facade);
            runner.runCommandsFromContainer(commandContainers);
        }
        new GamePollerAsyncTask().execute();
    }
}
