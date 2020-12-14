/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangMasuk;
import com.agung.inventory.entity.Petugas;
import com.agung.inventory.entity.Supplier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author agung
 */
public class BarangMasukRowMapper implements RowMapper<BarangMasuk>{

    @Override
    public BarangMasuk mapRow(ResultSet rs, int i) throws SQLException {
        BarangMasuk bm = new BarangMasuk();
        bm.setId(rs.getInt("id"));
        bm.setKode(rs.getString("no_transaksi"));
        bm.setTanggalMasuk(rs.getTimestamp("tanggal").toLocalDateTime());
        
        Petugas p = new Petugas();
        p.setNama(rs.getString("nama_petugas"));
        bm.setPetugas(p);
        
        Supplier s = new Supplier();
        s.setNama(rs.getString("nama_supplier"));
        bm.setSupplier(s);
        
        return bm;
    }
    
}
