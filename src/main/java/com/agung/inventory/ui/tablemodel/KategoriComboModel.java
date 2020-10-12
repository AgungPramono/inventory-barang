/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.Kategori;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author agung
 */

public class KategoriComboModel extends AbstractListModel implements ComboBoxModel{
    
    private List<Kategori> listKategori = new ArrayList<>();
    
    Kategori kategori = null;

    public KategoriComboModel(List<Kategori> listKategori) {
        this.listKategori = listKategori;
    }
    
    @Override
    public int getSize() {
       return listKategori.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listKategori.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        kategori = (Kategori) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return kategori;
    }
    
}
