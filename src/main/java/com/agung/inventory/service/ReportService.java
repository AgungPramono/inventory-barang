/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.service;

import com.agung.inventory.dto.LaporanBarangDto;
import com.agung.inventory.dto.LaporanBarangMasukDto;
import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.BarangMasuk;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author agung
 */
@Service
public class ReportService {

    @Autowired
    private MasterService masterService;
    
    @Autowired
    private TransactionService transactionService;

    public JasperPrint printLaporanStok() {
        try {
            List<LaporanBarangDto> listWrapper = dataStokBarang();
            JasperReport JRpt = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/laporan_stok_barang.jrxml"));
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, null,new JRBeanCollectionDataSource(listWrapper));
            return JPrint;
        } catch (JRException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public JasperPrint printLaporanBarangMasuk(LocalDateTime tanggalMulai, LocalDateTime tanggalSampai) {
        try {
            
            Map<String,Object> param = new HashMap<>();
            param.put("title", "Laporan Barang Masuk");
            param.put("tanggalMulai", tanggalMulai);
            param.put("tanggalSampai", tanggalSampai);
            
            List<LaporanBarangMasukDto> listWrapper = dataBarangMasuk();
            JasperReport JRpt = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/laporan_barang_masuk.jrxml"));
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, param,new JRBeanCollectionDataSource(listWrapper));
            return JPrint;
        } catch (JRException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<LaporanBarangDto> dataStokBarang() {
        List<LaporanBarangDto> result = new ArrayList<>();
        List<Barang> listBarang = masterService.findAllBarang();

        for(Barang b:listBarang){
            LaporanBarangDto barangDto  = new LaporanBarangDto();
            barangDto.setKode(b.getKodeBarang());
            barangDto.setNama(b.getNamaBarang());
            barangDto.setStok(b.getQty().intValue());
            barangDto.setKategori(b.getKategori().getNama());
            barangDto.setKeterangan(b.getKeterangan());
            result.add(barangDto);
        }
        
        return result;

    }
    
    private List<LaporanBarangMasukDto> dataBarangMasuk(){
        List<LaporanBarangMasukDto> result = new ArrayList<>();
        List<BarangMasuk> listBarangMasuk = transactionService.findAllBarangMasuk();
        
        for(BarangMasuk bm:listBarangMasuk){
            LaporanBarangMasukDto dto = new LaporanBarangMasukDto();
            dto.setPetugas(bm.getPetugas().getNama());
            dto.setSupplier(bm.getPetugas().getNama());
            result.add(dto);
        }
        return result;
    }

}
