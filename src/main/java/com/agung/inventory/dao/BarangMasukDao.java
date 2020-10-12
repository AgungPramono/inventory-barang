/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.BarangMasuk;
import com.agung.inventory.entity.BarangMasukDetail;
import com.agung.inventory.entity.Gudang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author agung
 */
public class BarangMasukDao implements BaseCrudDao<BarangMasuk> {

    private DataSource dataSource;

    public BarangMasukDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private String SQL_INSERT = "insert into barang_masuk (tanggal,id_petugas,id_supplier) values (?,?,?)";
    private String SQL_DELETE_DETAIL = "delete from barang_masuk_detail where id=?";
    private String SQL_CARI_BARANG_MASUK;
    private String SQL_HAPUS_HEADER = "delete from barang_masuk where id=?";

    
    

    @Override
    public void simpan(BarangMasuk t) {
        if (t.getId() == null) {
            try {
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, Timestamp.valueOf(t.getTanggalMasuk()));
                ps.setInt(2, t.getPetugas().getId());
                ps.setInt(3, t.getSupplier().getId());
                ps.executeUpdate();
                ResultSet rs  = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(BarangMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void simpanDetail(BarangMasukDetail bmd) {
        
    }

    private void hapusDetail(BarangMasukDetail bmd) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_DETAIL);
            ResultSet executeQuery = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BarangMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BarangMasuk cariById(BarangMasuk t) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CARI_BARANG_MASUK);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BarangMasuk barangMasuk = new BarangMasuk();
                return barangMasuk;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteById(BarangMasuk t) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_HAPUS_HEADER);
            ps.executeUpdate();
            for(BarangMasukDetail detail:t.getBarangMasukDetails()){
                hapusDetail(detail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<BarangMasuk> cariSemua() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private BarangMasukDetail ResultSetMapper(ResultSet rs) throws SQLException {

        BarangMasukDetail bmd = new BarangMasukDetail();
        bmd.setId(rs.getInt("id"));
        bmd.setQty(rs.getBigDecimal("qty"));

        //relasi ke barang
        Barang barang = new Barang();
        barang.setId(rs.getInt("id_barang"));
        barang.setKodeBarang(rs.getString("kode_barang"));
        barang.setNamaBarang(rs.getString("nama_barang"));
        bmd.setBarang(barang);

        //relasi ke gudang
        Gudang gudang = new Gudang();
        gudang.setId(rs.getInt("id"));
        gudang.setKodeGudang(rs.getString("kode_gudang"));
        gudang.setNamaGudang(rs.getString("nama_gudang"));

        //relasi ke barang masuk
        BarangMasuk barangMasuk = new BarangMasuk();
        barangMasuk.setId(rs.getInt("id"));
        barangMasuk.setTanggalMasuk(rs.getTimestamp("tanggal_masuk").toLocalDateTime());
        bmd.setBarangMasuk(barangMasuk);

        return bmd;

    }

    
    @Override
    public void setDataSource(Connection dataSource) {
    }
}
