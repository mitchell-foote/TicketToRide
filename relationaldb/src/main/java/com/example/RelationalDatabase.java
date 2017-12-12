package com.example;

import com.example.dataAccess.BaseGameSummaryDao;
import com.example.dataAccess.ClientCommandDao;
import com.example.dataAccess.UserDao;
import com.example.gameCommunication.commands.interfaces.IClientCommandData;
import com.example.model.classes.login.BaseGameSummary;
import com.example.model.classes.users.User;
import com.example.persistance.pluginInterfaces.IPersistanceManagerObject;
import com.sun.security.ntlm.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class RelationalDatabase implements IPersistanceManagerObject {

    @Override
    public List<User> getUsers() {

        List<User> userList = null;

        try {

            Connection connection = getDatabaseConnection();

            UserDao ud = new UserDao(connection);
            userList = ud.getUsers();

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public List<BaseGameSummary> getBaseGames() {

        List<BaseGameSummary> summaryList = null;

        try {

            Connection connection = getDatabaseConnection();

            BaseGameSummaryDao bd = new BaseGameSummaryDao(connection);
            summaryList = bd.getBaseGames();

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return summaryList;
    }

    @Override
    public List<IClientCommandData> getCommandList(String gameId) {

        List<IClientCommandData> commandList = null;

        try {

            Connection connection = getDatabaseConnection();

            ClientCommandDao cd = new ClientCommandDao(connection);
            commandList = cd.getCommandList(gameId);

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandList;
    }

    @Override
    public String putUser(User user) {

        try {

            Connection connection = getDatabaseConnection();

            UserDao ud = new UserDao(connection);
            ud.add(user);

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Adding user failed!";
        }

        return null;
    }

    @Override
    public String putBaseGameSummary(BaseGameSummary summary) {

        try {

            Connection connection = getDatabaseConnection();

            BaseGameSummaryDao bd = new BaseGameSummaryDao(connection);
            bd.add(summary);

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Adding game summary failed!";
        }

        return null;
    }

    @Override
    public String putCommand(IClientCommandData data, String gameId) {

        try {

            Connection connection = getDatabaseConnection();

            ClientCommandDao cd = new ClientCommandDao(connection);
            cd.add(data, gameId);

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Adding client command failed!";
        }

        return null;
    }

    @Override
    public String removeBaseGame(BaseGameSummary summary) {

        try {

            Connection connection = getDatabaseConnection();

            BaseGameSummaryDao bd = new BaseGameSummaryDao(connection);
            bd.remove(summary);

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Removing game summary failed!";
        }

        return null;
    }

    @Override
    public Void wipeThemOut_AllOfThem() {

        try {

            Connection connection = getDatabaseConnection();

            String sql = "delete from Users";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            sql = "delete from BaseGameSummaries";
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            sql = "delete from ClientCommands";
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getDatabaseConnection() throws Exception {

        Connection connection = null;

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
        connection.setAutoCommit(false);

        return connection;
    }
}
