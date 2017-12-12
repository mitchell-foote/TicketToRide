package com.example.dataAccess;

import com.example.model.classes.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/9/2017.
 */

public class UserDao {

    Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(User user) {

        PreparedStatement stmt = null;
        try {

            String sql = "insert into Users (name, password, auth_token) values (?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getAuthToken());

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

    public boolean remove(User user) {

        return false;
    }

    public List<User> getUsers() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            String sql = "select * from Users";
            stmt = connection.prepareStatement(sql);

            List<User> userList = new ArrayList<>();

            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String auth_token = rs.getString("auth_token");

                User u = new User(name, password, auth_token);
                userList.add(u);
            }

            return userList;

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
