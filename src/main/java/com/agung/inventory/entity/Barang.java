/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author agung
 */

@Data
@Entity
@Table(name = "barang")
public class Barang extends BaseEntity{
    
    @Column(name = "kode", nullable = false)
    private String kodeBarang;
    @Column(name = "nama", nullable = false)
    private String namaBarang;
    @Column(name = "qty", nullable = false)
    private BigDecimal qty;
    
    @OneToMany
    @JoinColumn(name = "id_kategori", nullable = false)
    private Kategori kategori;
    
    @Column(name = "keterangan")
    private String keterangan;
    
}
