/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.combo.model;

import com.agung.inventory.entity.Pelanggan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agung
 */
public class PelangganComboModel extends AbstractListModel implements ComboBoxModel{
    private List<Pelanggan> listPelanggan = new ArrayList<>();
    
    Pelanggan pelanggan = null;

    public PelangganComboModel(List<Pelanggan> listPelanggans) {
        this.listPelanggan = listPelanggans;
    }
    
    @Override
    public int getSize() {
       return listPelanggan.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listPelanggan.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        pelanggan = (Pelanggan) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return pelanggan;
    }
}
