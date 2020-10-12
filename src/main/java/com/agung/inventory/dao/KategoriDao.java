/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Kategori;
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
public class KategoriDao implements BaseCrudDao<Kategori> {

    private DataSource dataSource;
    private static final String SQL_INSERT_KATEGORI = "insert into kategori (kode,nama) value (?,?)";
    private static final String SQL_SELECT_ALL_KATEGORI = "select * from kategori";
    private final String SQL_UPDATE_KATEGORI = "update kategori set kode=?,nama=? where id=? ";
    private int result = 0;

    @Override
    public void setDataSource(Connection dataSource) {
    }
    
    public KategoriDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void simpan(Kategori t) {
        if (t.getId() == null) {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_KATEGORI);
                ps.setString(1, t.getKode());
                ps.setString(2, t.getNamaKategori());
                int resp = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE_KATEGORI);
                ps.setString(1, t.getKode());
                ps.setString(2, t.getNamaKategori());
                ps.setInt(3, t.getId());
                int resp = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Kategori cariById(Kategori t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Kategori t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Kategori> cariSemua() {
        List<Kategori> kategoris = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL_KATEGORI);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kategori k = new Kategori();
                k.setId(rs.getInt("id"));
                k.setKode(rs.getString("kode"));
                k.setNamaKategori(rs.getString("nama"));
                kategoris.add(k);
            }
            return kategoris;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();

    }

}
