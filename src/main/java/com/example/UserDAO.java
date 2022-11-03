package com.example;

import java.security.InvalidParameterException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connector = DBConnector.getConnection();

    public void create(User user) {

        if (read(user.getLogin()) != null)
            throw new InvalidParameterException("User already exists");

        try {
            PreparedStatement statement = connector.prepareStatement("INSERT INTO users(login, password) VALUES (?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User read(String login) {

        try {

            PreparedStatement statement = connector.prepareStatement("SELECT * FROM users WHERE login = ?");
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                return new User(id, login, password);
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    public List<User> readAll() {

        List<User> users = new ArrayList<>();

        try {

            Statement statement = connector.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                User user = new User(id, login, password);
                users.add(user);

            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;

    }

    public void update(User user) {

        if (read(user.getLogin()) == null)
            throw new InvalidParameterException("User is not found");

        try {
            PreparedStatement statement = connector.prepareStatement("UPDATE users SET password = ? WHERE login = ?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(User user) {

        if (read(user.getLogin()) == null)
            throw new InvalidParameterException("User is not found");

        try {
            PreparedStatement statement = connector.prepareStatement("DELETE FROM users WHERE login = ?");
            statement.setString(1, user.getLogin());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
