/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.component;

import java.awt.Component;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

/**
 *
 * @author agung
 */
public class DialogMgr {
    public static void showErrorDialog(Component owner,String title,String basicInfo,String detailInfo,Exception ex){
        ErrorInfo info = new ErrorInfo(title, basicInfo, detailInfo, null, ex,null, null);
        JXErrorPane.showDialog(owner, info);
    }
}
