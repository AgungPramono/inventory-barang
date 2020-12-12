package com.agung.inventory.service;

import com.agung.inventory.dao.BarangDao;
import com.agung.inventory.dao.KategoriDao;
import com.agung.inventory.dao.PetugasDao;
import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.Kategori;
import com.agung.inventory.entity.Petugas;
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

    public void saveBarang(Barang barang) throws SQLException {
        barangDao.simpan(barang);
    }

    public List<Barang> findAllBarang(){
        return barangDao.cariSemua();
    }
    public Barang findById(Barang barang)throws SQLException{
        return barangDao.cariById(barang);
    }

    public void delete(Barang barang)throws SQLException{
        barangDao.deleteById(barang);
    }

    public List<Kategori> findAllCategori() {
        return kategoriDao.cariSemua();
    }

    public void deleteById(Kategori kategori) throws SQLException {
        kategoriDao.deleteById(kategori);
    }

    public void simpan(Kategori kategori) throws SQLException {
        kategoriDao.simpan(kategori);
    }

    public Petugas findEmployeeByUsername(String username){
        return petugasDao.cariByUsername(username);
    }

    public List<Petugas> findAllEmployee() {
        return petugasDao.cariSemua();
    }

    public void deleteEmployee(Petugas petugas) {
        petugasDao.deleteById(petugas);
    }

    public void saveEmployee(Petugas petugas) {
        petugasDao.simpan(petugas);
    }
}
