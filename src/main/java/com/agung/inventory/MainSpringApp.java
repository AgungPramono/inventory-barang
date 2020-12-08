/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory;

import com.agung.inventory.config.AppContainer;
import com.agung.inventory.entity.BarangMasuk;
import com.agung.inventory.entity.BarangMasukDetail;
import java.util.List;

/**
 *
 * @author agung
 */
public class MainSpringApp {
    
    public static void main(String[]args){
        AppContainer.initContainer();
        List<BarangMasuk> alList = AppContainer.getTransactionService().findAllBarangMasuk();
        for (BarangMasuk barangMasuk : alList) {
            System.out.println("id "+barangMasuk.getId()+" : "+barangMasuk.getPetugas().getNama());
            for(BarangMasukDetail bmd:barangMasuk.getBarangMasukDetails()){
                System.out.println("Nama Barang"+bmd.getBarang().getNamaBarang());
                System.out.println("Id Header"+bmd.getBarangMasuk().getId());
            }
        }
    }
    
}
