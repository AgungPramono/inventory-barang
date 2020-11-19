/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author agung
 */

@Data
public class BarangMasukDetail {
    
    private Integer id;
    private BarangMasuk barangMasuk;
    private Barang barang;
    private BigDecimal qty;
    
}
