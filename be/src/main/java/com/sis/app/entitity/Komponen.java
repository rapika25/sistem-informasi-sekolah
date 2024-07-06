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
@Table(name = "komponen")
public class Komponen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "kode_komponen")
    @JsonProperty("kode_komponen")
    private String kodeKomponen;

    @Column(name = "nama_komponen")
    @JsonProperty("nama_komponen")
    private String namaKomponen;

    @Column(name = "id_ta")
    @JsonProperty("id_ta")
    private int idTa;

    @Column(name = "biaya")
    @JsonProperty("biaya")
    private int biaya;

    @Column(name = "kode_kelas")
    @JsonProperty("kode_kelas")
    private int kodeKelas;

    @Column(name = "tgl_dibuat")
    @JsonProperty("tgl_dibuat")
    private LocalDate tglDibuat;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_komponen", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<TagihanLain> tagihanLainList;
}
