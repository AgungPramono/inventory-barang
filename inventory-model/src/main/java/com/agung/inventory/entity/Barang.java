/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author agung
 */
@Setter
@Getter
@Entity
@Table(name = "barang",
        indexes = {
            @Index(
                    name = "index_kode_nama_qty",
                    columnList = "kode,nama,qty",
                    unique = true
            )
        }

)
public class Barang extends BaseEntity{
    
    @Column(name = "kode", nullable = false)
    private String kodeBarang;
    @Column(name = "nama", nullable = false)
    private String namaBarang;
    @Column(name = "qty", nullable = false)
    private BigDecimal qty;
    
    @ManyToOne
    @JoinColumn(name = "id_kategori", nullable = false)
    private Kategori kategori;
    
    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "satuan", nullable = true)
    private String satuan;
    
    @Column(name = "active")
    private Boolean active;
}
