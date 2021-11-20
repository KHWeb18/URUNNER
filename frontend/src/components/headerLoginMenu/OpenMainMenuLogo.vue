<template>
  <div>
      <v-card width="300" tile>
        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <v-img src="https://cdn.vuetifyjs.com/images/john.png"/>
            </v-list-item-avatar>
            <v-list-item-content>
              <router-link :to="{ name: 'memberProfile' }">
                <v-list-item-title class="text-h6">
                  {{ this.$store.state.moduleA.name }}
                </v-list-item-title>
              </router-link>
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
            <v-list-item-action>
            <v-tooltip bottom  v-if="isInstructor == 'ROLE_ADMIN,ROLE_USER'">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    color="warning"
                    v-bind="attrs"
                    v-on="on"
                    rounded
                    to="/mypage/lecture/lectureList"
                  >
                    <v-icon>mdi-shield-account-outline</v-icon>
                  </v-btn>
                </template>
                <span>내 강의 관리하기</span>
              </v-tooltip>
            <v-tooltip bottom v-else>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    color="primary"
                    v-bind="attrs"
                    v-on="on"
                    rounded
                    @click.prevent="openRegInstructor"
                  >
                    <v-icon>mdi-shield-account-outline</v-icon>
                  </v-btn>
                </template>
                <span>지식 공유자 신청</span>
              </v-tooltip>
            </v-list-item-action>
          </v-list-item>
        </v-list>
    </v-card>
  </div>
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
  computed: {
    isInstructor() {
      return this.$cookies.get('ROLES')
    }
  },
  methods: {
    clickLogoutBtn() {
      this.$emit('clickLogoutBtn')
    },
    openRegInstructor() {
      this.$emit('openRegInstructor')
    }
  }

}
</script>

<style>

</style>