<template>
  <v-row>
    <!-- Input Fields -->
    <v-col cols="10" offset="1">
      <v-card class="mb-2">
        <v-toolbar :color="$vuetify.theme.themes.dark.primary" dark >TAMBAH TAGIHAN LAIN</v-toolbar>
        <v-card-text>
          <v-alert v-if="message" color="red lighten-2" >{{ $t(message) }}</v-alert>
          <v-breadcrumbs :items="breadcrumbs" class="pa-0"></v-breadcrumbs>
          <v-form ref="form">
            <v-text-field
                name="tgl_bayar"
                label="Tanggal Bayar"
                type="date"
                :rules="rules.tgl_bayar"
                v-model="form.tgl_bayar"
            />
            <v-select
                v-model="form.id_ta"
                :items="tahun_ajaran"
                label="Tahun Ajaran"
                :rules="rules.tahun_ajaran"
            ></v-select>
            <v-select
                v-model="form.id_siswa"
                :items="siswa"
                label="Siswa"
                :rules="rules.siswa"
            ></v-select>
            <v-select
                v-model="form.id_transaksi"
                :items="transaksi"
                label="Transaksi"
                :rules="rules.transaksi"
            ></v-select>
            <v-select
                v-model="form.id_komponen"
                :items="komponen"
                label="Komponen"
                :rules="rules.komponen"
            ></v-select>
            <v-select
            label="Status"
            :items="status"
            :rules="rules.status"
            v-model="form.status"
            ></v-select>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn to="/tagihan-lain" :color="$vuetify.theme.themes.dark.accent" dark>Back</v-btn>
          <v-spacer />
          <v-btn @click="doSave" :color="$vuetify.theme.themes.dark.secondary" dark :loading="btnSaveDisable">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  head: {
    title: "Tambah Tagihan Lain",
  },
  data() {
    return {
      breadcrumbs: [
        { text: "Tagihan Lain", to: "/tagihan-lain", disabled: false, exact: true },
        { text: "Tambah", disabled: true },
      ],
      btnSaveDisable: false,
      message: "",
      tahun_ajaran: [],
      siswa: [],
      transaksi: [],
      komponen: [],
      status: ["Berhasil", "Pending", "Gagal"],
      form: {
        id_komponen: 0,
        id_transaksi: 0,
        id_siswa: 0,
        id_ta: 0,
        tgl_bayar: "",
        status: "",
      },
      rules: {
        tgl_bayar: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Tanggal Bayar" })],
        tahun_ajaran: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Tahun Ajaran" })],
        siswa: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Siswa" })],
        transaksi: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Transaksi" })],
        komponen: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Komponen" })],
        status: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Status" })],
      },
    };
  },
  methods: {
    doSave() {
      this.message = "";

      if (this.$refs.form.validate()) {
        this.btnSaveDisable = true;

        try {
          this.$axios.$post("/tagihan-lain", this.form)
              .then((res) => {
                this.$router.push({
                  name: `tagihan-lain___${this.$i18n.locale}`,
                  params: {
                    type: "success",
                    message: res.messages,
                    title: this.form.tgl_bayar,
                  },
                });
              })
              .catch((err) => {
                this.$router.push({
                  name: `tagihan-lain___${this.$i18n.locale}`,
                  params: {
                    type: "error",
                    message: err.messages,
                    title: this.form.tgl_bayar,
                  },
                });
              })
        } catch (error) {
          console.error('Error:', error);
          this.message = "An error occurred while saving.";
        } finally {
          this.btnSaveDisable = false;
        }
      }
    },
    getTahunAjaran() {
      this.isLoading = true;

      this.$axios
          .$get(`/tahun-ajaran?page=-1&limit=-1&search=`)
          .then((res) => {
            const { data } = res;

            data.forEach(item => {
              this.tahun_ajaran.push({
                text: item.periode,
                value: item.id,
              })
            })
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            this.isLoading = false;
          });
    },
    getSiswa() {
      this.isLoading = true;

      this.$axios
          .$get(`/siswa?page=-1&limit=-1&search=`)
          .then((res) => {
            const { data } = res;

            data.forEach(item => {
              this.siswa.push({
                text: item.nama_lengkap,
                value: item.id,
              })
            })
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            this.isLoading = false;
          });
    },
    getTransaksi() {
      this.isLoading = true;

      this.$axios
          .$get(`/transaksi?page=-1&limit=-1&search=`)
          .then((res) => {
            const { data } = res;

            data.forEach(item => {
              this.transaksi.push({
                text: item.kode_transaksi,
                value: item.id,
              })
            })
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            this.isLoading = false;
          });
    },
    getKomponen() {
      this.isLoading = true;

      this.$axios
          .$get(`/komponen?page=-1&limit=-1&search=`)
          .then((res) => {
            const { data } = res;

            data.forEach(item => {
              this.komponen.push({
                text: item.kode_komponen,
                value: item.id,
              })
            })
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            this.isLoading = false;
          });
    },
  },
  mounted() {
    this.getTahunAjaran()
    this.getSiswa()
    this.getTransaksi()
    this.getKomponen()
  }
};
</script>