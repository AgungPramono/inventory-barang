/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.di;

import com.agung.inventory.dao.BarangDao;
import com.agung.inventory.dao.KategoriDao;
import com.agung.inventory.dao.PelangganDao;
import com.agung.inventory.dao.PetugasDao;
import com.agung.inventory.dao.SupplierDao;
import com.agung.inventory.service.TransaksiService;
import com.agung.inventory.db.ConnectionHelper;
import javax.sql.DataSource;

/**
 *
 * @author agung
 */
public class InventoryInjector {
    
    private static InventoryInjector INSTANCE;
    
    private static BarangDao barangDao;
    private static KategoriDao kategoriDao;
    private static PelangganDao pelangganDao;
    private static SupplierDao supplierDao;
    private static PetugasDao petugasDao;
    private static TransaksiService transaksiService;
    
    
    public static synchronized InventoryInjector getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new InventoryInjector();
        }
        initDataSource();
        return INSTANCE;
    }
    
    private static void initDataSource(){
        DataSource ds = ConnectionHelper.getDataSource();
        barangDao = new BarangDao(ds);
        kategoriDao = new KategoriDao(ds);
        pelangganDao = new PelangganDao(ds);
        petugasDao = new PetugasDao(ds);
        supplierDao = new SupplierDao(ds);
        transaksiService = new TransaksiService(ds);
    }

    public static TransaksiService getTransaksiService() {
       return transaksiService ;
    }
    
    public static BarangDao getBarangDao(){
        return barangDao;
    }
    
    public static KategoriDao getKategoriDao(){
        return kategoriDao;
    }
    
    public static PelangganDao getPelangganDao(){
        return pelangganDao;
    }
    
    public static SupplierDao getSupplierDao(){
        return supplierDao;
    }
    
    public static PetugasDao getPetugasDao(){
        return petugasDao;
    }
}
