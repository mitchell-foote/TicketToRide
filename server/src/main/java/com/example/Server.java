package com.example;

import com.example.communication.PathHolder;
import com.example.handler.CreateJoinLeaveCommandHandler;
import com.example.handler.GetGamesHandler;
import com.example.handler.LoginHandler;
import com.example.handler.RegisterHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private HttpServer mServer;
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private void run(String portNumber){
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
        System.out.println("Starting Server");
        mServer.start();
    }
    public static void main(String[] args) {
        String portNumber;
        try{
            portNumber = args[0];
        }
        catch(Exception e)
        {

        }
        portNumber = PathHolder.getPort();
        new Server().run(portNumber);
    }
}
