/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class SupplierTableModel extends AbstractTableModel{
    
    private List<Supplier> listSuppliers = new ArrayList<>();
    private final String[]headerTitle = {"id","kode","Nama Supplier","Alamat","Telepon"};

    public SupplierTableModel(List<Supplier> suppliers) {
        this.listSuppliers = suppliers;
    }
    

    @Override
    public int getRowCount() {
        return listSuppliers.size();
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
        Supplier s = listSuppliers.get(rowIndex);
        switch(columnIndex){
            case 0:return s.getId();
            case 1:return s.getKode();
            case 2:return s.getNama();
            case 3:return s.getAlamat();
            case 4:return s.getTelepon();
            default:return "";
        }

    }
    
}
