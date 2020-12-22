/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.config;

import com.agung.inventory.service.MasterService;
import com.agung.inventory.service.ReportService;
import com.agung.inventory.service.TransactionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * aplikasi container untuk digunakan di class lain yang
 * mengakses databases
 * @author agung
 */
public class AppContext {

    private static TransactionService transactionService;
    private static ReportService reportService;
    private static MasterService masterService;

    public static void initContainer(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        transactionService =  context.getBean(TransactionService.class);
        reportService = context.getBean(ReportService.class);
        masterService = context.getBean(MasterService.class);
    }

    public static ReportService getReportService() {
        return reportService;
    }

    public static MasterService getMasterService() {
        return masterService;
    }

    public static TransactionService getTransactionService() {
        return transactionService;
    }
}
