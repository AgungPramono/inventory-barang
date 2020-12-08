/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.combo.model;

import com.agung.inventory.entity.Pelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author agung
 */

public class CustomerComboModel extends AbstractListModel implements ComboBoxModel{
    
    private List<Pelanggan> listPelanggans = new ArrayList<>();
    
    Pelanggan pelanggan = null;

    public CustomerComboModel(List<Pelanggan> listPelanggans) {
        this.listPelanggans = listPelanggans;
    }
    
    @Override
    public int getSize() {
       return listPelanggans.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listPelanggans.get(index);
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
