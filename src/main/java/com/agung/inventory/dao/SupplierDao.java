/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Supplier;
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
public class SupplierDao implements BaseCrudDao<Supplier> {
    
    private DataSource dataSource;

    private static final String SQL_SIMPAN_SUPPLIER = "insert into supplier (kode,nama,alamat,telepon) value (?,?,?,?)";
    private static final String SQL_UPDATE_SUPPLIER = "update supplier set kode=?,nama=?,alamat=?,telepon=? where id=?";
    private static final String SQL_SELECT_ALL_SUPPLIER = "select * from supplier";
    private static final String SQL_DELETE_SUPPLIER = "delete from supplier where id=?";

    public SupplierDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void simpan(Supplier t) {
        if (t.getId() == null) {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SIMPAN_SUPPLIER);
                ps.setString(1, t.getKode());
                ps.setString(2, t.getName());
                ps.setString(3, t.getAlamat());
                ps.setString(4, t.getNoTelpon());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PelangganDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE_SUPPLIER);
                ps.setString(1, t.getKode());
                ps.setString(2, t.getName());
                ps.setString(3, t.getAlamat());
                ps.setString(4, t.getNoTelpon());
                ps.setInt(5, t.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PelangganDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Supplier cariById(Supplier t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Supplier t) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE_SUPPLIER);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Supplier> cariSemua() {
        List<Supplier> listSupplier = new ArrayList<>();

        try {
        Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL_SUPPLIER);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supplier p = new Supplier();
                p.setId(rs.getInt("id"));
                p.setKode(rs.getString("kode"));
                p.setName(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setNoTelpon(rs.getString("telepon"));
                listSupplier.add(p);
            }
            return listSupplier;
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
     
    @Override
    public void setDataSource(Connection dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
