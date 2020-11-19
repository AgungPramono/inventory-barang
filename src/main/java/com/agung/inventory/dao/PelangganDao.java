/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Pelanggan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */

@Repository
public class PelangganDao implements BaseCrudDao<Pelanggan> {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final String SQL_UPDATE_PELANGGAN = "update pelanggan set kode=?,nama=?,alamat=?,telepon=? where id=?";
    private static final String SQL_SELECT_ALL_PELANGGAN = "select * from pelanggan";
    private static final String SQL_DELETE_PELANGGAN = "delete from pelanggan where id=?";

    @Autowired
    public PelangganDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource)
                .withTableName("pelanggan")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(Pelanggan t)throws SQLException {
        if (t.getId() == null) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(t);
            simpleJdbcInsert.execute(parameterSource);
        } else {
            jdbcTemplate.update(SQL_UPDATE_PELANGGAN,
                    t.getKode(),
                    t.getNama(),
                    t.getAlamat(),
                    t.getTelepon(),
                    t.getId());
        }

    }

    @Override
    public Pelanggan cariById(Pelanggan t) {
        return null;
    }

    @Override
    public void deleteById(Pelanggan t) {
        jdbcTemplate.update(SQL_DELETE_PELANGGAN, t.getId());
    }

    @Override
    public List<Pelanggan> cariSemua() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PELANGGAN, new PelangganRowMapper());
    }

    private static class PelangganRowMapper implements RowMapper<Pelanggan> {

        public PelangganRowMapper() {
        }

        @Override
        public Pelanggan mapRow(ResultSet rs, int i) throws SQLException {
            Pelanggan p = new Pelanggan();
            p.setId(rs.getInt("id"));
            p.setKode(rs.getString("kode"));
            p.setNama(rs.getString("nama"));
            p.setAlamat(rs.getString("alamat"));
            p.setTelepon(rs.getString("telepon"));
            return p;
        }
    }

    
    @Override
    public void setDataSource(Connection dataSource) {

    }
}
