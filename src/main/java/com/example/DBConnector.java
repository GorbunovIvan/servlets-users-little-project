package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/servlets_registrations";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    public static Connection getConnection() {

        if (connection != null)
            return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Cannot connect to DB");
            throw new RuntimeException(e);
        }

        return connection;

    }

}
