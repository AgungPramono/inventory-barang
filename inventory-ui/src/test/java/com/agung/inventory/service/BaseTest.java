package com.agung.inventory.service;

import com.agung.inventory.config.AppConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class BaseTest {

    static AbstractApplicationContext context;
    static MasterService masterService;
    static TransactionService transactionService;


    @BeforeAll
    public static void setupClass(){
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        masterService = context.getBean(MasterService.class);
        transactionService = context.getBean(TransactionService.class);
    }


   @AfterAll
    public static void tearDownClass(){
        context.stop();
    }
}
