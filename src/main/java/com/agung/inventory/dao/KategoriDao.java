/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Kategori;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author agung
 */
public class KategoriDao implements BaseCrudDao<Kategori> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String SQL_SELECT_ALL_KATEGORI = "select * from kategori";
    private final String SQL_UPDATE_KATEGORI = "update kategori set kode=?,nama=? where id=? ";
    private int result = 0;

    @Override
    public void setDataSource(Connection dataSource) {
    }

    public KategoriDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("kategori")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(Kategori t) {
        if (t.getId() == null) {
            SqlParameterSource params = new BeanPropertySqlParameterSource(t);
            simpleJdbcInsert.execute(params);
        } else {
            jdbcTemplate.update(SQL_UPDATE_KATEGORI,
                    t.getKode(),
                    t.getNama(),
                    t.getId());
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
        return jdbcTemplate.query(SQL_SELECT_ALL_KATEGORI, new BeanPropertyRowMapper(Kategori.class));
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
