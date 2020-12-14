package com.agung.inventory.util;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class MigrationUtil {

    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/inventory?serverTimezone=Asia/Jakarta";
    private static final String jdbcUsername = "admin";
    private static final String jdbcPassword = "admin123";

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, jdbcUsername, jdbcPassword)
                .load();
        flyway.migrate();
    }


}
