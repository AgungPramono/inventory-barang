/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author agung
 */
@Getter
@Setter
public class Pelanggan {
    
    private Integer id;
    private String kode;
    private String nama;
    private String telepon;
    private String alamat;

    @Override
    public String toString() {
        return nama;
    }
    
    
}
