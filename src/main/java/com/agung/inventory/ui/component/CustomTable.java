/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.component;

import com.agung.inventory.util.TableUtil;
import javax.swing.JScrollPane;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author agung
 */
public class CustomTable extends JXTable {
    
    private JScrollPane scrollPane;

    public CustomTable() {
        customUI();
    }
    
    private void customUI(){
        setRowHeight(30);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
        setAutoResizeMode(AUTO_RESIZE_OFF);
        TableUtil.initColumn(this,scrollPane);
        TableUtil.formatTable(this);
    }
    
    
}
