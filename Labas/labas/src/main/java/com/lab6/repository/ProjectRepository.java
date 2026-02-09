package com.lab6.repository;

import com.lab6.config.ConnectionToDb;
import com.lab6.dao.ProjectDao;
import com.lab6.entity.Project;

import java.sql.Connection;
import java.util.List;

public class ProjectRepository extends ConnectionToDb implements ProjectDao {

    Connection connection;

    public ProjectRepository () {
        this.connection = getConnection();
    }

    @Override
    public List<Project> getAll() {
        return List.of();
    }

    @Override
    public Project getById(int id) {
        return null;
    }

    @Override
    public void save(Project obj) {

    }

    @Override
    public void update(Project obj) {

    }

    @Override
    public void delete(Project obj) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
