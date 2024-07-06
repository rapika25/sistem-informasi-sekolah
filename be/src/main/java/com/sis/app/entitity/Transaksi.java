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
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "kode_transaksi")
    @JsonProperty("kode_transaksi")
    private String kodeTransaksi;

    @Column(name = "id_ta")
    @JsonProperty("id_ta")
    private int idTa;

    @Column(name = "id_siswa")
    @JsonProperty("id_siswa")
    private int idSiswa;

    @Column(name = "id_pembayaran")
    @JsonProperty("id_pembayaran")
    private int idPembayaran;

    @Column(name = "tgl_pembayaran")
    @JsonProperty("tgl_pembayaran")
    private LocalDate tglPembayaran;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_transaksi", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<TagihanSPP> tagihanSPP;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_transaksi", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<TagihanLain> tagihanLain;
}
