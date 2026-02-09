package com.lab6.repository;

import com.lab6.config.ConnectionToDb;
import com.lab6.dao.DeveloperDao;
import com.lab6.dao.ManagerDao;
import com.lab6.entity.Manager;

import java.sql.Connection;
import java.util.List;

public class ManagerRepository extends ConnectionToDb implements ManagerDao {

    Connection connection;

    public ManagerRepository () {
        this.connection = getConnection();
    }

    @Override
    public List<Manager> getAll() {
        return List.of();
    }

    @Override
    public Manager getById(int id) {
        return null;
    }

    @Override
    public void save(Manager obj) {

    }

    @Override
    public void update(Manager obj) {

    }

    @Override
    public void delete(Manager obj) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
