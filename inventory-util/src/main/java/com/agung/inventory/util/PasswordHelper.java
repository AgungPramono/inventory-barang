/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author agungpermadi13@gmail.com
 */
public class PasswordHelper {
    
    public static final String keyPassword = "zh1BRrQPu985xU3PvSWFd9ni8eWwa9Z7VxpuW3mP6ls=";

    public static String getPlainTextFromEncryptedText(String encryptedPassword) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(keyPassword);
        String passEncrypt = encryptedPassword.substring(4, encryptedPassword.length() - 1);

        return encryptor.decrypt(passEncrypt);
    }

    public static String getEncryptedTextFromPlainText(String plainText) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(keyPassword);
        String encryptedPassword = "ENC(" + encryptor.encrypt(plainText) + ")";
        
        return encryptedPassword;
    }
}
