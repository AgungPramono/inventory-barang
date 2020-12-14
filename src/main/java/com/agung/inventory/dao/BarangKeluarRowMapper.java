/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangKeluar;
import com.agung.inventory.entity.Pelanggan;
import com.agung.inventory.entity.Petugas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author agung
 */
public class BarangKeluarRowMapper implements RowMapper<BarangKeluar>{

    @Override
    public BarangKeluar mapRow(ResultSet rs, int i) throws SQLException {
        BarangKeluar bm = new BarangKeluar();
        bm.setId(rs.getInt("id"));
        bm.setKode(rs.getString("no_transaksi"));
        bm.setTanggalMasuk(rs.getTimestamp("tanggal").toLocalDateTime());

        Petugas petugas = new Petugas();
        petugas.setNama(rs.getString("nama_petugas"));
        bm.setPetugas(petugas);
//        
        Pelanggan pelanggan=  new Pelanggan();
        pelanggan.setNama(rs.getString("nama_customer"));
        bm.setPelanggan(pelanggan);
        
        return bm;
    }
    
}
