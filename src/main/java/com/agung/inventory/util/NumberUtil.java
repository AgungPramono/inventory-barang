/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.util;

import java.util.UUID;

/**
 *
 * @author agung
 */
public class NumberUtil {

    public static String generateTransactionCode() {
        String ts = String.valueOf(System.currentTimeMillis());
        String rand = UUID.randomUUID().toString();
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(ts+rand);
    }
}
