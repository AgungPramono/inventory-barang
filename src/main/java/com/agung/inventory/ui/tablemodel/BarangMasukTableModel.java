/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.BarangMasukDetail;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author agung
 */
public class BarangMasukTableModel extends AbstractTableModel {

    private final String[] header = {"Kode Barang", "Nama Barang", "Jumlah"};
    private final List<BarangMasukDetail> listBarangMasukDetails;

    public BarangMasukTableModel(List<BarangMasukDetail> barangMasukDetails) {
        this.listBarangMasukDetails = barangMasukDetails;
    }

    @Override
    public int getRowCount() {
        return listBarangMasukDetails.size();
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
        BarangMasukDetail detail = listBarangMasukDetails.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return detail.getBarang().getKodeBarang();
            case 1:
                return detail.getBarang().getNamaBarang();
            case 2:
                return detail.getQty();
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {   
        if (columnIndex == 2) {
            return BigDecimal.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        BarangMasukDetail bdm = listBarangMasukDetails.get(rowIndex);
        if (columnIndex == 2) {
            bdm.setQty((BigDecimal) aValue);
        }
    }

}
