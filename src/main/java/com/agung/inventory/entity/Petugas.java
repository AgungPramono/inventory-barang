package com.agung.inventory.entity;

import lombok.Data;

@Data
public class Petugas {
    private Integer id;
    private String nama;
    private String username;
    private String password;
    private boolean active;
}

