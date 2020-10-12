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
 */
public interface BaseCrudDao<T> {
    
    public void setDataSource(Connection dataSource);
    
    public void simpan(T t)throws SQLException;
    
    public T cariById(T t)throws SQLException;
    
    public void deleteById(T t)throws SQLException;
    
    public List<T> cariSemua()throws SQLException;
    
}
