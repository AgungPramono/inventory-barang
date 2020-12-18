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
    public void save(Pelanggan pelanggan)throws SQLException {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(pelanggan);

    }

    @Override
    public Pelanggan findById(Pelanggan t) {
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
    public List<Pelanggan> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pelanggan p")
                .list();
    }

    public List<Pelanggan> cariPelangganByParam(String column,String value){
       StringBuilder query = new StringBuilder("select p from Pelanggan p ");
        if (column.equalsIgnoreCase("kode")){
            query.append("where p.kode = '").append(value).append("'");
        }else if (column.equalsIgnoreCase("nama")){
            query.append("where p.nama like '%").append(value).append("%'");
        }
        query.append(" order by p.nama asc ");
        return sessionFactory.getCurrentSession()
                .createQuery(query.toString())
                .list();
    }

    @Override
    public void setDataSource(Connection dataSource) {

    }
}
