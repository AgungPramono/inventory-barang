/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.Kategori;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agung
 */
public class BarangDao implements BaseCrudDao<Barang> {

    private DataSource dataSource;

    private static final String SQL_SIMPAN = "insert into barang (id_kategori,kode,nama,qty,keterangan) "
            + "values (?,?,?,?,?)";
    private final String SQL_UPDATE = "update barang set id_kategori=?,kode=?,nama=?,qty=?,keterangan=? where id=? ";
    private final String SQL_DELETE_BY_ID = "delete from barang where id=?";
    private final String SQL_FIND_BY_ID = "select b.*,k.* from barang b inner join kategori k on b.id_kategori = k.id "
            + "where b.id=?";
    private final String SQL_FIND_ALL = "select b.*,k.* from barang b inner join kategori k on b.id_kategori = k.id";
    private String SQL_UPDATE_QTY = "update barang set qty=? where id=?";

    public BarangDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void simpan(Barang barang) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            if (barang.getId() == null) {
                try (PreparedStatement ps = conn.prepareStatement(SQL_SIMPAN)) {
                    ps.setInt(1, barang.getKategori().getId());
                    ps.setString(2, barang.getKodeBarang());
                    ps.setString(3, barang.getNamaBarang());
                    ps.setBigDecimal(4, barang.getQty());
                    ps.setString(5, barang.getKeterangan());
                    ps.executeUpdate();
                }


            } else {
                try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
                    ps.setInt(1, barang.getKategori().getId());
                    ps.setString(2, barang.getKodeBarang());
                    ps.setString(3, barang.getNamaBarang());
                    ps.setBigDecimal(4, barang.getQty());
                    ps.setString(5, barang.getKeterangan());
                    ps.setInt(6, barang.getId());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Barang cariById(Barang barang) {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setInt(1, barang.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Barang b = new Barang();
                        b.setId(rs.getInt("id"));
                        b.setKodeBarang(rs.getString("kode"));
                        b.setNamaBarang(rs.getString("nama"));
                        b.setQty(rs.getBigDecimal("qty"));

                        Kategori k = new Kategori();
                        k.setId(rs.getInt("id"));
                        k.setKode(rs.getString("k.kode"));
                        k.setNamaKategori(rs.getString("k.nama"));
                        b.setKategori(k);
                        return b;
                    }
                }
            }
        } catch (SQLException ex) {
           throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public void deleteById(Barang barang) {
        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement(SQL_DELETE_BY_ID)){
                ps.setInt(1, barang.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
           throw new RuntimeException(ex);
        }
    }

    public void updateQty(Barang b) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement(SQL_UPDATE_QTY)){
                ps.setBigDecimal(1, b.getQty());
                ps.setInt(2, b.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

    @Override
    public List<Barang> cariSemua() {
        List<Barang> listBarangs = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Barang b = new Barang();
                        b.setId(rs.getInt("id"));
                        b.setKodeBarang(rs.getString("kode"));
                        b.setNamaBarang(rs.getString("nama"));
                        b.setQty(rs.getBigDecimal("qty"));
                        b.setKeterangan(rs.getString("keterangan"));

                        Kategori k = new Kategori();
                        k.setId(rs.getInt("id"));
                        k.setKode(rs.getString("k.kode"));
                        k.setNamaKategori(rs.getString("k.nama"));
                        b.setKategori(k);
                        listBarangs.add(b);
                    }
                    return listBarangs;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }

}
