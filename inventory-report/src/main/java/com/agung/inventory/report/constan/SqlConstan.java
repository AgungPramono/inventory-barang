/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.report.constan;

/**
 *
 * @author agung
 */
public class SqlConstan {
    public static final String QUERY_REPORT_BARANG_MASUK =
                    "select b.kode,b.nama as nama_barang,bm.id as kode_master,bm.no_transaksi,bm.tanggal,"
                    + "p.nama as nama_petugas,s.nama as nama_supplier,bmd.qty "
                    + "from barang_masuk bm "
                    + "join barang_masuk_detail bmd on bm.id = bmd.id_header "
                    + "join barang b on bmd.id_barang = b.id "
                    + "join petugas p on p.id = bm.id_petugas "
                    + "join supplier s on s.id = bm.id_supplier " ;
    
    public static final String QUERY_REPORT_BARANG_KELUAR =
                    "select b.kode,b.nama as nama_barang,bm.id as kode_master,bm.no_transaksi,bm.tanggal,"
                    + "p.nama as nama_petugas,s.nama as nama_supplier,bmd.qty "
                    + "from barang_keluar bm "
                    + "join barang_keluar_detail bmd on bm.id = bmd.id_header "
                    + "join barang b on bmd.id_barang = b.id "
                    + "join petugas p on p.id = bm.id_petugas "
                    + "join pelanggan s on s.id = bm.id_pelanggan " ;
}
