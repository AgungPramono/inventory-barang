/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Barang;
import lombok.NoArgsConstructor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agung
 */
@NoArgsConstructor
public class BarangTableModel extends AbstractTableModel{

    private List<Barang> listBarang = new ArrayList<>();
    private final String[]headerTitle = {"kode","Nama","kategori","Stok","Keterangan"};

    public BarangTableModel(List<Barang> barangs) {
        this.listBarang = barangs;
    }

    public void setListBarang(List<Barang> listBarang) {
        this.listBarang = listBarang;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listBarang.size();
    }

    @Override
    public int getColumnCount() {
        return headerTitle.length;
    }

    @Override
    public String getColumnName(int column) {
        return headerTitle[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang barang = listBarang.get(rowIndex);
        switch(columnIndex){
            case 0:return barang.getKodeBarang();
            case 1:return barang.getNamaBarang();
            case 2:return barang.getKategori();
            case 3:return barang.getQty().longValue();
            case 4:return barang.getKeterangan();
            default:return "";
        }

    }
    
}
