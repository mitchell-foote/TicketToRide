package com.example.fifteam.tickettoride.testers;

import com.example.fifteam.tickettoride.serverCommunications.ServerProxy;

/**
 * Created by Mitchell Foote on 10/4/2017.
 */

public class ClientTester
{
    public static void main(String[] args){
        ServerProxy proxy = new ServerProxy();
        try
        {
            String auth = proxy.register("batman", "123456");
            System.out.println("WAIT!!");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
