/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author agung
 */
public class ConnectionHelper {
    
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource hikariDataSource;
    
    static{
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/inventory?serverTimezone=UTC" );
        config.setUsername( "admin" );
        config.setPassword( "admin123" );
        config.setMaxLifetime(30000);
        config.setMinimumIdle(10);
        config.setMaximumPoolSize(80);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        hikariDataSource = new HikariDataSource( config );
    }
    
    private ConnectionHelper(){
        
    }
    
    public static HikariDataSource getDataSource(){
            return hikariDataSource;
    }
}
