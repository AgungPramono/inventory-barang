/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author agung
 */

@Data
public class BarangKeluar {
    private Integer id;
    private String kode;
    private LocalDateTime tanggalMasuk;
    private Petugas petugas;
    private Pelanggan pelanggan;
    private List<BarangKeluarDetail> barangKeluarDetails = 
            new ArrayList<>();
    
}
