/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.dto;

import lombok.Data;

/**
 *
 * @author agung
 */

@Data
public class LaporanBarangDto {
    
    private String kode;
    private String nama;
    private Integer stok;
    private String kategori;
    private String keterangan;
    
}
