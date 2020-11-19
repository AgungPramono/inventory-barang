/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.BarangMasuk;
import com.agung.inventory.entity.BarangMasukDetail;
import com.agung.inventory.entity.Petugas;
import com.agung.inventory.entity.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author agung
 */
public class BarangMasukRowMapper implements RowMapper<BarangMasuk>{

    @Override
    public BarangMasuk mapRow(ResultSet rs, int i) throws SQLException {
        BarangMasuk bm = new BarangMasuk();
        
        Barang barang = new Barang();
        barang.setKodeBarang(rs.getString("kode"));
        barang.setNamaBarang(rs.getString("nama_barang"));
       
        
        Petugas p = new Petugas();
        p.setNama(rs.getString("nama_petugas"));
        bm.setPetugas(p);
        
        Supplier s = new Supplier();
        s.setNama(rs.getString("nama_supplier"));
        bm.setSupplier(s);
        
        BarangMasukDetail bmd = new BarangMasukDetail();
        bmd.setBarang(barang);
        bmd.setQty(rs.getBigDecimal("qty"));
        bmd.setBarangMasuk(bm);
        
        bm.getBarangMasukDetails().add(bmd);
        
        return bm;
    }
    
}
