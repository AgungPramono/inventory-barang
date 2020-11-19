/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangKeluar;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */

@Repository
public class BarangKeluarDao {
    
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
   
    @Autowired
    private void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("barang_keluar")
                .usingColumns("tanggal","id_petugas","id_pelanggan")
                .usingGeneratedKeyColumns("id");
    }
    
    public Integer simpan(BarangKeluar barangKeluar){
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("tanggal", barangKeluar.getTanggalMasuk())
                .addValue("id_petugas", barangKeluar.getPetugas().getId())
                .addValue("id_pelanggan", barangKeluar.getPelanggan().getId());
        
        int retvalId = simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
        barangKeluar.setId(retvalId);
        return retvalId;
    }
    
}
