/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.combo.model;

import com.agung.inventory.entity.Supplier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agung
 */

public class SupplierComboModel extends AbstractListModel implements ComboBoxModel{
    
    private List<Supplier> listSuppliers = new ArrayList<>();
    
    Supplier supplier = null;

    public SupplierComboModel(List<Supplier> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }
    
    @Override
    public int getSize() {
       return listSuppliers.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listSuppliers.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        supplier = (Supplier) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return supplier;
    }
    
}
