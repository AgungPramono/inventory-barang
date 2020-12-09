/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangKeluar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class BarangKeluarDao {
    
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    
     private final String FIND_ALL_TRANSACTION_MASTER = "select bm.id,bm.tanggal,bm.no_transaksi,p.nama as nama_petugas,s.nama as nama_customer "
            + "from barang_keluar bm "
            + "join petugas p "
            + "on p.id = bm.id_petugas "
            + "join pelanggan s "
            + "on s.id = bm.id_pelanggan ";
   
    @Autowired
    private void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("barang_keluar")
                .usingColumns("tanggal","id_petugas","id_pelanggan","no_transaksi")
                .usingGeneratedKeyColumns("id");
    }
    
    public Integer simpan(BarangKeluar barangKeluar){
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("tanggal", barangKeluar.getTanggalMasuk())
                .addValue("no_transaksi", barangKeluar.getKode())
                .addValue("id_petugas", barangKeluar.getPetugas().getId())
                .addValue("id_pelanggan", barangKeluar.getPelanggan().getId());
        
        int retvalId = simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
        barangKeluar.setId(retvalId);
        return retvalId;
    }
    
    public List<BarangKeluar> cariSemua(){
        return jdbcTemplate.query(FIND_ALL_TRANSACTION_MASTER, new BarangKeluarRowMapper());
    }
    
    public List<BarangKeluar> cariByParameter(String kolom, String value) {
        StringBuilder sql = new StringBuilder(FIND_ALL_TRANSACTION_MASTER);

        switch (kolom) {
            case "tanggal":
                sql.append("where date(bm.tanggal) ='").append(value).append("'");
                break;
            case "kode":
                sql.append("where bm.no_transaksi=").append(value);
                break;
            case "pelanggan":
                sql.append("where s.nama like '%").append(value).append("%'");
                break;
            case "petugas":
                sql.append("where p.nama like '%").append(value).append("%'");
                break;
            default:
                break;
        }
        sql.append(" order by bm.tanggal desc");
        
        return jdbcTemplate.query(sql.toString(), new BarangKeluarRowMapper());
    }
    
}
