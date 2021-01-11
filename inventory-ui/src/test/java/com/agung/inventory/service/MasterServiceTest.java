package com.agung.inventory.service;


import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.Kategori;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MasterServiceTest extends BaseTest {

    @Test
    public void deleteTest() throws SQLException {
        List<Barang> barangList = masterService.findAllItem();
        for (Barang b : barangList) {
            masterService.delete(b);
        }
        barangList = masterService.findAllItem();
        assertEquals(0, barangList.size());
    }

    @Test
    void saveBarang() throws SQLException {
        List<Kategori> kategoris = masterService.findAllCategori();
        for (int i = 1; i <= 10; i++) {
            Barang barang = new Barang();
            barang.setKodeBarang("B-00" + i);
            barang.setNamaBarang("barang test " + i);
            barang.setKategori(kategoris.get(0));
            barang.setActive(true);
            barang.setSatuan("UNIT");
            barang.setQty(new BigDecimal(0));
            masterService.saveBarang(barang);
        }
        int result = masterService.findAllItem().size();
        assertEquals(10, result);
        assertFalse(masterService.findAllItem().isEmpty());
    }

    @Test
    void findItemByParam() {
        List<Barang>barangList = masterService.findItemByParam("kode","B-001");
        assertFalse(barangList.isEmpty());
        assertEquals(1,barangList.size());

        List<Barang>barangList2 = masterService.findItemByParam("kode","B-00xx");
        assertTrue(barangList2.isEmpty());
        assertEquals(0,barangList2.size());

        List<Barang>list3 = masterService.findItemByParam("nama","barang test 1");
        for (Barang b:list3){
            System.out.println(b.getNamaBarang());
        }
        assertFalse(list3.isEmpty());
        assertEquals(2,list3.size());
        assertEquals("barang test 1", list3.get(0).getNamaBarang());
    }

}