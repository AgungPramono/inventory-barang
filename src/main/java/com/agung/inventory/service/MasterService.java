package com.agung.inventory.service;

import com.agung.inventory.dao.*;
import com.agung.inventory.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
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
        barangDao.save(barang);
    }

    public List<Barang> findAllBarang(){
        return barangDao.findAll();
    }
    public Barang findById(Barang barang)throws SQLException{
        return barangDao.findById(barang);
    }

    public void delete(Barang barang)throws SQLException{
        barangDao.deleteById(barang);
    }

    public List<Kategori> findAllCategori() {
        return kategoriDao.findAll();
    }

    public void deleteById(Kategori kategori) throws SQLException {
        kategoriDao.deleteById(kategori);
    }

    public void saveCategory(Kategori kategori) throws SQLException {
        kategoriDao.save(kategori);
    }

    public Petugas findEmployeeByUsername(String username){
        return petugasDao.cariByUsername(username);
    }

    public List<Petugas> findAllEmployee() {
        return petugasDao.findAll();
    }

    public void deleteEmployee(Petugas petugas) {
        petugasDao.deleteById(petugas);
    }

    public void saveEmployee(Petugas petugas) {
        petugasDao.save(petugas);
    }

    public List<Supplier> findAllSupplier() {
        return supplierDao.findAll();
    }

    public void saveSupplier(Supplier supplier) {
        supplierDao.save(supplier);
    }

    public void deleteSupplierById(Supplier supplier) {
        supplierDao.deleteById(supplier);
    }

    public List<Pelanggan> findAllCustomer() {
        return pelangganDao.findAll();
    }

    public void saveCustomer(Pelanggan pelanggan) throws SQLException {
        pelangganDao.save(pelanggan);
    }

    public void deleteCustomerById(Pelanggan pelanggan) {
        pelangganDao.deleteById(pelanggan);
    }

    public List<Barang> findItemByName(String text) {
        return itemDao.cariBarangByName(text);
    }
}
