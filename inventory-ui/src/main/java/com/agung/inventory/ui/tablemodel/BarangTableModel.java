/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class BarangTableModel extends AbstractTableModel{

    private List<Barang> listBarang = new ArrayList<>();
    private final String[]headerTitle = {"kode Item","Nama Item","kategori","Stok","Satuan","Keterangan"};
    
    
    public BarangTableModel(){
        
    }

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
            case 4:return barang.getSatuan();
            case 5:return barang.getKeterangan();
            default:return "";
        }

    }
    
}
