/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.BarangMasukDetail;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agung
 */
public class BarangMasukDetailDao implements BaseCrudDao<BarangMasukDetail>{

    private DataSource datasource;
    private String SQL_INSERT_DETAIL = "insert into barang_masuk_detail (id_header,id_barang,qty) values (?,?,?)";
    

    public BarangMasukDetailDao(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public void simpan(BarangMasukDetail bmd) {
        try(Connection conn=datasource.getConnection()) {
           try( PreparedStatement ps2 = conn.prepareStatement(SQL_INSERT_DETAIL)){
               ps2.setInt(1, bmd.getBarangMasuk().getId());
               ps2.setInt(2, bmd.getBarang().getId());
               ps2.setBigDecimal(3, bmd.getQty());
               ps2.executeUpdate();
           }
        } catch (SQLException ex) {
            Logger.getLogger(BarangMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setDataSource(Connection dataSource) {
    }

    
}
