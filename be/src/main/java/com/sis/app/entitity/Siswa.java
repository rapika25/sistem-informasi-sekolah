package com.sis.app.entitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "siswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_ta")
    @JsonProperty("id_ta")
    private int idTa;

    @Column(name = "nisn")
    @JsonProperty("nisn")
    private int nisn;

    @Column(name = "nama_lengkap")
    @JsonProperty("nama_lengkap")
    private String namaLengkap;

    @Column(name = "tanggal_lahir")
    @JsonProperty("tanggal_lahir")
    private LocalDate tanggalLahir;

    @Column(name = "alamat")
    @JsonProperty("alamat")
    private String alamat;

    @Column(name = "nama_ortu")
    @JsonProperty("nama_ortu")
    private String namaOrtu;

    @Column(name = "telp")
    @JsonProperty("telp")
    private String telp;

    @Column(name = "foto")
    @JsonProperty("foto")
    private String foto;

    @Column(name = "status")
    @JsonProperty("status")
    private boolean status;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_siswa", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<Transaksi> transaksiList;
}
