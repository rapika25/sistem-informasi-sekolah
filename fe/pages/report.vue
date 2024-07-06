<template>
  <v-container>
    <v-btn @click="generatePDF" :color="$vuetify.theme.themes.dark.secondary" dark class="mb-2">Download PDF</v-btn>
    <v-card>
      <v-card-title>Laporan Siswa</v-card-title>
      <v-card-text>
        <v-simple-table>
          <template v-slot:default>
            <thead>
            <tr>
              <th class="text-left">No</th>
              <th class="text-left">NIM</th>
              <th class="text-left">Nama Siswa</th>
              <th class="text-left">Mata Kuliah</th>
              <th class="text-left">Nilai</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(siswa, index) in siswaList" :key="siswa.id">
              <td>{{ index + 1 }}</td>
              <td>{{ siswa.nim }}</td>
              <td>{{ siswa.nama_siswa }}</td>
              <td>
                <template v-for="(nilai, idx) in siswa.nilai">
                  {{ getMataKuliahName(nilai.mataKuliahID) }}<br />
                </template>
              </td>
              <td>
                <template v-for="(nilai, idx) in siswa.nilai">
                  {{ nilai.nilai }}<br />
                </template>
              </td>
            </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';

pdfMake.vfs = pdfFonts.pdfMake.vfs;

export default {
  data() {
    return {
      siswaList: [],
      mataKuliahList: []
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const [siswares, mataKuliahres] = await Promise.all([
          this.$axios.$get('/siswa'),
        ]);

        this.siswaList = siswares.data;
        this.mataKuliahList = mataKuliahres.data;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    generatePDF() {
      const documentDefinition = {
        content: [
          { text: 'Laporan Siswa', style: 'header' },
          '\n',
          {
            table: {
              headerRows: 1,
              widths: ['auto', 'auto', '*', 'auto', 'auto'],
              body: [
                ['No', 'NIM', 'Nama Siswa', 'Mata Kuliah', 'Nilai'],
                ...this.siswaList.flatMap((siswa, index) =>
                    siswa.nilai.map((nilai, idx) => [
                      idx === 0 ? index + 1 : '',
                      idx === 0 ? siswa.nim : '',
                      idx === 0 ? siswa.nama_siswa : '',
                      this.getMataKuliahName(nilai.mataKuliahID),
                      nilai.nilai
                    ])
                )
              ]
            }
          }
        ],
        styles: {
          header: {
            fontSize: 18,
            bold: true
          }
        }
      };

      pdfMake.createPdf(documentDefinition).download("report.pdf");
    },
    getMataKuliahName(id) {
      const mataKuliah = this.mataKuliahList.find(mk => mk.id === id);
      return mataKuliah ? mataKuliah.nama_mata_kuliah : 'Unknown';
    }
  }
};
</script>

<style scoped>
.text-left {
  text-align: left;
}
</style>
