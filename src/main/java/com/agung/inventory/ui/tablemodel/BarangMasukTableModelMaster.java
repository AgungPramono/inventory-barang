/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.BarangMasuk;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author agung
 */
public class BarangMasukTableModelMaster extends AbstractTableModel {

    private final String[] header = {"Tanggal","Kode Transaksi", "Petugas", "Supplier"};
    private final List<BarangMasuk> listBarangMasuk;

    public BarangMasukTableModelMaster(List<BarangMasuk> barangMasuks) {
        this.listBarangMasuk = barangMasuks;
    }

    @Override
    public int getRowCount() {
        return listBarangMasuk.size();
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
        BarangMasuk master = listBarangMasuk.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return master.getTanggalMasuk().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case 1:
                return master.getKode();
            case 2:
                return master.getPetugas().getNama();
            case 3:
                return master.getSupplier().getNama();
            default:
                return "";
        }
    }
}
