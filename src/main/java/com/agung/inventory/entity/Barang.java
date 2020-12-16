/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author agung
 */
@Setter
@Getter
@Entity
@Table(name = "barang")
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
}
