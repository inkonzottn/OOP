package com.lab6.repository;

import com.lab6.config.ConnectionToDb;
import com.lab6.dao.QADao;
import com.lab6.entity.QASpecialist;

import java.sql.Connection;
import java.util.List;

public class QARepository extends ConnectionToDb implements QADao {

    Connection connection;

    public QARepository () {
        this.connection = getConnection();
    }

    @Override
    public List<QASpecialist> getAll() {
        return List.of();
    }

    @Override
    public QASpecialist getById(int id) {
        return null;
    }

    @Override
    public void save(QASpecialist obj) {

    }

    @Override
    public void update(QASpecialist obj) {

    }

    @Override
    public void delete(QASpecialist obj) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
