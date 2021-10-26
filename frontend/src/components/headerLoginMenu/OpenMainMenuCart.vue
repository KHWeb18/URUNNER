<template>
  <v-card color="primary" width="500">
    <v-tabs fixed-tabs slider-color="primary">
      <v-tab v-for="(item, index) in items" :key="index">
        {{ item }}
      </v-tab>
        <v-tab-item>
          <v-card>
            <v-list>
              <v-list-item-group color="primary">
                <v-list-item v-for="(item, i) in LectureLists" :key="i" class="mb-2">
                  <v-img :src="item.image" max-height="89" max-width="123"/>
                  <v-list-item-content class="ml-2">
                    <v-list-item-title><h4>{{ item.title }}</h4></v-list-item-title>
                    <v-list-item-subtitle v-text="item.price" class="text-right pa-3"/>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
            <v-list class="ma-0 pa-0">
              <v-btn color="primary" x-large block>수강 바구니로 이동</v-btn>
            </v-list>
        </v-card>
       </v-tab-item>

        <v-tab-item>
          <v-card>
            <v-list>
              <v-list-item-content>
                <v-list-item-subtitle><p class="text-center">위시 리스트가 없습니다. </p></v-list-item-subtitle>
              </v-list-item-content>
            </v-list>
            <v-list class="ma-0 pa-0">
              <v-btn color="primary" x-large block>위시 리스트로 이동</v-btn>
            </v-list>
        </v-card>
       </v-tab-item>
    </v-tabs>
  </v-card>
</template>

<script>
  export default {
    data () {
      return {
        tab: null,
        items: [
          '수강바구니', '위시리스트'
        ],
        LectureLists: [
        { title: '스스로 구축하는 AWS 클라우드 네트워크 - 기본편', price: '₩42,000', image: 'https://cdn.inflearn.com/public/courses/327724/cover/e6ee6969-4d60-4b64-8401-11c5ed0ad288/327724-eng.png' },
        { title: '스스로 구축하는 AWS 클라우드 네트워크 - 기본편', price: '₩42,000', image: 'https://cdn.inflearn.com/public/courses/327724/cover/e6ee6969-4d60-4b64-8401-11c5ed0ad288/327724-eng.png' },
        { title: '스스로 구축하는 AWS 클라우드 네트워크 - 기본편', price: '₩42,000', image: 'https://cdn.inflearn.com/public/courses/327724/cover/e6ee6969-4d60-4b64-8401-11c5ed0ad288/327724-eng.png' },
      ],
      }
    },
  }
</script>

<style>

</style>