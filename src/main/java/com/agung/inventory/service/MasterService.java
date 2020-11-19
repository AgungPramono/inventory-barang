package com.agung.inventory.service;

import com.agung.inventory.dao.BarangDao;
import com.agung.inventory.dao.BarangMasukDetailDao;
import com.agung.inventory.entity.Barang;
import com.agung.inventory.entity.BarangMasukDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MasterService {

    @Autowired
    private BarangMasukDetailDao barangMasukDetailDao;

    @Autowired
    private BarangDao barangDao;

    public void save(Barang barang) throws SQLException {
        barangDao.simpan(barang);
    }

    public List<BarangMasukDetail> findAll(){
        return barangMasukDetailDao.cariSemua();
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


}
