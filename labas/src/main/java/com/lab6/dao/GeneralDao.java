package com.lab6.dao;

import java.util.List;

public interface GeneralDao<T> {
    List<T> getAll();
    T getById(int id);

    void save(T obj);
    void update(T obj);
    void delete(T obj);
    void delete (Long id);
    void deleteAll();

}
