/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.BarangMasukDetail;
import com.agung.inventory.entity.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author agung
 */

@Repository
public class BarangMasukDetailDao implements BaseCrudDao<BarangMasukDetail> {

    private final DataSource datasource;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private final String SQL_INSERT_DETAIL = "INSERT INTO BARANG_MASUK_DETAIL (ID_HEADER,ID_BARANG,QTY) VALUES (:header,?,?)";
    private final String SQL_CARI_BARANG_MASUK = ""
            + "select "
            + "b.qty as stock_akhir,"
            + "b.nama,"
            + "k.nama as kategori,"
            + "bm.qty as stock_masuk "
            + "from "
            + "barang_masuk_detail bm "
            + "join "
            + "barang b on bm.id_barang = b.id "
            + "join "
            + "kategori k on k.id = b.id_kategori "
            + "where bm.id_header=? ";

    @Autowired
    public BarangMasukDetailDao(DataSource datasource) {
        this.datasource = datasource;
        this.jdbcTemplate = new JdbcTemplate(this.datasource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.datasource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.datasource)
                .withTableName("barang_masuk_detail")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(BarangMasukDetail bmd) {
        Map<String, Object> params = new HashMap<>();
        params.put("id_header", bmd.getBarangMasuk().getId());
        params.put("id_barang", bmd.getBarang().getId());
        params.put("qty", bmd.getQty());

        int retvalKey = simpleJdbcInsert.executeAndReturnKey(params).intValue();
        bmd.setId(retvalKey);
    }

    @Override
    public BarangMasukDetail cariById(BarangMasukDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(BarangMasukDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BarangMasukDetail> cariSemua() {
        return jdbcTemplate.query(SQL_CARI_BARANG_MASUK, new BarangMasukDetailMapper());
    }

    public List<BarangMasukDetail> cariDetailByIdMaster(Integer idMaster){
        return jdbcTemplate.query(SQL_CARI_BARANG_MASUK, new Object[]{idMaster},new BarangMasukDetailMapper());
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }

    private class BarangMasukDetailMapper implements RowMapper<BarangMasukDetail> {

        public BarangMasukDetailMapper() {
        }

        @Override
        public BarangMasukDetail mapRow(ResultSet rs, int i) throws SQLException {
            Kategori kategori = new Kategori();
            kategori.setNama(rs.getString("kategori"));

            Barang barang = new Barang();
            barang.setNamaBarang(rs.getString("nama"));
            barang.setQty(rs.getBigDecimal("stock_akhir"));
            barang.setKategori(kategori);

            BarangMasukDetail bmd = new BarangMasukDetail();
            bmd.setQty(rs.getBigDecimal("stock_masuk"));
            bmd.setBarang(barang);
            
            return bmd;
        }
    }

}
