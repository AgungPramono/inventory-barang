/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangKeluarDetail;
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
public class BarangKeluarDetailDao {
    
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
   
    @Autowired
    private void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("barang_keluar_detail")
                .usingColumns("id_header","id_barang","qty")
                .usingGeneratedKeyColumns("id");
    }
    
    public Integer simpan(BarangKeluarDetail detail){
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id_header", detail.getBarangKeluar().getId())
                .addValue("id_barang", detail.getBarang().getId())
                .addValue("qty",detail.getQty());
        
        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }
    
}
