/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author agung
 */

@Data
public class BarangKeluarDetail {
    private Integer id;
    private BarangKeluar barangKeluar;
    private Barang barang;
    private BigDecimal qty;
}
