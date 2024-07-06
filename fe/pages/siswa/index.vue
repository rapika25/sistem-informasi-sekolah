<template>
  <v-row class="frame-content">
    <v-col cols="10" offset="1">
      <v-card class="my-3">
        <v-toolbar :color="$vuetify.theme.themes.dark.primary" dark >
          Siswa
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-toolbar>
        <v-card-text>
          <v-alert v-if="message" :type="alertType">{{ message }}</v-alert>
          <div class="d-flex mb-4">
            <v-breadcrumbs :items="breadcrumbs" class="pa-0" />
            <v-spacer></v-spacer>
            <v-btn to="/siswa/add" :color="$vuetify.theme.themes.dark.secondary" dark elevation="3" small
              >Tambah <v-icon right>mdi-plus-circle</v-icon></v-btn
            >
          </div>

          <v-data-table
            :isLoading="isLoading"
            :items="siswa"
            :headers="headers"
            :items-per-page="10"
            :server-items-length="totalData"
            :options.sync="options"
            :search.sync="search"
            :footer-props="{
              itemsPerPageOptions: [10, 25, 50, 75, 100],
            }"
          >
            <template v-slot:top>
              <v-dialog v-model="dialogDelete" max-width="500px">
                <v-card>
                  <v-card-title
                    >Kamu yakin ingin menghapus data
                    {{ itemDelete.nama_siswa }}?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn :color="$vuetify.theme.themes.dark.accent" dark text @click="cancelDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="error"
                      text
                      @click="confirmDelete(itemDelete.id)"
                      >Delete</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </template>
            <template v-slot:item.status="{ item }">
              <span>{{ item.status ? "Active" : "Inactive" }}</span>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-btn :to="`/siswa/edit/${item.id}`" icon :color="$vuetify.theme.themes.dark.info"
                ><v-icon small>mdi-pencil</v-icon></v-btn
              >
              <v-btn small icon @click="deleteItem(item)" :color="$vuetify.theme.themes.dark.error"
                ><v-icon small>mdi-delete</v-icon></v-btn
              >
            </template>
          </v-data-table>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  head: {
    title: "Siswa",
  },
  data() {
    return {
      siswa: [],
      options: {},
      totalData: 0,
      search: "",
      isLoading: false,
      message: "",
      alertType: "",
      dialogDelete: false,
      itemDelete: "",
      headers: [
        { text: "No.", value: "number", sortable: false },
        { text: "NISN", value: "nisn", sortable: false },
        { text: "Nama Lengkap", value: "nama_lengkap", sortable: false },
        { text: "Status", value: "status", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
      breadcrumbs: [
        {
          text: "",
          disabled: true,
          to: "/siswa",
        },
      ],
    };
  },
  methods: {
    getSiswa() {
      this.isLoading = true;
      const { page, itemsPerPage } = this.options;

      this.$axios
        .$get(`/siswa?page=${page - 1}&limit=${itemsPerPage}&search=${this.search}`)
        .then((res) => {
          this.siswa = [];
          this.totalData = 0;

          if (res.success) {
            this.siswa = res.data;
            this.totalData = res.additionalEntity.totalData;

            let i = res.additionalEntity.number * itemsPerPage + 1;
            i = isNaN(i) ? 1 : i;
            this.siswa.map((item) => (item.number = i++));
          }
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    confirmDelete(id) {
      this.$axios
        .$delete(`/siswa/${id}`)
        .then(async () => {
          await this.getSiswa();
          this.alertType = "success";
          this.message = this.$t("DELETE_SUCCESS", {
            title: this.itemDelete.nama_siswa,
          });
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.cancelDelete();
        });
    },
    deleteItem(item) {
      this.dialogDelete = true;
      this.itemDelete = item;
    },
    cancelDelete() {
      this.dialogDelete = false;
    },
  },
  watch: {
    options: {
      handler() {
        this.getSiswa();
      },
      deep: true,
    },
    search: {
      handler() {
        this.getSiswa();
      },
    },
  },
  mounted() {
    if (this.$route.params.message) {
      this.alertType = this.$route.params.type;
      this.message = this.$t(this.$route.params.message, {
        title: this.$route.params.nama_siswa,
      });
    }
  },
};
</script>
