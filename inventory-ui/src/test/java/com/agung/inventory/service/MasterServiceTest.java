package com.agung.inventory.service;


import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.Kategori;
import com.agung.inventory.entity.Petugas;
import com.agung.inventory.entity.Supplier;
import com.agung.inventory.util.PasswordHelper;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tags({
        @Tag("master-service"),
        @Tag("integration-test")
})
@DisplayName("test modul master service")
public class MasterServiceTest extends BaseTest {

    @Test
    @DisplayName("test hapus barang")
    public void deleteTest() throws SQLException {
        List<Barang> barangList = masterService.findAllItem();
        for (Barang b : barangList) {
            masterService.delete(b);
        }
        barangList = masterService.findAllItem();
        assertEquals(0, barangList.size());
    }

    @Test
    @DisplayName("test simpan barang")
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
    @DisplayName("test cari barang by parameter kolom dan value")
    void findItemByParam() {
        List<Barang> barangList = masterService.findItemByParam("kode", "B-001");
        assertFalse(barangList.isEmpty());
        assertEquals(1, barangList.size());

        List<Barang> barangList2 = masterService.findItemByParam("kode", "B-00xx");
        assertTrue(barangList2.isEmpty());
        assertEquals(0, barangList2.size());

        List<Barang> list3 = masterService.findItemByParam("nama", "barang test 1");
        for (Barang b : list3) {
            System.out.println(b.getNamaBarang());
        }
        assertFalse(list3.isEmpty());
        assertEquals(2, list3.size());
        assertEquals("barang test 1", list3.get(0).getNamaBarang());
    }

    @Test
    @DisplayName("test simpan kategori")
    void saveCategory() throws SQLException {
        for (int i = 0; i <= 10; i++) {
            Kategori kategori = new Kategori();
            kategori.setKode("k-00" + i);
            kategori.setNama("Kategori " + i);
            masterService.saveCategory(kategori);
        }
        List<Kategori> kategoris = masterService.findAllCategori();
        assertEquals(11, kategoris.size());
        assertNotEquals(0, kategoris.size());
        assertFalse(kategoris.isEmpty());
    }

    @Test
    @DisplayName("test hapus kategori")
    void testDeleteKategori() throws SQLException {
        List<Kategori> result = masterService.findAllCategori();
        for (Kategori k:result){
            masterService.deleteById(k);
        }
        result = masterService.findAllCategori();
        assertEquals(0, result.size());
        assertNotEquals(10, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("test simpan petugas")
    void saveEmployee() {
        for(int i=0; i<10; i++){
            Petugas petugas = new Petugas();
            petugas.setNama("Petugas "+i);
            petugas.setUsername("petugas_"+i);
            petugas.setPassword(PasswordHelper.getEncryptedTextFromPlainText("petugas"+i));
            petugas.setActive(true);
            masterService.saveEmployee(petugas);
        }

        List<Petugas> result = masterService.findAllEmployee();
        assertEquals(10, result.size());
        assertNotEquals(0, result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("test hapus petugas")
    void deleteEmployee() throws Exception {
        List<Petugas> result = masterService.findAllEmployee();
        for (Petugas petugas:result){
            masterService.deleteEmployee(petugas);
        }
        result = masterService.findAllEmployee();
        assertEquals(0, result.size());
        assertNotEquals(10, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    void testInsertSupplier(){
        for (int i=0; i<10; i++){
            Supplier supplier = new Supplier();
            supplier.setKode("s-00"+i);
            supplier.setNama("Supplier "+i);
            supplier.setAlamat("alamat "+i);
            supplier.setTelepon("08042"+i);
            masterService.saveSupplier(supplier);
        }

        List<Supplier> result = masterService.findAllSupplier();
        assertEquals(10, result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void deleteSupplier(){
        List<Supplier> result = masterService.findAllSupplier();
        for (Supplier s:result){
            masterService.deleteSupplierById(s);
        }
        result = masterService.findAllSupplier();
        assertTrue(result.isEmpty());
        assertNotEquals(10, result.size());
    }

    @Test
    @Disabled
    @DisplayName("test pencarian supplier dengan parameter kolom dan valuenya")
    void testFindSupplierByParam(){
        List<Supplier> resultSupplier = masterService.findSupplierByParam("kode", "S-001");
                assertFalse(resultSupplier.isEmpty());
        assertEquals(1, resultSupplier.size());

        List<Supplier> resultSupplier2 = masterService.findSupplierByParam("kode", "S-00xx");
        assertTrue(resultSupplier2.isEmpty());
        assertEquals(0, resultSupplier2.size());

        List<Supplier> result = masterService.findSupplierByParam("nama", "Supplier 1");
        for (Supplier supplier : result) {
            System.out.println(supplier.getNama());
        }
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("barang test 1", result.get(0).getNama());
    }
}