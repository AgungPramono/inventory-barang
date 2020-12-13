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
    
     private final String FIND_ALL_TRANSACTION_MASTER = "select bm.id,bm.tanggal,bm.no_transaksi,p.nama as nama_petugas,s.nama as nama_customer "
            + "from barang_keluar bm "
            + "join petugas p "
            + "on p.id = bm.id_petugas "
            + "join pelanggan s "
            + "on s.id = bm.id_pelanggan ";
   
    @Autowired
    private void setDataSource(DataSource dataSource){
    }
    
    public void simpan(BarangKeluar barangKeluar){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(barangKeluar);
    }
    
    public List cariSemua(){
        return sessionFactory.getCurrentSession()
                .createQuery("from BarangKeluar b")
                .list();
    }
    
    public List cariByParameter(String kolom, String value) {
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
