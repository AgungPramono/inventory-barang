/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agung
 */

@Data
public class BarangMasuk {
    
    private Integer id;
    private LocalDateTime tanggalMasuk;
    private Petugas petugas;
    private Supplier supplier;
    private List<BarangMasukDetail> barangMasukDetails = 
            new ArrayList<>();
    
}
