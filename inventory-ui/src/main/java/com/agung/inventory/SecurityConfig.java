/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory;

import com.agung.inventory.config.AppContext;
import com.agung.inventory.entity.Petugas;
import com.agung.inventory.ui.LoginDialog;
import com.agung.inventory.ui.MainFrame;
import com.agung.inventory.util.PasswordHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
            List<Petugas> p = AppContext.getMasterService().findAllEmployee();
            if (p.isEmpty()){
                Petugas petugas = new Petugas();
                petugas.setNama("admin");
                petugas.setPassword(PasswordHelper.getEncryptedTextFromPlainText("admin123"));
                petugas.setUsername("admin");
                petugas.setActive(true);
                AppContext.getMasterService().saveEmployee(petugas);
            }
            notLogin = new LoginDialog().showDialog();
        }
        mainFrame.getTxtUser().setText(activePetugas.getNama());
        mainFrame.setVisible(true);
    }

}
