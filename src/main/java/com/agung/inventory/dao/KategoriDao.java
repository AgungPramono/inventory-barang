/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Kategori;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
public class KategoriDao implements BaseCrudDao<Kategori> {
    
    @Autowired
    private SessionFactory sessionFactory;

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final String SQL_SELECT_ALL_KATEGORI = "select * from kategori";
    private final String SQL_UPDATE_KATEGORI = "update kategori set kode=?,nama=? where id=? ";
    private final String SQL_DELETE_KATEGORI = "delete from katefori where id=? ";
    private final int result = 0;

    @Override
    public void setDataSource(Connection dataSource) {
    }

    
    @Autowired
    public KategoriDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource)
                .withTableName("kategori")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(Kategori t)throws SQLException{
        sessionFactory.getCurrentSession()
                .saveOrUpdate(t);
    }

    @Override
    public Kategori cariById(Kategori t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Kategori t) throws SQLException{
        sessionFactory.getCurrentSession()
                .delete(t);
    }


    @Override
    public List<Kategori> cariSemua() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Kategori k")
                .list();
    }

    private class KategoriRowMapper implements RowMapper<Kategori> {

        public KategoriRowMapper() {
        }

        @Override
        public Kategori mapRow(ResultSet rs, int i) throws SQLException {
            Kategori k = new Kategori();
            k.setId(rs.getInt("id"));
            k.setKode(rs.getString("kode"));
            k.setNama(rs.getString("nama"));
            return k;
        }
    }

}
