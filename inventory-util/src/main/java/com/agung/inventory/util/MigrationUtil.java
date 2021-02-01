package com.agung.inventory.util;

import org.flywaydb.core.Flyway;

public class MigrationUtil {

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
