package com.example.dataAccess;

import com.example.communication.commands.CommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ClientCommandDeserializer;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/9/2017.
 */

public class ClientCommandDao {

    public static Gson gson = new Gson();
    Connection connection;

    public ClientCommandDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(IClientCommandData commandData, String gameId) {
        PreparedStatement stmt = null;
        try {

            String sql = "insert into ClientCommands (game_id, timestamp, command_data) values (?, ?, ?)";

            Long timestamp = System.currentTimeMillis();
            String command_data_string = gson.toJson(commandData);

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameId);
            stmt.setLong(2, timestamp);
            stmt.setString(3, command_data_string);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean remove(IClientCommandData commandData) {

        return false;
    }

    public List<IClientCommandData> getCommandList(String gameId) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IClientCommandData.class, new ClientCommandDeserializer<>());
        Gson special_gson = builder.create();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            String sql = "select * from ClientCommands where game_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameId);

            List<IClientCommandData> commandList = new ArrayList<>();

            rs = stmt.executeQuery();
            while (rs.next()) {
                String command_data_string = rs.getString("command_data");
                IClientCommandData command = special_gson.fromJson(command_data_string, IClientCommandData.class);
                commandList.add(command);
            }

            return commandList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
