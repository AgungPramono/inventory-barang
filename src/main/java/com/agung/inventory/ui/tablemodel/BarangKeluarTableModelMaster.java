/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.BarangKeluar;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class BarangKeluarTableModelMaster extends AbstractTableModel {

    private final String[] header = {"Tanggal","Kode Transaksi", "Petugas", "Customer"};
    private final List<BarangKeluar> listBarangKeluar;

    public BarangKeluarTableModelMaster(List<BarangKeluar> barangKeluars) {
        this.listBarangKeluar = barangKeluars;
    }

    @Override
    public int getRowCount() {
        return listBarangKeluar.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BarangKeluar master = listBarangKeluar.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return master.getTanggalMasuk().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case 1:
                return master.getKode();
            case 2:
                return master.getPetugas().getNama();
            case 3:
                return master.getPelanggan().getNama();
            default:
                return "";
        }
    }
}
