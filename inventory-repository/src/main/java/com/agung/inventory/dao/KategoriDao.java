/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Kategori;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */

@Repository
public class KategoriDao implements BaseCrudDao<Kategori> {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setDataSource(Connection dataSource) {
    }

    @Override
    public void save(Kategori t)throws SQLException{
        sessionFactory.getCurrentSession()
                .saveOrUpdate(t);
    }

    @Override
    public Kategori findById(Kategori t) {
        return (Kategori) sessionFactory.getCurrentSession()
                .createQuery("from Kategori k where k.id= :id")
                .setParameter("id", t.getId())
                .uniqueResult();
    }

    @Override
    public void deleteById(Kategori t) throws SQLException{
        sessionFactory.getCurrentSession()
                .delete(t);
    }


    @Override
    public List<Kategori> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select k from Kategori k order by k.nama asc")
                .list();
    }

}
