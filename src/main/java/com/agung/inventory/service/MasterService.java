package com.agung.inventory.service;

import com.agung.inventory.dao.*;
import com.agung.inventory.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class MasterService {

    @Autowired
    private BarangDao barangDao;

    @Autowired
    private PetugasDao petugasDao;

    @Autowired
    private KategoriDao kategoriDao;

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private PelangganDao pelangganDao;

    @Autowired
    private BarangDao itemDao;

    public void saveBarang(Barang barang) throws SQLException {
        if (barang == null) {
            return;
        }
        barangDao.save(barang);
    }

    public List<Barang> findAllBarang() {
        return barangDao.findAll();
    }

    public Barang findById(Barang barang) throws SQLException {
        return barangDao.findById(barang);
    }

    public void delete(Barang barang) throws SQLException {
        Barang b = barangDao.findById(barang);
        if (b != null) {
            barangDao.deleteById(b);
        }
    }

    public List<Kategori> findAllCategori() {
        return kategoriDao.findAll();
    }

    public void deleteById(Kategori kategori) throws SQLException {
        Kategori k = kategoriDao.findById(kategori);
        if (k != null) {
            kategoriDao.deleteById(k);
        }
    }

    public void saveCategory(Kategori kategori) throws SQLException {
        if (kategori != null) {
            kategoriDao.save(kategori);
        }
    }

    public Petugas findEmployeeByUsername(String username) {
        return petugasDao.cariByUsername(username);
    }

    public List<Petugas> findAllEmployee() {
        return petugasDao.findAll();
    }

    public void deleteEmployee(Petugas petugas) {
        Petugas p = petugasDao.findById(petugas);
        if (p != null) {
            petugasDao.deleteById(p);
        }
    }

    public void saveEmployee(Petugas petugas) {
        if (petugas != null) {
            petugasDao.save(petugas);
        }
    }

    public List<Supplier> findAllSupplier() {
        return supplierDao.findAll();
    }

    public void saveSupplier(Supplier supplier) {
        if (supplier != null) {
            supplierDao.save(supplier);
        }
    }

    public void deleteSupplierById(Supplier supplier) {
        Supplier s = supplierDao.findById(supplier);
        if (s != null) {
            supplierDao.deleteById(s);
        }
    }

    public List<Pelanggan> findAllCustomer() {
        return pelangganDao.findAll();
    }

    public void saveCustomer(Pelanggan pelanggan) throws SQLException {
        if (pelanggan != null) {
            pelangganDao.save(pelanggan);
        }
    }

    public void deleteCustomerById(Pelanggan pelanggan) {
        Pelanggan p = pelangganDao.findById(pelanggan);
        if (p != null) {
            pelangganDao.deleteById(p);
        }
    }

    public List findItemByName(String text) {
        return itemDao.cariBarangByName(text);
    }
}
