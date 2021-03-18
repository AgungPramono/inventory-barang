/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author agung
 */
public class CustomComboUI extends JComboBox<String> {
    
    private Boolean enableRightClick = Boolean.TRUE;

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

    public CustomComboUI() {
        if (getEnableRightClick()) {
            this.getComponent(0).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        setSelectedItem(null);
                        repaint();
                    }
                }
            });
        }
    }

    public Boolean getEnableRightClick() {
        return enableRightClick;
    }

    public void setEnableRightClick(Boolean enableRightClick) {
        this.enableRightClick = enableRightClick;
    }
    
    

}
