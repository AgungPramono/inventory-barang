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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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

    @Autowired
    private DataSource dataSource;

    private JasperDesign jasperDesign;
    private JRDesignQuery jrQuery = new JRDesignQuery();

    public JasperPrint printLaporanStok() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("tanggalUpdate", LocalDateTime.now());

            List<LaporanBarangDto> listWrapper = dataStokBarang();

            JasperReport JRpt = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream(ReportConstant.PATH_RPT_STOK_BARANG));
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, params, new JRBeanCollectionDataSource(listWrapper));
            return JPrint;
        } catch (JRException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public JasperPrint generateLaporanTransaksi(LocalDateTime tanggalMulai, LocalDateTime tanggalSampai, String jenisLaporan) {
        try {

            String start = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(tanggalMulai);
            String end = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(tanggalSampai);

            StringBuilder query = new StringBuilder();
            Map<String, Object> param = new HashMap<>();

            if (jenisLaporan.equals(ReportConstant.LAPORAN_BARANG_MASUK)) {
                jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(ReportConstant.PATH_RPT_BARANG_MASUK));
                query.append(SqlConstan.QUERY_REPORT_BARANG_MASUK);
                param.put("title", "Laporan Barang Masuk");
            } else if (jenisLaporan.equals(ReportConstant.LAPORAN_BARANG_KELUAR)) {
                jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(ReportConstant.PATH_RPT_BARANG_KELUAR));
                param.put("title", "Laporan Barang Keluar");
                query.append(SqlConstan.QUERY_REPORT_BARANG_KELUAR);
            }

            param.put("tanggalMulai", tanggalMulai);
            param.put("tanggalSampai", tanggalSampai);

            query.append("where bm.tanggal >='").append(start).append("' ");
            query.append("and bm.tanggal <='").append(end).append("' ");
            query.append("order by bm.tanggal desc");

            jrQuery.setText(query.toString());
            jasperDesign.setQuery(jrQuery);

            JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, param, dataSource.getConnection());
            return JPrint;
        } catch (JRException | SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public JasperPrint generateLaporanMasukById(Integer id) {
        try {
            jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(ReportConstant.PATH_RPT_BARANG_MASUK));

            StringBuilder query = new StringBuilder();
            query.append(SqlConstan.QUERY_REPORT_BARANG_MASUK);

            Map<String, Object> param = new HashMap<>();
            param.put("title", "Laporan Barang Masuk");

            query.append("where bm.id=").append(id);
            query.append(" order by bm.tanggal desc");

            jrQuery.setText(query.toString());
            jasperDesign.setQuery(jrQuery);

            JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, param, dataSource.getConnection());
            return JPrint;
        } catch (JRException | SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public JasperPrint generateLaporanKeluarById(Integer id) {
        try {
            jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(ReportConstant.PATH_RPT_BARANG_KELUAR));

            StringBuilder query = new StringBuilder();
            query.append(SqlConstan.QUERY_REPORT_BARANG_KELUAR);

            Map<String, Object> param = new HashMap<>();
            param.put("title", "Laporan Barang Keluar");

            query.append("where bm.id=").append(id);
            query.append(" order by bm.tanggal desc");

            jrQuery.setText(query.toString());
            jasperDesign.setQuery(jrQuery);

            JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, param, dataSource.getConnection());
            return JPrint;
        } catch (JRException | SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<LaporanBarangDto> dataStokBarang() {
        List<LaporanBarangDto> result = new ArrayList<>();
        List<Barang> listBarang = masterService.findAllBarang();

        for (Barang b : listBarang) {
            LaporanBarangDto barangDto = new LaporanBarangDto();
            barangDto.setKode(b.getKodeBarang());
            barangDto.setNama(b.getNamaBarang());
            barangDto.setStok(b.getQty().intValue());
            barangDto.setKategori(b.getKategori().getNama());
            barangDto.setKeterangan(b.getKeterangan());
            result.add(barangDto);
        }

        return result;

    }

    private List<LaporanBarangMasukDto> dataBarangMasuk() {
        List<LaporanBarangMasukDto> result = new ArrayList<>();
        List<BarangMasuk> listBarangMasuk = transactionService.findAllBarangMasuk();

        for (BarangMasuk bm : listBarangMasuk) {
            LaporanBarangMasukDto dto = new LaporanBarangMasukDto();
            dto.setPetugas(bm.getPetugas().getNama());
            dto.setSupplier(bm.getSupplier().getNama());
            result.add(dto);
        }
        return result;
    }

}
