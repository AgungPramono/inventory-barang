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
public class Supplier {
    
    private Integer id;
    private String kode;
    private String name;
    private String alamat;
    private String noTelpon;

    @Override
    public String toString() {
        return kode+"|"+name;
    }
    
    
}
