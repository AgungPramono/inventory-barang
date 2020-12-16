/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.dialog;

import com.agung.inventory.config.AppContext;
import com.agung.inventory.config.SecurityConfig;
import com.agung.inventory.entity.*;
import com.agung.inventory.ui.combo.model.SupplierComboModel;
import com.agung.inventory.ui.tablemodel.BarangMasukTableModel;
import com.agung.inventory.util.DateUtil;
import com.agung.inventory.util.NumberUtil;
import com.agung.inventory.util.TableUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author agung
 */
public class DlgBarangMasuk extends javax.swing.JDialog {

    private static DlgBarangMasuk INSTANCE;
    private BarangMasuk barangMasuk;
    private BarangMasukDetail barangMasukDetail;
    private List<BarangMasukDetail> barangMasukDetails = new ArrayList<>();
    private Petugas petugas;

    public DlgBarangMasuk() {
        super(new JFrame(), true);
        initComponents();
        setLocationRelativeTo(null);
        initForm();
    }

    private void initForm() {
        LocalDateTime dateNow = LocalDateTime.now();
        loadSupplierCombo();
        jdate.setDate(DateUtil.toDate(dateNow));
        txtKodeTransaksi.setText(NumberUtil.generateTransactionCode());

        tblBarangMasuk.getSelectionModel().addListSelectionListener(new TableSelection());
        txtPetugas.setText(SecurityConfig.getActivePetugas().getNama());
    }

    public static synchronized DlgBarangMasuk getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DlgBarangMasuk();
        }
        
        return INSTANCE;
    }

    private void refreshTable() {
        tblBarangMasuk.setModel(new BarangMasukTableModel(barangMasukDetails));
        TableUtil.initColumn(tblBarangMasuk);
    }

    public void showDialog() {
        initForm();
        this.setVisible(true);
    }

    private void loadSupplierCombo() {
        cmbSupplier.setModel(new SupplierComboModel(AppContext.getMasterService().findAllSupplier()));
    }

    private void loadFormToDomain() {
        barangMasuk.setBarangMasukDetails(barangMasukDetails);
        barangMasuk.setTanggalMasuk(LocalDateTime.now());
        barangMasuk.setKode(txtKodeTransaksi.getText());
        barangMasuk.setPetugas(SecurityConfig.getActivePetugas());
        barangMasuk.setSupplier((Supplier) cmbSupplier.getSelectedItem());
        barangMasuk.setKeterangan(txtKeterangan.getText());
        for (BarangMasukDetail bmd:barangMasukDetails){
            bmd.setBarangMasuk(barangMasuk);
        }
    }

    private void addBarangMasukDetail(Barang barang) {
        if (barang != null) {
            BarangMasukDetail detail = new BarangMasukDetail();
            detail.setBarang(barang);
            detail.setQty(BigDecimal.ONE);
            barangMasukDetails.add(detail);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Barang tidak ada!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm(){
        barangMasukDetails = new ArrayList<>();
        barangMasuk = null;
        txtNamaBarang.setText("");
        txtKodeTransaksi.setText(NumberUtil.generateTransactionCode());
        refreshTable();
    }
    
    private Boolean isFormValid(){
        return cmbSupplier.getSelectedItem() != null
                && !barangMasukDetails.isEmpty();
    }
    
    private boolean cekItemExisted(Barang barang){
        Boolean retval = true;
        for(BarangMasukDetail detail:barangMasukDetails){
            if(detail.getBarang().getKodeBarang().equalsIgnoreCase(barang.getKodeBarang())){
                JOptionPane.showMessageDialog(this, "Barang sudah dipilih !");
                retval = false;
                break;
            }
        }
        return retval;
    }
    
    private void addBarang(){
        Barang barang = new LookupBarangDialog().showDialog();
        if (barang != null) {
            txtNamaBarang.setText(barang.getKodeBarang() + "|" + barang.getNamaBarang());
            boolean isBarangExist = false;
            for (BarangMasukDetail bmd : barangMasukDetails) {
                if (bmd.getBarang().getId().equals(barang.getId())) {
                    bmd.setQty(bmd.getQty().add(BigDecimal.ONE));
                    isBarangExist = true;
                    break;
                }
            }
            if (isBarangExist) {
                refreshTable();
            } else {
                addBarangMasukDetail(barang);
            }
        }
    }

    private void removeAllDetail(){
        if (barangMasukDetails.size() > 0) {
            barangMasukDetails.clear();
            refreshTable();
        }
    }

    private void removeSelectedDetail(){
        if (tblBarangMasuk.getSelectedRow() >= 0 && barangMasukDetail != null) {
            if (barangMasukDetail != null) {
                barangMasukDetails.remove(barangMasukDetail);
            }
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Tidak ada data dipilih !!", "Terjadi Kesalahan !!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void save(){
        if (!isFormValid()) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan,Data Belum Lengkap",
                    "Gagal", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (barangMasuk == null) {
            barangMasuk = new BarangMasuk();
        }
        loadFormToDomain();
        try {
            AppContext.getTransactionService().saveInTransaction(barangMasuk);
            clearForm();
            JOptionPane.showMessageDialog(this, "Data Berhasil disimpan!",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtNamaBarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarangMasuk = new javax.swing.JTable();
        btnHapusSemua = new javax.swing.JButton();
        btnHapusDipilih = new javax.swing.JButton();
        btnAddBarang = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKodeTransaksi = new javax.swing.JTextField();
        txtPetugas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbSupplier = new com.agung.inventory.ui.component.CustomComboUI();
        jdate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI BARANG MASUK");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        tblBarangMasuk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tblBarangMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblBarangMasuk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblBarangMasuk.setRowHeight(40);
        jScrollPane1.setViewportView(tblBarangMasuk);

        btnHapusSemua.setText("Hapus Semua");
        btnHapusSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusSemuaActionPerformed(evt);
            }
        });

        btnHapusDipilih.setText("Hapus Yang Terpilih");
        btnHapusDipilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusDipilihActionPerformed(evt);
            }
        });

        btnAddBarang.setText("Tambah Barang");
        btnAddBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnHapusSemua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapusDipilih))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddBarang)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddBarang))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapusSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusDipilih))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddBarang, txtNamaBarang});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusDipilih, btnHapusSemua});

        jButton4.setText("Simpan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Tutup");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel5AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel5.setText("Kode Transaksi");

        jLabel2.setText("Petugas");

        txtKodeTransaksi.setEnabled(false);

        txtPetugas.setEnabled(false);

        jLabel3.setText("Supplier");

        jdate.setDateFormatString("dd MMMM yyyy HH:ss");
        jdate.setEnabled(false);

        jLabel4.setText("Tanggal");

        jLabel6.setText("Keterangan");

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane2.setViewportView(txtKeterangan);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbSupplier, txtPetugas});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(38, 38, 38))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtKodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbSupplier, jLabel2, jLabel3, jLabel4, jLabel5, jdate, txtPetugas});

        jTabbedPane1.addTab("Master", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addGap(9, 9, 9))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton5});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton4, jButton5});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBarangActionPerformed
        addBarang();
    }//GEN-LAST:event_btnAddBarangActionPerformed

    private void btnHapusSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusSemuaActionPerformed
        removeAllDetail();
    }//GEN-LAST:event_btnHapusSemuaActionPerformed

    private void btnHapusDipilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusDipilihActionPerformed
        removeSelectedDetail();
    }//GEN-LAST:event_btnHapusDipilihActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        save();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       this.dispose();
       clearForm();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel5AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5AncestorAdded

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tblBarangMasuk.getSelectedRow() >= 0) {
                barangMasukDetail = barangMasukDetails.get(tblBarangMasuk.getSelectedRow());
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBarang;
    private javax.swing.JButton btnHapusDipilih;
    private javax.swing.JButton btnHapusSemua;
    private com.agung.inventory.ui.component.CustomComboUI cmbSupplier;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdate;
    private javax.swing.JTable tblBarangMasuk;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKodeTransaksi;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtPetugas;
    // End of variables declaration//GEN-END:variables
}
