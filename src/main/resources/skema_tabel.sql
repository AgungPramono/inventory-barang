create table kategori(
    id INTEGER NOT NULL AUTO_INCREMENT,
    kode VARCHAR(255) NOT NULL UNIQUE,
    nama VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

create table barang(
    id INTEGER NOT NULL AUTO_INCREMENT,
    id_kategori INTEGER(10) NOT NULL,
    kode VARCHAR(255) NOT NULL UNIQUE,
    nama VARCHAR(255) NOT NULL,
    qty DECIMAL(19,2) NOT NULL,
    keterangan VARCHAR(255),
    PRIMARY KEY(id),
    constraint fk_kategori_id foreign key (id_kategori) references kategori (id)
);

create table supplier(
        id INTEGER NOT NULL AUTO_INCREMENT,
        kode VARCHAR(255) NOT NULL UNIQUE,
        nama VARCHAR(255) NOT NULL,
        alamat VARCHAR(255) NOT NULL,
        telepon VARCHAR(255) NOT NULL,
        PRIMARY KEY(id)
);

create table petugas(
        id INTEGER NOT NULL AUTO_INCREMENT,
        nama VARCHAR(255) NOT NULL,
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        active BOOLEAN DEFAULT true,
        PRIMARY KEY(id)
);


create table pelanggan(
        id INTEGER NOT NULL AUTO_INCREMENT,
        kode VARCHAR(255) NOT NULL UNIQUE,
        nama VARCHAR(255) NOT NULL,
        alamat VARCHAR(255) NOT NULL,
        telepon VARCHAR(255) NOT NULL,
        PRIMARY KEY(id)
);

create table barang_masuk(
     id INTEGER NOT NULL AUTO_INCREMENT,
     no_transaksi  VARCHAR(255) NOT NULL,
     tanggal TIMESTAMP NOT NULL,
     id_supplier INTEGER(10) NOT NULL,
     id_petugas INTEGER(10) not null,
     PRIMARY KEY(id),
     constraint fk_petugas_masuk_id foreign key (id_petugas) references petugas (id),
     constraint fk_supplier_id foreign key (id_supplier) references supplier (id)
);

create table barang_masuk_detail(
    id INTEGER NOT NULL AUTO_INCREMENT,
    id_header INTEGER(10) NOT NULL,
    id_barang INTEGER(10) NOT NULL,
    qty DECIMAL(19,2) NOT NULL,
    PRIMARY KEY(id),
     constraint fk_barang_masuk_id foreign key (id_header) references barang_masuk (id),
     constraint fk_barang_id foreign key (id_barang) references barang (id)
);

create table barang_keluar(
     id INTEGER NOT NULL AUTO_INCREMENT,
     no_transaksi  VARCHAR(255) NOT NULL,
     tanggal TIMESTAMP NOT NULL,
     id_petugas INTEGER(10) NOT NULL,
     id_pelanggan INTEGER(10) not null,
     PRIMARY KEY(id),
     constraint fk_petugas_id foreign key (id_petugas) references petugas (id),
     constraint fk_pelanggan_id foreign key (id_pelanggan) references pelanggan (id)
);

create table barang_keluar_detail(
    id INTEGER NOT NULL AUTO_INCREMENT,
    id_header INTEGER(10) NOT NULL,
    id_barang INTEGER(10) NOT NULL,
    qty DECIMAL(19,2) NOT NULL,
    PRIMARY KEY(id),
     constraint fk_barang_keluar_id foreign key (id_header) references barang_keluar (id),
     constraint fk_out_barang_id foreign key (id_barang) references barang (id)
);

create table hibernate_sequence (
        next_val bigint
    );

insert into hibernate_sequence values ( 1 );
