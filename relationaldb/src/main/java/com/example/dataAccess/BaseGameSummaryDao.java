package com.example.dataAccess;

import com.example.model.classes.login.BaseGameSummary;
import com.example.model.enums.SharedColor;
import com.example.persistance.pluginInterfaces.IBaseGameSummary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by USER on 12/9/2017.
 */

public class BaseGameSummaryDao {

    Connection connection;

    public BaseGameSummaryDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(BaseGameSummary gameSummary) {

        PreparedStatement stmt = null;
        try {

            String sql = "insert into BaseGameSummaries (id, owner_username, game_name, started, full_game_id, player1_name, player1_color, player2_name, player2_color, player3_name, player3_color, player4_name, player4_color, player5_name, player5_color) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameSummary.getId());
            stmt.setString(2, gameSummary.getOwner());
            stmt.setString(3, gameSummary.getGameName());
            stmt.setBoolean(4, gameSummary.isStarted());
            stmt.setString(5, gameSummary.getFullGameId());

            Map<String, SharedColor> playerMap = gameSummary.getPlayers();

            Set<Map.Entry<String, SharedColor>> playerSet = playerMap.entrySet();

            String player1_name = "";
            String player1_color = "";
            String player2_name = "";
            String player2_color = "";
            String player3_name = "";
            String player3_color = "";
            String player4_name = "";
            String player4_color = "";
            String player5_name = "";
            String player5_color = "";

            int counter = 0;
            for (Map.Entry<String, SharedColor> entry : playerSet) {
                counter++;
                switch(counter) {
                    case 1:
                        player1_name = entry.getKey();
                        player1_color = entry.getValue().toString();
                        break;
                    case 2:
                        player2_name = entry.getKey();
                        player2_color = entry.getValue().toString();
                        break;
                    case 3:
                        player3_name = entry.getKey();
                        player3_color = entry.getValue().toString();
                        break;
                    case 4:
                        player4_name = entry.getKey();
                        player4_color = entry.getValue().toString();
                        break;
                    case 5:
                        player5_name = entry.getKey();
                        player5_color = entry.getValue().toString();
                        break;
                    default:
                        break;
                }
            }

            stmt.setString(6, player1_name);
            stmt.setString(7, player1_color);
            stmt.setString(8, player2_name);
            stmt.setString(9, player2_color);
            stmt.setString(10, player3_name);
            stmt.setString(11, player3_color);
            stmt.setString(12, player4_name);
            stmt.setString(13, player4_color);
            stmt.setString(14, player5_name);
            stmt.setString(15, player5_color);

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

    public boolean remove(BaseGameSummary gameSummary) {

        PreparedStatement stmt = null;
        try {
            String sql = "delete from BaseGameSummaries where id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameSummary.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public List<BaseGameSummary> getBaseGames() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            String sql = "select * from BaseGameSummaries";
            stmt = connection.prepareStatement(sql);

            List<BaseGameSummary> summaryList = new ArrayList<>();

            rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String owner_username = rs.getString("owner_username");
                String game_name = rs.getString("game_name");
                boolean started = rs.getBoolean("started");
                String full_game_id = rs.getString("full_game_id");
                String player1_name = rs.getString("player1_name");
                String player1_color = rs.getString("player1_color");
                String player2_name = rs.getString("player2_name");
                String player2_color = rs.getString("player2_color");
                String player3_name = rs.getString("player3_name");
                String player3_color = rs.getString("player3_color");
                String player4_name = rs.getString("player4_name");
                String player4_color = rs.getString("player4_color");
                String player5_name = rs.getString("player5_name");
                String player5_color = rs.getString("player5_color");

                Map<String, SharedColor> playerMap = new HashMap<>();

                if (!player1_name.equals("")) {
                    playerMap.put(player1_name, getPlayerColorFromString(player1_color));
                }
                if (!player2_name.equals("")) {
                    playerMap.put(player2_name, getPlayerColorFromString(player2_color));
                }
                if (!player3_name.equals("")) {
                    playerMap.put(player3_name, getPlayerColorFromString(player3_color));
                }
                if (!player4_name.equals("")) {
                    playerMap.put(player4_name, getPlayerColorFromString(player4_color));
                }
                if (!player5_name.equals("")) {
                    playerMap.put(player5_name, getPlayerColorFromString(player5_color));
                }

                BaseGameSummary s = new BaseGameSummary(id, owner_username, game_name, playerMap);
                s.setStarted(started);
                s.setFullGameId(full_game_id);
                summaryList.add(s);
            }

            return summaryList;

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

    private SharedColor getPlayerColorFromString(String color_name) {

        SharedColor color = null;

        switch (color_name) {
            case "GREEN":
                color = SharedColor.GREEN;
                break;
            case "BLUE":
                color = SharedColor.BLUE;
                break;
            case "RED":
                color = SharedColor.RED;
                break;
            case "YELLOW":
                color = SharedColor.YELLOW;
                break;
            case "BLACK":
                color = SharedColor.BLACK;
                break;
            default:
                color = SharedColor.RAINBOW;
                break;
        }

        return color;
    }
}
