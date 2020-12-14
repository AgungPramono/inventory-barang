/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.service;

import com.agung.inventory.constan.StokTidakCukupException;
import com.agung.inventory.dao.BarangDao;
import com.agung.inventory.dao.BarangKeluarDao;
import com.agung.inventory.dao.BarangMasukDao;
import com.agung.inventory.dao.BarangMasukDetailDao;
import com.agung.inventory.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author agung
 */
@Service("transactionService")
@Transactional(readOnly = true)
public class TransactionService {

    @Autowired
    private BarangMasukDao barangMasukDao;

    @Autowired
    private BarangMasukDetailDao barangMasukDetailDao;

    @Autowired
    private BarangKeluarDao barangKeluarDao;

    @Autowired
    private BarangDao itemDao;

    @Transactional(readOnly = false)
    public void saveInTransaction(BarangMasuk barangMasuk) throws Exception {
        barangMasukDao.save(barangMasuk);
        for (BarangMasukDetail detail : barangMasuk.getBarangMasukDetails()) {
            Barang b = itemDao.findById(detail.getBarang());
            if (b != null) {
                BigDecimal newQty = b.getQty().add(detail.getQty());
                b.setQty(newQty);
                b.setId(detail.getBarang().getId());
                itemDao.updateQty(b);
            }
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class,
        StokTidakCukupException.class},
            readOnly = false, isolation = Isolation.READ_COMMITTED )
    public void saveOutTransaction(BarangKeluar barangKeluar) throws Exception {
        if (barangKeluar != null) {
            barangKeluarDao.save(barangKeluar);
            for (BarangKeluarDetail detail : barangKeluar.getBarangKeluarDetails()) {
                Barang b = itemDao.findById(detail.getBarang());
                if (b != null) {
                    if (detail.getQty().compareTo(b.getQty()) > 0) {
                        throw new StokTidakCukupException("stock " + detail.getBarang().getNamaBarang() + " tidak mencukupi");
                    }
                    BigDecimal newQty = b.getQty().subtract(detail.getQty());
                    b.setQty(newQty);
                    b.setId(detail.getBarang().getId());
                    itemDao.save(b);
                }
            }
        }
    }
    
    public List<BarangMasuk> findAllBarangMasuk(){
        return barangMasukDao.findAll();
    }
    
    public List<BarangMasuk> findBarangMasukMaster(){
        return barangMasukDao.finAllTransactionMaster();
    }
    
    public List<BarangMasuk> findBarangMasukByParam(String kolom, String value){
        return barangMasukDao.cariByParameter(kolom, value);
    }
    
    public List<BarangKeluar> findAllBarangKeluar(){
        return barangKeluarDao.findAll();
    }
    
     public List<BarangKeluar> findAllBarangKeluarByParam(String kolom, String value){
        return barangKeluarDao.findByParameter(kolom, value);
    }

    public List<BarangMasukDetail> findAllBarangMasukDetailByIdMaster(Integer id){
        return barangMasukDetailDao.cariDetailByIdMaster(id);
    }
}