<template>
  <v-row>
    <!-- Input Fields -->
    <v-col cols="10" offset="1">
      <v-card class="mb-2">
        <v-toolbar :color="$vuetify.theme.themes.dark.primary" dark >UBAH TAHUN AJARAN</v-toolbar>
        <v-card-text>
          <v-alert v-if="message" color="red lighten-2" >{{ $t(message) }}</v-alert>
          <v-breadcrumbs :items="breadcrumbs" class="pa-0"></v-breadcrumbs>
          <v-form ref="form">
            <v-text-field
                name="periode"
                label="Periode"
                type="text"
                :rules="rules.periode"
                v-model="form.periode"
            />
            <v-text-field
                name="tgl_mulai"
                label="Tanggal Mulai"
                type="date"
                :rules="rules.tgl_mulai"
                v-model="form.tgl_mulai"
            />
            <v-text-field
                name="tgl_akhir"
                label="Tanggal Akhir"
                type="date"
                :rules="rules.tgl_akhir"
                v-model="form.tgl_akhir"
            />
            <v-text-field
                name="kurikulum"
                label="Kurikulum"
                type="text"
                :rules="rules.kurikulum"
                v-model="form.kurikulum"
            />
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn to="/tahun-ajaran" :color="$vuetify.theme.themes.dark.accent" dark>Back</v-btn>
          <v-spacer />
          <v-btn @click="doSave" :disabled="btnSaveDisable" :color="$vuetify.theme.themes.dark.secondary" dark>
            <template v-if="btnSaveDisable">
              <v-progress-circular indeterminate size="24"></v-progress-circular>
            </template>
            <template v-else>
              Save
            </template>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  head: {
    title: "Ubah Tahun Ajaran",
  },
  asyncData({ params }) {
    return {
      id: params.id,
    };
  },
  data() {
    return {
      breadcrumbs: [
        { text: "Tahun Ajaran", to: "/tahun-ajaran", disabled: false, exact: true },
        { text: "Ubah", disabled: true },
      ],
      btnSaveDisable: false,
      message: "",
      form: {
        id: 0,
        periode: "",
        tgl_mulai: "",
        tgl_akhir: "",
        kurikulum: "",
      },
      rules: {
        periode: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Periode" })],
        tgl_mulai: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Tanggal Mulai" })],
        tgl_akhir: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Tanggal Akhir" })],
        kurikulum: [(v) => !!v || this.$t("FIELD_IS_REQUIRED", { field: "Kurikulum" })],
      },
    };
  },
  methods: {
    doSave() {
      this.message = "";

      if (this.$refs.form.validate()) {
        this.btnSaveDisable = true;

        try {
          this.form.id = this.id
          this.$axios.$put(`/tahun-ajaran`, this.form)
              .then((res) => {
                this.$router.push({
                  name: `tahun-ajaran___${this.$i18n.locale}`,
                  params: {
                    type: "success",
                    message: res.messages,
                    title: this.form.kurikulum,
                  },
                });
              })
              .catch((err) => {
                this.$router.push({
                  name: `tahun-ajaran___${this.$i18n.locale}`,
                  params: {
                    type: "error",
                    message: err.messages,
                    title: this.form.kurikulum,
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
  getData() {
      try {
        this.$axios.$get(`/tahun-ajaran/${this.id}`)
            .then((res) => {
              const { data } = res;
              this.form.periode = data.periode
              this.form.tgl_mulai = data.tgl_mulai
              this.form.tgl_akhir = data.tgl_akhir
              this.form.kurikulum = data.kurikulum;
            })
      } catch (error) {
        console.error('Error:', error);
      }
    },
  },
  mounted() {
    this.getData();
  }
};
</script>
