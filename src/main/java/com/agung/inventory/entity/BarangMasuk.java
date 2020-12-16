/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agung
 */

@Getter
@Setter
@Entity
@Table(name = "barang_masuk")
public class BarangMasuk extends BaseEntity {

    @Column(name = "no_transaksi", nullable = false, unique = true)
    private String kode;

    @Column(name = "tanggal", columnDefinition = "TIMESTAMP")
    private LocalDateTime tanggalMasuk;

    @ManyToOne
    @JoinColumn(name = "id_petugas", nullable = false)
    private Petugas petugas;

    @ManyToOne
    @JoinColumn(name = "id_supplier", nullable = false)
    private Supplier supplier;

    @Column(name = "keterangan", nullable = true)
    private String keterangan;

    @OneToMany(mappedBy = "barangMasuk",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<BarangMasukDetail> barangMasukDetails =
            new ArrayList<>();

}
