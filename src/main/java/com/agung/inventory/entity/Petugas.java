package com.agung.inventory.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Petugas {
    private Integer id;
    private String nama;
    private String username;
    private String password;
    private boolean active;
    
     @Override
    public String toString() {
        return nama;
    }
    
}

