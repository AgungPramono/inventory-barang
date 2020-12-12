/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.di;

import com.agung.inventory.dao.*;
import com.agung.inventory.db.ConnectionHelper;
import com.agung.inventory.service.TransaksiService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *
 * @author agung
 * untuk container jika menggunakan metode
 * access jdbc murni
 */
public class Injector {

    private static Injector INSTANCE;

    private static KategoriDao kategoriDao;
    private static PelangganDao pelangganDao;
    private static SupplierDao supplierDao;
    private static PetugasDao petugasDao;
    private static TransaksiService transaksiService;
    private static BarangMasukDetailDao barangMasukDetailDao;

    public static synchronized Injector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Injector();
            initDataSource();
        }
        return INSTANCE;
    }

    private static void initDataSource() {
        DataSource ds = ConnectionHelper.getDataSource();
        kategoriDao = new KategoriDao(ds);
        pelangganDao = new PelangganDao(ds);
        petugasDao = new PetugasDao(ds);
        supplierDao = new SupplierDao(ds);
        transaksiService = new TransaksiService(ds);
        barangMasukDetailDao = new BarangMasukDetailDao(ds);
    }

    public static BarangMasukDetailDao getBarangMasukDetailDao() {
        return barangMasukDetailDao;
    }

    public static TransaksiService getTransaksiService() {
        return transaksiService;
    }

    public static KategoriDao getKategoriDao() throws SQLException {
        return kategoriDao;
    }

    public static PelangganDao getPelangganDao() {
        return pelangganDao;
    }

    public static SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public static PetugasDao getPetugasDao() {
        return petugasDao;
    }
}
