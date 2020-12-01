/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author agung
 */

@Data
public class Barang {
    
    private Integer id;
    private String kodeBarang;
    private String namaBarang;
    private BigDecimal qty;
    private Kategori kategori;
    private String keterangan;
    
}
