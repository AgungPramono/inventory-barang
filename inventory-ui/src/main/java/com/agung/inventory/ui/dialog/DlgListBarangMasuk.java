/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.ui.dialog;

import com.agung.inventory.Main;
import com.agung.inventory.config.AppContext;
import com.agung.inventory.entity.BarangMasuk;
import com.agung.inventory.entity.BarangMasukDetail;
import com.agung.inventory.entity.Petugas;
import com.agung.inventory.entity.Supplier;
import com.agung.inventory.ui.combo.model.PetugasComboModel;
import com.agung.inventory.ui.combo.model.SupplierComboModel;
import com.agung.inventory.ui.tablemodel.BarangMasukDetailTableModel;
import com.agung.inventory.ui.tablemodel.BarangMasukTableModelMaster;
import com.agung.inventory.util.DateUtil;
import com.agung.inventory.util.TableUtil;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.springframework.util.StringUtils;

/**
 *
 * @author agung
 */
public class DlgListBarangMasuk extends javax.swing.JDialog {

    private static DlgListBarangMasuk singleton;
    private List<BarangMasuk> listBarangMasuk;
    private BarangMasuk barangMasuk;
    private final BarangMasukDetailTableModel barangMasukDetailTableModel = new BarangMasukDetailTableModel();

    public DlgListBarangMasuk() {
        super(Main.getMainFrame(), true);
        initComponents();
        initForm();
    }

    private void initForm() {
        setTitle("Daftar Barang Masuk");
        LocalDateTime dateNow = LocalDateTime.now();
        setLocationRelativeTo(null);
        tblBarangMasuk.getSelectionModel().addListSelectionListener(new TableSelection());
        dateChooser.setDate(DateUtil.toDate(dateNow));
        loadComboSupplier();
        loadComboPetugas();
        chEnableDate1.setSelected(false);
        dateChooser.setEnabled(false);
        dateChooser.setDate(null);
    }

    private void loadComboSupplier() {
        cmbSupplier.setModel(new SupplierComboModel(AppContext.getMasterService().findAllSupplier()));
    }

    private void loadComboPetugas() {
        cmbPetugas.setModel(new PetugasComboModel(AppContext.getMasterService().findAllEmployee()));
    }

    public static synchronized DlgListBarangMasuk getSingleton() {
        if (singleton == null) {
            singleton = new DlgListBarangMasuk();
        }
        return singleton;
    }

    public void showDialog() {
        initDataTable();
        this.setVisible(true);
    }

    private void initDataTable() {
        listBarangMasuk = AppContext.getTransactionService().findBarangMasukMaster();
        initTable(listBarangMasuk);
    }

    private void initTable(List<BarangMasuk> listData) {
        if (!listData.isEmpty()) {
            tblBarangMasuk.setModel(new BarangMasukTableModelMaster(listData));
        } else {
            tblBarangMasuk.setModel(new BarangMasukTableModelMaster(new ArrayList<>()));
        }
        TableUtil.initColumn(tblBarangMasuk,jScrollPane2);
    }

    private void refresh() {
        if (dateChooser.getDate() != null && dateChooser.isEnabled()) {
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
            listBarangMasuk = AppContext.getTransactionService().findBarangMasukByParam("tanggal", tanggal);
        }

        if (!StringUtils.isEmpty(txtKode.getText())) {
            listBarangMasuk = AppContext.getTransactionService().findBarangMasukByParam("kode", txtKode.getText());
        }

        if (cmbSupplier.getSelectedItem() != null) {
            Supplier s = (Supplier) cmbSupplier.getSelectedItem();
            listBarangMasuk = AppContext.getTransactionService().findBarangMasukByParam("supplier", s.getNama());
        }

        if (cmbPetugas.getSelectedItem() != null) {
            Petugas p = (Petugas) cmbPetugas.getSelectedItem();
            listBarangMasuk = AppContext.getTransactionService().findBarangMasukByParam("petugas", p.getNama());
        }

        if (StringUtils.isEmpty(txtKode.getText())
                && dateChooser.getDate() == null
                && cmbSupplier.getSelectedItem() == null
                && cmbPetugas.getSelectedItem() == null) {
            listBarangMasuk = AppContext.getTransactionService().findBarangMasukMaster();
        }

        initTable(listBarangMasuk);
    }

    private void loadDetailById(Integer idMaster) {
        List<BarangMasukDetail> listDetail = AppContext.getTransactionService().findAllBarangMasukDetailByIdMaster(idMaster);
        loadDetail(listDetail);
    }

    private void loadDetail(List<BarangMasukDetail> listDetail) {
        if (!listDetail.isEmpty()) {
            barangMasukDetailTableModel.setData(listDetail);
            tblDetail.setModel(barangMasukDetailTableModel);
        } else {
            barangMasukDetailTableModel.setData(new ArrayList<>());
            tblDetail.setModel(barangMasukDetailTableModel);
        }
        TableUtil.initColumn(tblDetail,jScrollPane3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBarangMasuk = new com.agung.inventory.ui.component.CustomTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetail = new com.agung.inventory.ui.component.CustomTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        chEnableDate1 = new javax.swing.JCheckBox();
        cmbPetugas = new com.agung.inventory.ui.component.CustomComboUI();
        cmbSupplier = new com.agung.inventory.ui.component.CustomComboUI();
        btnRefresh = new com.agung.inventory.ui.component.PrimaryButton();
        btnAdd = new com.agung.inventory.ui.component.PrimaryButton();
        btnCetak = new com.agung.inventory.ui.component.PrimaryButton();
        btnKeluar = new com.agung.inventory.ui.component.DangerButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

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
        tblBarangMasuk.setRowHeight(30);
        tblBarangMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMasukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBarangMasuk);

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDetail.setRowHeight(30);
        tblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetail);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Tanggal");

        dateChooser.setDateFormatString("dd MMMM yyyy");

        jLabel2.setText("Kode");

        txtKode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKodeMouseClicked(evt);
            }
        });

        jLabel3.setText("Supplier");

        jLabel4.setText("Petugas");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setText("LIST TRANSAKSI MASUK");

        chEnableDate1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chEnableDate1ItemStateChanged(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCetak.setText("Cetak Laporan");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(chEnableDate1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                                    .addComponent(txtKode)
                                    .addComponent(cmbSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(342, 342, 342)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cmbPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(chEnableDate1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chEnableDate1, cmbSupplier, dateChooser, jLabel1, jLabel2, jLabel3, txtKode});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbPetugas, jLabel4});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtKodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKodeMouseClicked
        txtKode.selectAll();
    }//GEN-LAST:event_txtKodeMouseClicked

    private void chEnableDate1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chEnableDate1ItemStateChanged
        if (chEnableDate1.isSelected()) {
            dateChooser.setDate(new Date());
            dateChooser.setEnabled(true);
        } else {
            dateChooser.setDate(null);
            dateChooser.setEnabled(false);
        }
    }//GEN-LAST:event_chEnableDate1ItemStateChanged

    private void tblBarangMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMasukMouseClicked
        if (evt.getClickCount() == 2) {
            printReport(barangMasuk.getId());
        }
    }//GEN-LAST:event_tblBarangMasukMouseClicked

    private void tblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDetailMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        DlgBarangMasuk dlg = DlgBarangMasuk.getInstance();
        dlg.showDialog();
        initDataTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
         if (tblBarangMasuk.getSelectedRow() >= 0 && barangMasuk != null) {
            printReport(barangMasuk.getId());
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
      this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void printReport(Integer id) {
        DlgViewLaporan.getSingleton()
                .showDialog(AppContext.getReportService().generateLaporanMasukById(id), "Laporan Barang Masuk");
    }

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tblBarangMasuk.getSelectedRow() >= 0) {
                barangMasuk = listBarangMasuk.get(tblBarangMasuk.getSelectedRow());
                loadDetailById(barangMasuk.getId());
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.agung.inventory.ui.component.PrimaryButton btnAdd;
    private com.agung.inventory.ui.component.PrimaryButton btnCetak;
    private com.agung.inventory.ui.component.DangerButton btnKeluar;
    private com.agung.inventory.ui.component.PrimaryButton btnRefresh;
    private javax.swing.JCheckBox chEnableDate1;
    private com.agung.inventory.ui.component.CustomComboUI cmbPetugas;
    private com.agung.inventory.ui.component.CustomComboUI cmbSupplier;
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.agung.inventory.ui.component.CustomTable tblBarangMasuk;
    private com.agung.inventory.ui.component.CustomTable tblDetail;
    private javax.swing.JTextField txtKode;
    // End of variables declaration//GEN-END:variables
}
