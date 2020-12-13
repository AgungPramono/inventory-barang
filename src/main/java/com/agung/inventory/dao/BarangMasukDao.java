/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangMasuk;
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
public class BarangMasukDao implements BaseCrudDao<BarangMasuk> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void simpan(BarangMasuk barangMasuk) {
       sessionFactory.getCurrentSession()
               .saveOrUpdate(barangMasuk);
    }

    @Override
    public BarangMasuk cariById(BarangMasuk t) {
        return (BarangMasuk) sessionFactory.getCurrentSession()
                .createQuery("select bm from BarangMasuk bm where bm.id= :id")
                .setParameter("id",t.getId())
                .uniqueResult();
    }

    @Override
    public void deleteById(BarangMasuk t) {
            
    }

    @Override
    public List<BarangMasuk> cariSemua() {
        return sessionFactory.getCurrentSession()
                .createQuery("from BarangMasuk bm")
                .list();
    }

    public List cariSemuaBarangMasuksMaster() {
       return sessionFactory.getCurrentSession()
               .createQuery("select bm from BarangMasuk bm order by bm.tanggalMasuk desc")
               .list();
    }

    public List<BarangMasuk> cariByParameter(String kolom, String value) {
        StringBuilder sql = new StringBuilder("select bm from BarangMasuk bm ");

        switch (kolom) {
            case "tanggal":
                sql.append("where date(bm.tanggalMasuk) ='").append(value).append("'");
                break;
            case "kode":
                sql.append("where bm.kode='").append(value).append("'");
                break;
            case "supplier":
                sql.append("where bm.supplier.nama like '%").append(value).append("%'");
                break;
            case "petugas":
                sql.append("where bm.petugas.nama like '%").append(value).append("%'");
                break;
            default:
                break;
        }
        sql.append(" order by bm.tanggalMasuk desc");
        
        List result = sessionFactory.getCurrentSession()
                .createQuery(sql.toString())
                .list();
        return result;
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }
}
