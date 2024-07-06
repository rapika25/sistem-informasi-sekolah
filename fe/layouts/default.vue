<template>
  <v-app>
    <v-navigation-drawer disable-resize-watcher fixed app :color="$vuetify.theme.themes.dark.primary" dark>
      <v-list>
        <v-list-item
          v-for="(item, i) in sideMenu"
          :key="i"
          :to="item.to"
          router
          exact
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <v-container fill-height fluid>
        <Nuxt />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "DefaultLayout",
  data() {
    return {
      sideDrawer: false,
      sideMenu: [
        {
          icon: "mdi-view-dashboard-variant",
          title: "Dashboard",
          to: "/",
        },
        {
          icon: "mdi-calendar-month",
          title: "Tahun Ajaran",
          to: "/tahun-ajaran",
        },
        {
          icon: "mdi-account-school",
          title: "Siswa",
          to: "/siswa",
        },
        {
          icon: "mdi-cash-sync",
          title: "Pembayaran",
          to: "/pembayaran",
        },
        {
          icon: "mdi-account-credit-card",
          title: "Transaksi",
          to: "/transaksi",
        },
        {
          icon: "mdi-receipt-text-send",
          title: "Tagihan SPP",
          to: "/tagihan-spp",
        },
        {
          icon: "mdi-chart-box",
          title: "Komponen",
          to: "/komponen",
        },
        {
          icon: "mdi-currency-usd",
          title: "Tagihan Lain",
          to: "/tagihan-lain",
        },
      ],
    };
  },
  mounted() {
    this.$router.push("/")
  },
};
</script>
