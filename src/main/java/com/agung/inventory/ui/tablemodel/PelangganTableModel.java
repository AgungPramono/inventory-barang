/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Pelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class PelangganTableModel extends AbstractTableModel{
    
    private List<Pelanggan> listPelanggans = new ArrayList<>();
    private String[]headerTitle = {"id","kode","Nama Pelanggan","Alamat","Telepon"};

    public PelangganTableModel(List<Pelanggan> pelanggans) {
        this.listPelanggans = pelanggans;
    }
    

    @Override
    public int getRowCount() {
        return listPelanggans.size();
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
        Pelanggan pelanggan = listPelanggans.get(rowIndex);
        switch(columnIndex){
            case 0:return pelanggan.getId();
            case 1:return pelanggan.getKode();
            case 2:return pelanggan.getNama();
            case 3:return pelanggan.getAlamat();
            case 4:return pelanggan.getTelepon();
            default:return "";
        }

    }
    
}
