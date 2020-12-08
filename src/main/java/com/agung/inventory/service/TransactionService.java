/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.service;

import com.agung.inventory.dao.*;
import com.agung.inventory.entity.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BarangKeluarDetailDao barangKeluarDetailDao;

    @Autowired
    private BarangDao barangDao;

    @Transactional(readOnly = false)
    public void simpanBarangMasuk(BarangMasuk barangMasuk) throws Exception {
        barangMasukDao.simpan(barangMasuk);

        for (BarangMasukDetail detail : barangMasuk.getBarangMasukDetails()) {
            detail.setBarangMasuk(barangMasuk);
            barangMasukDetailDao.simpan(detail);

            Barang b = barangDao.cariById(detail.getBarang());
            if (b != null) {
                BigDecimal newQty = b.getQty().add(detail.getQty());
                b.setQty(newQty);
                b.setId(detail.getBarang().getId());
                barangDao.updateQty(b);
            }
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class,
        StokTidakCukupException.class},
            readOnly = false)
    public void simpanBarangKeluar(BarangKeluar barangKeluar) throws Exception {
        if (barangKeluar != null) {
            barangKeluarDao.simpan(barangKeluar);

            for (BarangKeluarDetail detail : barangKeluar.getBarangKeluarDetails()) {
                detail.setBarangKeluar(barangKeluar);
                barangKeluarDetailDao.simpan(detail);

                Barang b = barangDao.cariById(detail.getBarang());

                if (b != null) {
                    if (detail.getQty().compareTo(b.getQty()) > 0) {
                        throw new StokTidakCukupException("stock " + detail.getBarang().getNamaBarang() + " tidak mencukupi");
                    }
                    BigDecimal newQty = b.getQty().subtract(detail.getQty());
                    b.setQty(newQty);
                    b.setId(detail.getBarang().getId());
                    barangDao.updateQty(b);
                }
            }
        }
    }
    
    public List<BarangMasuk> findAllBarangMasuk(){
        return barangMasukDao.cariSemua();
    }
    
    public List<BarangMasuk> findBarangMasukMaster(){
        return barangMasukDao.cariSemuaBarangMasuksMaster();
    }
    
    public List<BarangMasuk> findBarangMasukByParam(String kolom, String value){
        return barangMasukDao.cariByParameter(kolom, value);
    }
    
    public List<BarangKeluar> findBarangKeluar(){
        return barangKeluarDao.cariSemua();
    }
    
     public List<BarangKeluar> findBarangKeluarByParam(String kolom, String value){
        return barangKeluarDao.cariByParameter(kolom, value);
    }
}