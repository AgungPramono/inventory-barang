/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.Kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author agung
 */

@Component
public class BarangDao implements BaseCrudDao<Barang> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SIMPAN = "insert into barang (id_kategori,kode,nama,qty,keterangan) "
            + "values (?,?,?,?,?)";
    private final String SQL_UPDATE = "update barang set id_kategori=?,kode=?,nama=?,qty=?,keterangan=? where id=? ";
    private final String SQL_DELETE_BY_ID = "delete from barang where id=?";
    private final String SQL_FIND_BY_ID = "select b.*,k.* from barang b inner join kategori k on b.id_kategori = k.id "
            + "where b.id=?";
    private final String SQL_FIND_BY_NAME = "select b.*,k.* from barang b inner join kategori k on b.id_kategori = k.id "
            + "where b.nama like lower(?)";
    private final String SQL_FIND_ALL = "select b.*,k.* from barang b inner join kategori k on b.id_kategori = k.id";
    private final String SQL_UPDATE_QTY = "update barang set qty=? where id=?";

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void simpan(Barang barang) throws SQLException {
        if (barang.getId() == null) {
            jdbcTemplate.update((Connection con) -> {
                PreparedStatement ps = con.prepareStatement(SQL_SIMPAN);
                ps.setInt(1, barang.getKategori().getId());
                ps.setString(2, barang.getKodeBarang());
                ps.setString(3, barang.getNamaBarang());
                ps.setBigDecimal(4, barang.getQty());
                ps.setString(5, barang.getKeterangan());
                return ps;
            });
        } else {
            jdbcTemplate.update((Connection con) -> {
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
                ps.setInt(1, barang.getKategori().getId());
                ps.setString(2, barang.getKodeBarang());
                ps.setString(3, barang.getNamaBarang());
                ps.setBigDecimal(4, barang.getQty());
                ps.setString(5, barang.getKeterangan());
                ps.setInt(6, barang.getId());
                return ps;});
        }
    }

    @Override
    public Barang cariById(Barang barang) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{barang.getId()}, new BarangRowMapper());
    }

    @Override
    public void deleteById(Barang barang) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, barang.getId());
    }

    public void updateQty(Barang b) throws SQLException {
        jdbcTemplate.update(SQL_UPDATE_QTY, b.getQty(), b.getId());
    }

    @Override
    public List<Barang> cariSemua() {
        return jdbcTemplate.query(SQL_FIND_ALL, new BarangRowMapper());
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }

    public List<Barang> cariBarangByName(String text) {
        return jdbcTemplate.query(SQL_FIND_BY_NAME, new Object[]{"%"+text.toLowerCase()+"%"},new BarangRowMapper());
    }

    private class BarangRowMapper implements RowMapper<Barang> {

        public BarangRowMapper() {
        }

        @Override
        public Barang mapRow(ResultSet rs, int i) throws SQLException {
            Barang b = new Barang();
            b.setId(rs.getInt("id"));
            b.setKodeBarang(rs.getString("kode"));
            b.setNamaBarang(rs.getString("nama"));
            b.setQty(rs.getBigDecimal("qty"));
            b.setKeterangan(rs.getString("keterangan"));

            Kategori k = new Kategori();
            k.setId(rs.getInt("id"));
            k.setKode(rs.getString("k.kode"));
            k.setNama(rs.getString("k.nama"));
            b.setKategori(k);
            return b;
        }
    }

}
