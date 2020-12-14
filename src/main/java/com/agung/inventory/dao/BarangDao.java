/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class BarangDao implements BaseCrudDao<Barang> {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Barang barang) throws SQLException {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(barang);
    }

    @Override
    public Barang findById(Barang barang) {
       return (Barang) sessionFactory.getCurrentSession()
               .createQuery("select b from Barang b where b.id= :id")
               .setParameter("id", barang.getId())
               .uniqueResult();
    }

    @Override
    public void deleteById(Barang barang) {
        sessionFactory.getCurrentSession().delete(barang);
    }

    public void updateQty(Barang b) throws SQLException {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(b);
    }

    @Override
    public List<Barang> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Barang b")
                .list();
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }

    public List cariBarangByName(String text) {
        return sessionFactory.getCurrentSession()

                .createQuery("select b from Barang b where b.nama like :nama")
                .setParameter("nama", "%"+text+"%").list();
    }

}
