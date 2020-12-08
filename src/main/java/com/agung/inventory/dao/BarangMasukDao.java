/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangMasuk;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */
@Repository
public class BarangMasukDao implements BaseCrudDao<BarangMasuk> {

    private final String SQL_INSERT = "insert into barang_masuk (tanggal,no_transaksi,id_petugas,id_supplier) values (?,?,?,?)";
    private final String SQL_DELETE_DETAIL = "delete from barang_masuk_detail where id=?";
    private final String SQL_CARI_BARAN_MASUK = "";
    private final String SQL_HAPUS_HEADER = "delete from barang_masuk where id=?";
    
    private final String FIND_ALL_TRANSACTION = "select b.kode,b.nama as nama_barang,bm.id,bm.tanggal,bm.no_transaksi,p.nama as nama_petugas,"
            + "s.nama as nama_supplier,bmd.qty "
            + "from barang_masuk bm "
            + "join barang_masuk_detail bmd "
            + "on bm.id = bmd.id_header "
            + "join barang b "
            + "on bmd.id_barang = b.id "
            + "join petugas p "
            + "on p.id = bm.id_petugas "
            + "join supplier s "
            + "on s.id = bm.id_supplier group by bm.tanggal";
    
    private final String FIND_ALL_TRANSACTION_MASTER = "select bm.id,bm.tanggal,bm.no_transaksi,p.nama as nama_petugas,s.nama as nama_supplier "
            + "from barang_masuk bm "
            + "join petugas p "
            + "on p.id = bm.id_petugas "
            + "join supplier s "
            + "on s.id = bm.id_supplier ";

    private final DataSource dataSource;
    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;

    public BarangMasukDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource)
                .withTableName("barang_masuk")
                .usingColumns("tanggal", "id_petugas", "id_supplier", "no_transaksi")
                .usingGeneratedKeyColumns("id");
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("barang_masuk")
                .usingColumns("tanggal", "id_petugas", "id_supplier", "no_transaksi")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(BarangMasuk t) {
        if (t.getId() == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("tanggal", t.getTanggalMasuk());
            params.put("no_transaksi", t.getKode());
            params.put("id_petugas", t.getPetugas().getId());
            params.put("id_supplier", t.getSupplier().getId());
            int retId = simpleJdbcInsert.executeAndReturnKey(params).intValue();
            t.setId(retId);
        }
    }

    @Override
    public BarangMasuk cariById(BarangMasuk t) {
        return (BarangMasuk) jdbcTemplate.queryForObject(SQL_CARI_BARAN_MASUK, new Object[]{t.getId()}, new BeanPropertyRowMapper(BarangMasuk.class));
    }

    @Override
    public void deleteById(BarangMasuk t) {
            
    }

    @Override
    public List<BarangMasuk> cariSemua() {
        return jdbcTemplate.query(FIND_ALL_TRANSACTION, new BarangMasukRowMapper());
    }

    public List<BarangMasuk> cariSemuaBarangMasuksMaster() {
        StringBuilder sql = new StringBuilder(FIND_ALL_TRANSACTION_MASTER);
        sql.append("order by bm.tanggal desc");
        return jdbcTemplate.query(sql.toString(), new BarangMasukRowMapper());
    }

    public List<BarangMasuk> cariByParameter(String kolom, String value) {
        StringBuilder sql = new StringBuilder(FIND_ALL_TRANSACTION_MASTER);

        switch (kolom) {
            case "tanggal":
                sql.append("where bm.tanggal=").append(value);
                break;
            case "kode":
                sql.append("where bm.no_transaksi=").append(value);
                break;
            case "supplier":
                sql.append("where s.nama like '%").append(value).append("%'");
                break;
            case "petugas":
                sql.append("where p.nama like '%").append(value).append("%'");
                break;
            default:
                break;
        }
        sql.append(" order by bm.tanggal desc");
        
        return jdbcTemplate.query(sql.toString(), new BarangMasukRowMapper());
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }
}
