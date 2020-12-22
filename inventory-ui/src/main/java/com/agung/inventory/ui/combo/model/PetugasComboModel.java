/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.combo.model;

import com.agung.inventory.entity.Petugas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agung
 */

public class PetugasComboModel extends AbstractListModel implements ComboBoxModel{
    
    private List<Petugas> listPetugas = new ArrayList<>();
    
    Petugas petugas = null;

    public PetugasComboModel(List<Petugas> listPetugases) {
        this.listPetugas = listPetugases;
    }
    
    @Override
    public int getSize() {
       return listPetugas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listPetugas.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        petugas = (Petugas) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return petugas;
    }
    
}
