/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dao;

import com.agung.inventory.entity.Supplier;
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
public class SupplierDao implements BaseCrudDao<Supplier> {
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    
    private static final String SQL_UPDATE_SUPPLIER = "update supplier set kode=?,nama=?,alamat=?,telepon=? where id=?";
    private static final String SQL_SELECT_ALL_SUPPLIER = "select * from supplier";
    private static final String SQL_DELETE_SUPPLIER = "delete from supplier where id=?";

    public SupplierDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource)
                .withTableName("supplier")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void simpan(Supplier t) {
        if (t.getId() == null) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(t);
            simpleJdbcInsert.execute(parameterSource);
        } else {
            jdbcTemplate.update(SQL_UPDATE_SUPPLIER,
                    t.getKode(),
                    t.getNama(),
                    t.getAlamat(),
                    t.getTelepon(),
                    t.getId());
        }

    }

    @Override
    public Supplier cariById(Supplier t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Supplier t) {
        jdbcTemplate.update(SQL_DELETE_SUPPLIER, t.getId());
    }

    @Override
    public List<Supplier> cariSemua() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPPLIER, new BeanPropertyRowMapper(Supplier.class));
    }
    
     
    @Override
    public void setDataSource(Connection dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
