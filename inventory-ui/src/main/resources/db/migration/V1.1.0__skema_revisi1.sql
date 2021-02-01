alter table barang_masuk add column keterangan VARCHAR(255);
alter table barang_keluar add column keterangan VARCHAR(255);

alter table barang add column satuan VARCHAR(255) not null;
