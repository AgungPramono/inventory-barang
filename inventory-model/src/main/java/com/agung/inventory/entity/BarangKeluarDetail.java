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

@Getter
@Setter
@Entity
@Table(name = "barang_keluar_detail")
public class BarangKeluarDetail extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_header", nullable = false)
    private BarangKeluar barangKeluar;

    @ManyToOne
    @JoinColumn(name = "id_barang", nullable = false)
    private Barang barang;

    @Column(name = "qty", nullable = false)
    private BigDecimal qty;
}