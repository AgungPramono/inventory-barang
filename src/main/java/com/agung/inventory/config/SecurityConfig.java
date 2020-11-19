/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.config;

import com.agung.inventory.entity.Petugas;
import com.agung.inventory.ui.LoginDialog;
import com.agung.inventory.ui.MainFrame;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author agung
 */
public class SecurityConfig {

    @Getter
    @Setter
    private static Petugas activePetugas;

    @Setter
    private static MainFrame mainFrame;

    public static void initLogin() {
        boolean notLogin = Boolean.TRUE;
        while (notLogin) {
            notLogin = new LoginDialog().showDialog();
        }
        mainFrame.getTxtUser().setText(activePetugas.getNama());
        mainFrame.setVisible(true);
    }

}
