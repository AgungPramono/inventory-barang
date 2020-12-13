/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Petugas;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class PetugasDao implements BaseCrudDao<Petugas> {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Petugas t) {
       sessionFactory.getCurrentSession()
               .saveOrUpdate(t);
    }

    @Override
    public Petugas findById(Petugas t) {
       return (Petugas) sessionFactory.getCurrentSession()
               .createQuery("select p from Petugas p where p.id= :id")
               .setParameter("id",t.getId())
               .uniqueResult();
    }

    @Override
    public void deleteById(Petugas t) {
        sessionFactory.getCurrentSession()
                .delete(t);
    }

    @Override
    public List<Petugas> findAll() {
       return sessionFactory.getCurrentSession()
               .createQuery("from Petugas p")
               .list();
    }
    
     @Override
    public void setDataSource(Connection dataSource) {
    }

    public Petugas cariByUsername(String userName) {
        return (Petugas) sessionFactory.getCurrentSession()
                .createQuery("from Petugas p where p.username= :username")
                .setParameter("username", userName)
                .uniqueResult();
    }

}
