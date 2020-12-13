/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangMasukDetail;
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
public class BarangMasukDetailDao implements BaseCrudDao<BarangMasukDetail> {

    @Autowired private SessionFactory sessionFactory;

    @Autowired
    public BarangMasukDetailDao(DataSource datasource) {
    }

    @Override
    public void simpan(BarangMasukDetail bmd) {

    }

    @Override
    public BarangMasukDetail cariById(BarangMasukDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(BarangMasukDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BarangMasukDetail> cariSemua() {
        return sessionFactory.getCurrentSession()
                .createQuery("select b from BarangMasukDetail b")
                .list();
    }

    public List<BarangMasukDetail> cariDetailByIdMaster(Integer idMaster){
        return sessionFactory.getCurrentSession()
                .createQuery("select b from BarangMasukDetail b where b.barangMasuk.id= :idMaster")
                .setParameter("idMaster",idMaster)
                .list();
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }


}
