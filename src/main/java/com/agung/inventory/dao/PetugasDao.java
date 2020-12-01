/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Petugas;
import com.agung.inventory.db.ConnectionHelper;
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
public class PetugasDao implements BaseCrudDao<Petugas> {

    private DataSource dataSource;

    private static final String SQL_SELECT_ALL = "select * from petugas";
    private static final String SQL_FIND_BY_ID = "select * from petugas where id=?";

    public PetugasDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void simpan(Petugas t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Petugas cariById(Petugas t) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID);
            ps.setInt(1, t.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Petugas p = new Petugas();
                p.setId(rs.getInt("id"));
                p.setNama(rs.getString("nama"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PetugasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Petugas();
    }

    @Override
    public void deleteById(Petugas t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Petugas> cariSemua() {
        try {
            List<Petugas> result = new ArrayList<>();
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Petugas p = new Petugas();
                p.setId(rs.getInt("id"));
                p.setNama(rs.getString("nama"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                result.add(p);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(PetugasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
     @Override
    public void setDataSource(Connection dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
