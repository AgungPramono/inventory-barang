/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Kategori;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class KategoriTableModel extends AbstractTableModel{
    
    private List<Kategori> listKategori = new ArrayList<>();
    private String[]headerTitle = {"id","kode","Nama Kategori"};

    public KategoriTableModel(List<Kategori> kategoris) {
        this.listKategori = kategoris;
    }
    

    @Override
    public int getRowCount() {
        return listKategori.size();
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
        Kategori kategori = listKategori.get(rowIndex);
        switch(columnIndex){
            case 0:return kategori.getId();
            case 1:return kategori.getKode();
            case 2:return kategori.getNama();
            default:return "";
        }

    }
    
}
