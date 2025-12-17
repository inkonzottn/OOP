package com.lab6.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDb {
    public Connection getConnection() {

        Connection connection =  null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_team", "root", "Nastya-aa-mysql2007");
            System.out.println("Database connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  connection;
    }
}
