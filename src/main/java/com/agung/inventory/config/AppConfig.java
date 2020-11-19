/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.config;

import com.agung.inventory.db.ConnectionHelper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author agung
 */
@Configuration
@ComponentScan(basePackages = {"com.agung.inventory"})
@EnableTransactionManagement
public class AppConfig {
    
    @Bean("dataSource")
    public HikariDataSource dataSource(){
        return ConnectionHelper.getDataSource();
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
