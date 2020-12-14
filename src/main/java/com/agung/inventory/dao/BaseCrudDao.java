/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author agung
 * @param <T>
 */
public interface BaseCrudDao<T> {
    
    void setDataSource(Connection dataSource);
    
    void save(T t)throws SQLException;
    
    T findById(T t)throws SQLException;
    
    void deleteById(T t)throws SQLException;
    
    List<T> findAll()throws SQLException;

}
