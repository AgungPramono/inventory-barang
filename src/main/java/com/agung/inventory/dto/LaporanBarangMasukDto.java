package com.agung.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LaporanBarangMasukDto {

    private LocalDateTime tanggal;
    private String petugas;
    private String supplier;
    private String nama;
    private Integer stok;
    private String kode;

}
