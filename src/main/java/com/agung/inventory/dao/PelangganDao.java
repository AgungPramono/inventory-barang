/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author agung
 */
public class PelangganDao implements BaseCrudDao<Pelanggan> {

    private DataSource dataSource;

    private static final String SQL_SIMPAN_PELANGGAN = "insert into pelanggan (kode,nama,alamat,telepon) value (?,?,?,?)";
    private static final String SQL_UPDATE_PELANGGAN = "update pelanggan set kode=?,nama=?,alamat=?,telepon=? where id=?";
    private static final String SQL_SELECT_ALL_PELANGGAN = "select * from pelanggan";
    private static final String SQL_DELETE_PELANGGAN = "delete from pelanggan where id=?";

    public PelangganDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void simpan(Pelanggan t) {
        if (t.getId() == null) {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SIMPAN_PELANGGAN);
                ps.setString(1, t.getKode());
                ps.setString(2, t.getNama());
                ps.setString(3, t.getAlamat());
                ps.setString(4, t.getNoTelepon());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PelangganDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE_PELANGGAN);
                ps.setString(1, t.getKode());
                ps.setString(2, t.getNama());
                ps.setString(3, t.getAlamat());
                ps.setString(4, t.getNoTelepon());
                ps.setInt(5, t.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PelangganDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Pelanggan cariById(Pelanggan t) {
        return null;
    }

    @Override
    public void deleteById(Pelanggan t) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PELANGGAN);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pelanggan> cariSemua() {
        List<Pelanggan> listPelanggans = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL_PELANGGAN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setId(rs.getInt("id"));
                p.setKode(rs.getString("kode"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setNoTelepon(rs.getString("telepon"));
                listPelanggans.add(p);
            }
            return listPelanggans;
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public void setDataSource(Connection dataSource) {

    }

}
