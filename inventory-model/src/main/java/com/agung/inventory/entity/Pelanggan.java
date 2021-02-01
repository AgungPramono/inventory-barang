/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author agung
 */
@Getter
@Setter
@Entity
@Table(name = "pelanggan")
public class Pelanggan extends BaseEntity {

    @Column(name = "kode", nullable = false, unique = true)
    private String kode;
    @Column(name = "nama", nullable = false)
    private String nama;
    @Column(name = "telepon", nullable = false)
    private String telepon;
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Override
    public String toString() {
        return nama;
    }
    
    
}
