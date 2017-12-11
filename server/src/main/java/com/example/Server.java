package com.example;

import com.example.communication.PathHolder;
import com.example.handler.CreateJoinLeaveCommandHandler;
import com.example.handler.GetGamesHandler;
import com.example.handler.GetInGameCommandList;
import com.example.handler.InGameCommandHandler;
import com.example.handler.LoginHandler;
import com.example.handler.RegisterHandler;
import com.example.handler.StartGameHandler;
import com.example.model.ServerModel;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.IntBuffer;
import java.nio.file.Path;

public class Server {
    private HttpServer mServer;
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private void run(String portNumber, String plugin, int checkpointSize){
        ServerModel.instance().setDtb(new JsonPersistanceManagement());
        ServerModel.instance().setSaveFrequency(checkpointSize);
        ServerModel.instance().loadUseInfo();
        ServerModel.instance().loadGames();

        System.out.println("Initializing HTTP Server");
        try{
            mServer = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)),MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e){
            e.printStackTrace();
            return;
        }
        mServer.setExecutor(null);
        System.out.println("Creating contexts");
        mServer.createContext(PathHolder.getLoginURL(), new LoginHandler());
        mServer.createContext(PathHolder.getRegisterURL(), new RegisterHandler());
        mServer.createContext(PathHolder.getGamesURL(), new GetGamesHandler());
        mServer.createContext(PathHolder.getGameCommandURL(),new CreateJoinLeaveCommandHandler());
        mServer.createContext(PathHolder.getStartGameURL(), new StartGameHandler());
        mServer.createContext(PathHolder.getGameCommandListURL(), new GetInGameCommandList());
        mServer.createContext(PathHolder.getInGameCommandsURL(), new InGameCommandHandler());
        System.out.println("Starting Server");
        mServer.start();
    }
    public static void main(String[] args) {
        String portNumber = null;
        String plugin = null;
        String checkpointString = null;
        try {
            portNumber = args[0];
            plugin = args[1];
            checkpointString = args[2];
        }
        catch(Exception e) {

        }
        if (portNumber == null) {
            System.out.println("Port not provided. Setting to: " + PathHolder.getPort());
            portNumber = PathHolder.getPort();
        }
        if (plugin == null) {
            System.out.println("Database plugin not provided. Setting to default");
            plugin = "json";
        }

        int checkpointSize;
        try {
            checkpointSize = Integer.parseInt(checkpointString);
            if (checkpointSize < 1) {
                checkpointSize = 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Valid checkpoint size not provided. Setting to 1");
            checkpointSize = 1;
        }

        new Server().run(portNumber, plugin, checkpointSize);
    }
}
