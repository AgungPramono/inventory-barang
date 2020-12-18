/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.tablemodel;

import com.agung.inventory.entity.BarangMasukDetail;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class BarangMasukDetailTableModel extends AbstractTableModel{
    private final String[]header = {"Barang","Kategori","Satuan","Stok Akhir","Stock Masuk"};
    private List<BarangMasukDetail> listDetail;
    private final NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ID"));

    public void setData(List<BarangMasukDetail> listDetail) {
        this.listDetail = listDetail;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return listDetail.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public void fireTableChanged(TableModelEvent e) {
        super.fireTableChanged(e); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BarangMasukDetail bmd = listDetail.get(rowIndex);
        switch(columnIndex){
            case 0:return bmd.getBarang().getNamaBarang();
            case 1:return bmd.getBarang().getKategori().getNama();
            case 2:return bmd.getBarang().getSatuan();
            case 3:return numberFormat.format(bmd.getBarang().getQty());
            case 4:return numberFormat.format(bmd.getQty());
            default:return "";
        }
    }
    
}
