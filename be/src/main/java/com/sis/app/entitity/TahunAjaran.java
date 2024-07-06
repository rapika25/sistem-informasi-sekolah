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
@Table(name = "tahun_ajaran")
public class TahunAjaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "periode")
    @JsonProperty("periode")
    private int periode;

    @Column(name = "tgl_mulai")
    @JsonProperty("tgl_mulai")
    private LocalDate tglMulai;

    @Column(name = "tgl_akhir")
    @JsonProperty("tgl_akhir")
    private LocalDate tglAkhir;

    @Column(name = "kurikulum")
    @JsonProperty("kurikulum")
    private String kurikulum;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_ta", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<Siswa> siswa;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_ta", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<Komponen> komponen;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_ta", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<Transaksi> transaksi;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_ta", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<TagihanSPP> tagihanSPP;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_ta", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private List<TagihanLain> tagihanLain;
}
