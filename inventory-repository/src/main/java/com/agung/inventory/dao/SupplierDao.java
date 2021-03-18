/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Supplier;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class SupplierDao implements BaseCrudDao<Supplier> {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public SupplierDao(DataSource dataSource) {

    }

    @Override
    public void save(Supplier supplier) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(supplier);
    }

    @Override
    public Supplier findById(Supplier t) {
        return (Supplier) sessionFactory.getCurrentSession()
                .createQuery("select s from Supplier s where s.id= :id")
                .setParameter("id", t.getId())
                .uniqueResult();
    }


    @Override
    public void deleteById(Supplier supplier) {
        sessionFactory.getCurrentSession()
                .delete(supplier);
    }

    @Override
    public List<Supplier> findAll() {
        return sessionFactory.getCurrentSession()
        .createQuery("from Supplier s")
        .list();
    }

    public List<Supplier> findSupplierByParan(String column,String value){
        StringBuilder query =new StringBuilder("select s from Supplier s ");
        if (column.equalsIgnoreCase("nama")){
            query.append("wher s.nama like '").append(value).append("'");
        }else if (column.equalsIgnoreCase("kode")){
            query.append("where s.kode ='").append(value).append("'");
        }

        query.append(" order by s.nama asc");
        return sessionFactory.getCurrentSession()
                .createQuery(query.toString())
                .list();
    }
     
    @Override
    public void setDataSource(Connection dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
