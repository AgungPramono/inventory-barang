/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.config;

import com.agung.inventory.dao.*;
import com.agung.inventory.service.MasterService;
import com.agung.inventory.service.ReportService;
import com.agung.inventory.service.TransactionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author agung
 */
public class AppContainer {

    private static TransactionService transactionService;
    private static ReportService reportService;
    private static BarangDao barangDao;
    private static KategoriDao kategoriDao;
    private static PelangganDao pelangganDao;
    private static PetugasDao petugasDao;
    private static SupplierDao supplierDao;
    private static MasterService masterService;

    public static void initContainer(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        transactionService =  context.getBean(TransactionService.class);
        reportService = context.getBean(ReportService.class);
        masterService = context.getBean(MasterService.class);
        barangDao = context.getBean(BarangDao.class);
        kategoriDao = context.getBean(KategoriDao.class);
        pelangganDao = context.getBean(PelangganDao.class);
        petugasDao = context.getBean(PetugasDao.class);
        supplierDao = context.getBean(SupplierDao.class);
    }

    public static ReportService getReportService() {
        return reportService;
    }
    

    public static BarangDao getBarangDao() {
        return barangDao;
    }

    public static KategoriDao getKategoriDao() {
        return kategoriDao;
    }

    public static PelangganDao getPelangganDao() {
        return pelangganDao;
    }

    public static PetugasDao getPetugasDao() {
        return petugasDao;
    }

    public static SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public static MasterService getMasterService() {
        return masterService;
    }

    public static TransactionService getTransactionService() {
        return transactionService;
    }
}
