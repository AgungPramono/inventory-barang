/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory;

import com.agung.inventory.config.AppContainer;
import com.agung.inventory.config.SecurityConfig;
import com.agung.inventory.ui.MainFrame;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author agung
 */
public class Main {

    public static MainFrame mainFrame;

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
                    AppContainer.initContainer();
                    PlasticLookAndFeel laf = new PlasticXPLookAndFeel();
                    PlasticLookAndFeel.setCurrentTheme(new ExperienceRoyale());
                    Options.setPopupDropShadowEnabled(true);
                    UIManager.setLookAndFeel(laf);
                    mainFrame = new MainFrame();
                    SecurityConfig.setMainFrame(mainFrame);
                    SecurityConfig.initLogin();
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
