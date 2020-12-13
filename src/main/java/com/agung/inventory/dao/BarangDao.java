/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.Kategori;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author agung
 */

@Repository
public class BarangDao implements BaseCrudDao<Barang> {
    
    @Autowired
    private SessionFactory sessionFactory;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void simpan(Barang barang) throws SQLException {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(barang);
    }

    @Override
    public Barang cariById(Barang barang) {
       return (Barang) sessionFactory.getCurrentSession()
               .createQuery("select b from Barang b where b.id= :id")
               .setParameter("id", barang.getId())
               .uniqueResult();
    }

    @Override
    public void deleteById(Barang barang) {
        sessionFactory.getCurrentSession().delete(barang);
    }

    public void updateQty(Barang b) throws SQLException {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(b);
    }

    @Override
    public List<Barang> cariSemua() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Barang b")
                .list();
    }

    @Override
    public void setDataSource(Connection dataSource) {
    }

    public List<Barang> cariBarangByName(String text) {
        return sessionFactory.getCurrentSession()
                .createQuery("select b from Barang b where b.nama like :nama")
                .setParameter("nama", "%"+text+"%").list();
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
