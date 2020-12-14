/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangKeluar;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class BarangKeluarDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(BarangKeluar barangKeluar){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(barangKeluar);
    }
    
    public List findAll(){
        return sessionFactory.getCurrentSession()
                .createQuery("from BarangKeluar b")
                .list();
    }
    
    public List findByParameter(String kolom, String value) {
        StringBuilder sql = new StringBuilder("select bk from BarangKeluar bk ");

        switch (kolom) {
            case "tanggal":
                sql.append("where date(bk.tanggalMasuk) ='").append(value).append("'");
                break;
            case "kode":
                sql.append("where bk.kode='").append(value).append("'");
                break;
            case "pelanggan":
                sql.append("where bk.pelanggan.nama like '%").append(value).append("%'");
                break;
            case "petugas":
                sql.append("where bk.petugas.nama like '%").append(value).append("%'");
                break;
            default:
                break;
        }
        sql.append(" order by bk.tanggalMasuk desc");
        
        return sessionFactory.getCurrentSession()
                .createQuery(sql.toString())
                .list();
    }
    
}
