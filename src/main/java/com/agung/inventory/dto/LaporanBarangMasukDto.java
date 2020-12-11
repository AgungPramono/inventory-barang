package com.agung.inventory.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

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
