<template>
  <div>
      <v-card width="260" tile style="opacity:0.96">
        <v-list>
          <v-list-item>
            <router-link :to="{ path: '/memberProfile' }">
              <v-list-item-avatar color="black" size="60">
                <v-img :src="`http://localhost:7777/lecture/image/${thumb_path}/${email}`"></v-img>
              </v-list-item-avatar>
            </router-link>
            <v-list-item-content>
              <router-link :to="{ path: '/memberProfile' }">
                <v-list-item-title class="card_nickname">
                  {{ nickanme }}
                </v-list-item-title>
              </router-link>
              <v-list-item-subtitle class="card_role">{{roleCheck()}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-list nav dense>
          <v-list-item-group color="primary">
            <v-list-item
              v-for="(item, i) in items2"  :key="i">
              <router-link :to="{ path: item.path }" class="card_button">
                <div class="button_flex">
                  <v-list-item-icon>
                    <v-icon v-text="item.icon"></v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title v-text="item.text"></v-list-item-title>
                  </v-list-item-content>
                </div>
              </router-link>
            </v-list-item>
          </v-list-item-group>
        </v-list>
        <v-list class="ma-0 pa-0">
          <v-list-item>
            <v-list-item-content class="ma-0 pa-0">
                <v-btn depressed rounded text="text" @click="clickLogoutBtn">
                  Logout
                </v-btn>
                <v-btn v-if="isAdmin" depressed rounded text="text" @click="clickManager" style="margin-bottom:5px;">
                  Admin
                </v-btn>
            </v-list-item-content>
            <v-list-item-action>
              
            <v-tooltip bottom v-if="isInstructor">

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
import Vue from 'vue'
export default {
  data() {
    return {
      items2: [
        { text: '강의 이어듣기', icon: 'mdi-book', path: '/latest' },
        { text: '내 학습', icon: 'mdi-bookshelf', path: '/myLecture' },
        { text: '위시리스트', icon: 'mdi-star-check-outline', path: '/wishlist' },
        { text: '포인트', icon: 'mdi-alpha-p-circle-outline', path: '/my-points' },
        { text: '구매내역', icon: 'mdi-purse-outline', path: '/orders' },
        { text: '문의하기', icon: 'mdi-message-question-outline', path: '/inqforuser' },
      ],
      nickanme: Vue.$cookies.get("NICKNAME"),
      thumb_path: this.$store.state.profile.thumb_path,
      email: Vue.$cookies.get("USER_NAME"),
    }
  },
  computed: {
    isInstructor() {
      return this.$cookies.get('ROLES').includes("ROLE_MANAGER")
    },
    isAdmin() {
      return this.$cookies.get('ROLES').includes("ROLE_ADMIN")
    }
  },
  methods: {
    clickLogoutBtn() {
      this.$emit('clickLogoutBtn')
    },
    openRegInstructor() {
      this.$emit('openRegInstructor')
    },
    clickManager () {
      this.$router.push('/manager')
    },
    roleCheck() {
      if(this.isInstructor == 'ROLE_USER,ROLE_MANAGER') {
        var text = '지식공유자'
        return text
      } else {
        var text2 = '일반회원'
        return text2
      }
    }
  }

}
</script>

<style>
a { text-decoration:none !important }
a:hover { text-decoration:none !important }
.card_nickname {
  color: #424242;
  font-size: 22px !important;
  font-weight: bold;
  border-bottom: 1px solid #BDBDBD;
}
.card_role {
  color: #424242;
  font-size: 12px !important;
}
.v-list.v-sheet.theme--light.v-list--dense.v-list--nav {
  padding: 0px;
  margin: 20px;
}
.card_button {
  display:flex;
  justify-content:start;
  flex-grow: 1;
  color: #424242 !important;
}
.v-list-item__icon {
  margin-right: 15px !important;
}
.button_flex {
  display: flex;
  justify-content: row;
}
</style>
