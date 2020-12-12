/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Pelanggan;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class PelangganDao implements BaseCrudDao<Pelanggan> {

    @Autowired
    private SessionFactory sessionFactory;

    public PelangganDao(DataSource dataSource) {
    }

    @Override
    public void simpan(Pelanggan pelanggan)throws SQLException {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(pelanggan);

    }

    @Override
    public Pelanggan cariById(Pelanggan t) {
        return (Pelanggan) sessionFactory.getCurrentSession()
                .createQuery("select p from Pelanggan p where p.id= :id")
                .setParameter("id", t.getId())
                .uniqueResult();
    }

    @Override
    public void deleteById(Pelanggan t) {
        sessionFactory.getCurrentSession()
                .delete(t);
    }

    @Override
    public List<Pelanggan> cariSemua() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pelanggan p")
                .list();
    }

    @Override
    public void setDataSource(Connection dataSource) {

    }
}
