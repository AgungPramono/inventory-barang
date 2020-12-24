/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory;

import com.agung.inventory.config.AppContext;
import com.agung.inventory.ui.MainFrame;
import com.agung.inventory.util.MigrationUtil;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBlue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author agung
 */
public class Main {

    public static MainFrame mainFrame;

    @Autowired
    private static MigrationUtil migrationUtil;

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    private static void SystemInfo() {
        System.out.println("Number Processor :" + Runtime.getRuntime().availableProcessors());
        System.out.println("Total Memory :" + Runtime.getRuntime().totalMemory());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SystemInfo();
                    MigrationUtil.migrate();
                    AppContext.initContainer();
                    PlasticLookAndFeel laf = new PlasticXPLookAndFeel();
                    PlasticLookAndFeel.setCurrentTheme(new DesertBlue());
                    Options.setPopupDropShadowEnabled(true);
                    UIManager.setLookAndFeel(laf);
                    mainFrame = new MainFrame();
                    SecurityConfig.setMainFrame(mainFrame);
                    SecurityConfig.initLogin();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getMessage().contains("Connection refused: connect")) {
                        JOptionPane.showMessageDialog(getMainFrame(), "Error", "Gagal koneksi ke database ", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

}