/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author agung
 */
@Getter
@Setter
@Entity
@Table(name = "kategori")
public class Kategori extends BaseEntity{
    
    @Column(name = "kode", nullable = false)
    private String kode;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @OneToMany(mappedBy = "kategori",cascade = CascadeType.ALL)
    private List<Barang> barangs;

    @Override
    public String toString() {
        return nama;
    }
    
    
    
}