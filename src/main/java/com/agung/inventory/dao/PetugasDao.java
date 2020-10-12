/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Petugas;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author agung
 */
public class PetugasDao implements BaseCrudDao<Petugas> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String SQL_SELECT_ALL = "select * from petugas";
    private static final String SQL_UPDATE_PETUGAS = "update petugas set nama=?,username=?,password=?,active=? where id=?";
    private static final String SQL_FIND_BY_ID = "select * from petugas where id=?";

    public PetugasDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource)
                .withTableName("petugas")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(Petugas t) {
        if (t.getId() == null) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(t);
            simpleJdbcInsert.execute(parameterSource);
        }else{
           jdbcTemplate.update(SQL_UPDATE_PETUGAS,
                   t.getNama(),
                   t.getUsername(),
                   t.getPassword(),
                   t.isActive());
        }
    }

    @Override
    public Petugas cariById(Petugas t) {
       return (Petugas) jdbcTemplate.queryForObject(SQL_FIND_BY_ID,new Object[]{t.getId()},new BeanPropertyRowMapper(Petugas.class));
    }

    @Override
    public void deleteById(Petugas t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Petugas> cariSemua() {
       return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper(Petugas.class));
    }
    
     @Override
    public void setDataSource(Connection dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
