/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.service;

import com.agung.inventory.dao.BarangDao;
import com.agung.inventory.dao.BarangMasukDao;
import com.agung.inventory.dao.BarangMasukDetailDao;
import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.BarangMasuk;
import com.agung.inventory.entity.BarangMasukDetail;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agung
 */
public class TransaksiService {

    private static TransaksiService INSTANCE;
    private BarangDao barangDao;
    private BarangMasukDao barangMasukDao;
    private BarangMasukDetailDao detailDao;
    private DataSource dataSource;

    public TransaksiService(DataSource dataSource) {
        this.dataSource = dataSource;
        barangDao = new BarangDao(dataSource);
        barangMasukDao = new BarangMasukDao(dataSource);
        detailDao = new BarangMasukDetailDao(dataSource);
    }

    public void simpanBarangMasuk(BarangMasuk barangMasuk) {
        try {
            this.dataSource.getConnection().setAutoCommit(false);
            barangMasukDao.simpan(barangMasuk);
            
            for (BarangMasukDetail bmd : barangMasuk.getBarangMasukDetails()) {
                bmd.setBarangMasuk(barangMasuk);
                detailDao.simpan(bmd);
            }
            for (BarangMasukDetail detail : barangMasuk.getBarangMasukDetails()) {
                Barang b = barangDao.cariById(detail.getBarang());
                if (b != null) {
                    BigDecimal newQty = b.getQty().add(detail.getQty());
                    b.setQty(newQty);
                    b.setId(detail.getBarang().getId());
                    barangDao.updateQty(b);
                }
            }
            this.dataSource.getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
                this.dataSource.getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally {
            try {
                dataSource.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
