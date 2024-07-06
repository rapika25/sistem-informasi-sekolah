<template>
  <v-row>
    <!-- Input Fields -->
    <v-col cols="10" offset="1">
      <v-card class="mb-2">
        <v-toolbar :color="$vuetify.theme.themes.dark.primary" dark >TAMBAH KOMPONEN</v-toolbar>
        <v-card-text>
          <v-alert v-if="message" color="red lighten-2" >{{ $t(message) }}</v-alert>
          <v-breadcrumbs :items="breadcrumbs" class="pa-0"></v-breadcrumbs>
          <v-form ref="form">
            <v-text-field
                name="kode_komponen"
                label="Kode Komponen"
                type="text"
                :rules="rules.kode_komponen"
                v-model="form.kode_komponen"
            />
            <v-text-field
                name="nama_komponen"
                label="Nama Komponen"
                type="text"
                :rules="rules.nama_komponen"
                v-model="form.nama_komponen"
            />
            <v-text-field
                name="biaya"
                label="Biaya"
                type="number"
                :rules="rules.biaya"
                v-model="form.biaya"
            />
            <v-text-field
                name="kode_kelas"
                label="Kode Kelas"
                type="number"
                :rules="rules.kode_kelas"
                v-model="form.kode_kelas"
            />
            <v-text-field
                name="tgl_dibuat"
                label="Tanggal Dibuat"
                type="date"
                :rules="rules.tgl_dibuat"
                v-model="form.tgl_dibuat"
            />
            <v-select
                v-model="form.id_ta"
                :items="tahun_ajaran"
                label="Tahun Ajaran"
                :rules="rules.tahun_ajaran"
            ></v-select>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn to="/komponen" :color="$vuetify.theme.themes.dark.accent" dark>Back</v-btn>
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
    title: "Tambah Komponen",
  },
  data() {
    return {
      breadcrumbs: [
        { text: "Komponen", to: "/komponen", disabled: false, exact: true },
        { text: "Tambah", disabled: true },
      ],
      btnSaveDisable: false,
      message: "",
      tahun_ajaran: [],
      form: {
        id_ta: 0,
        kode_komponen: "",
        nama_komponen: "",
        biaya: 0,
        kode_kelas: 0,
        tgl_dibuat: "",
      },
      rules: {
        kode_komponen: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Kode Komponen" })],
        nama_komponen: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Nama Komponen" })],
        biaya: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Biaya" })],
        kode_kelas: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Kode Kelas" })],
        tgl_dibuat: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Tanggal Dibuat" })],
        tahun_ajaran: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Tahun Ajaran" })],
      },
    };
  },
  methods: {
    doSave() {
      this.message = "";

      if (this.$refs.form.validate()) {
        this.btnSaveDisable = true;

        try {
          this.$axios.$post("/komponen", this.form)
              .then((res) => {
                this.$router.push({
                  name: `komponen___${this.$i18n.locale}`,
                  params: {
                    type: "success",
                    message: res.messages,
                    title: this.form.nama_komponen,
                  },
                });
              })
              .catch((err) => {
                this.$router.push({
                  name: `komponen___${this.$i18n.locale}`,
                  params: {
                    type: "error",
                    message: err.messages,
                    title: this.form.nama_komponen,
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
  },
  mounted() {
    this.getTahunAjaran()
  }
};
</script>