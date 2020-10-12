/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Petugas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class PetugasTableModel extends AbstractTableModel{
    
    private List<Petugas> listPetugases = new ArrayList<>();
    private String[]headerTitle = {"id","nama","Username"};

    public PetugasTableModel(List<Petugas> listPetugases) {
        this.listPetugases = listPetugases;
    }
    

    @Override
    public int getRowCount() {
        return listPetugases.size();
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
        Petugas petugas = listPetugases.get(rowIndex);
        switch(columnIndex){
            case 0:return petugas.getId();
            case 1:return petugas.getNama();
            case 2:return petugas.getUsername();
            default:return "";
        }

    }
    
}
