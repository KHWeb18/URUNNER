<template>
  <v-card width="300" tile>
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="https://cdn.vuetifyjs.com/images/john.png"/>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title class="text-h6">
              User name
            </v-list-item-title>
            <v-list-item-subtitle>Student</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item> 
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="d-flex">
              <v-btn class="mr-3">쿠폰:  0개</v-btn>
              <v-btn>포인트:  1000p</v-btn>
            </v-list-item-title> 
          </v-list-item-content>
        </v-list-item>
        <h3 class="text-center">학생</h3>
      </v-list>
      <v-divider></v-divider>
      <v-list nav dense>
        <v-list-item-group color="primary">
          <v-list-item
            v-for="(item, i) in items2"  :key="i">
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
      <v-list class="ma-0 pa-0">
        <v-list-item>
          <v-list-item-content class="ma-0 pa-0">
            <v-list-item-subtitle @click="clickLogoutBtn">
              <p>로그아웃</p>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      items2: [
        { text: '이어서 학습하기', icon: 'mdi-youtube' },
        { text: '대시보드', icon: 'mdi-chart-pie' },
        { text: '내 학습', icon: 'mdi-bookshelf' },
        { text: '강의노트', icon: 'mdi-note-edit-outline' },
        { text: '멘토링', icon: 'mdi-lightbulb-on-outline' },
        { text: '작성한 게시글', icon: 'mdi-view-dashboard-edit-outline' },
        { text: '더보기', icon: 'mdi-dots-horizontal' },
      ],
    }
  },
  methods: {
    clickLogoutBtn() {
      this.$emit('clickLogoutBtn')
    }
  }

}
</script>

<style>

</style>