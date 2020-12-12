package com.agung.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "petugas")
public class Petugas extends BaseEntity{
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
     @Override
    public String toString() {
        return nama;
    }
    
}

