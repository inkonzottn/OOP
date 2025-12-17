package com.lab6.repository;

import com.lab6.config.ConnectionToDb;
import com.lab6.dao.DeveloperDao;
import com.lab6.entity.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository extends ConnectionToDb implements DeveloperDao {

    Connection connection;

    public DeveloperRepository () {
        this.connection = getConnection();
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        ResultSet resultSet = null;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `developers`");

            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getLong("id"));
                developer.setFirstName(resultSet.getString("first_name"));
                developer.setLastName(resultSet.getString("last_name"));
                developer.setSpecialization(resultSet.getString("specialization"));
                developer.setHourlyRate(resultSet.getDouble("hourly_rate"));

                developers.add(developer);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
        }

        return developers;
    }

    @Override
    public Developer getById(int id) {
        return null;
    }

    @Override
    public void save(Developer developer) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            // Склеюємо SQL запит вручну
            String sql = "INSERT INTO developers (first_name, last_name, specialization, hourly_rate) VALUES (" +
                    "'" + developer.getFirstName() + "', " +
                    "'" + developer.getLastName() + "', " +
                    "'" + developer.getSpecialization() + "', " +
                    developer.getHourlyRate() + ");";

            statement.execute(sql);

            statement.close();
            System.out.println("Developer saved!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Блок закриття ресурсу, якщо він не закрився в try
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Developer obj) {

    }

    @Override
    public void delete(Developer obj) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
