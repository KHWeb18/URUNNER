<template>

    <v-app-bar app="app" color="white" width="100%">
        <div class="d-flex justify-space-between align-center" style="width: 100%;">
            <v-app-bar-nav-icon class="hidden-lg-and-up" @click="drawer = !drawer"></v-app-bar-nav-icon>
            <div class="d-flex" id="logo">
                <v-icon color="black">mdi-run-fast</v-icon>
                <p class="d-inline text-h6 ma-0 text-decoration-underline">URUNNER</p>
            </div>
            <div class="category d-flex text-h6 grey--text text--darken-2">
                <div class="mr-9 hidden-md-and-down">로드맵</div>
                <div class="mr-9 hidden-md-and-down">강의</div>
                <div class="mr-9 hidden-md-and-down">커뮤니티</div>
                <div class="hidden-md-and-down">유러너</div>
                {{cookie}}
            </div>
            <div class="end d-flex">
                <div class="search hidden-md-and-down mr-6">
                    <input type="text" class="search-input">
                        <v-icon>mdi-magnify</v-icon>
                    </div>
                    <div class="d-flex">
                        <v-btn outlined="outlined" class="mr-1 pa-1" @click="clickLoginBtn" v-if="isLogin ? false : true">로그인</v-btn>
                        <v-btn outlined="outlined" class="mr-1 pa-1" @click="clickLogoutBtn" v-if="isLogin ? true : false">로그아웃</v-btn>
                        <v-btn class="primary pa-1" @click="goToMemberRegisterPage">회원가입</v-btn>
                    </div>
            </div>
        </div>
          <v-navigation-drawer
                v-model="drawer"
                temporary="temporary"
                absolute="absolute"
                max-width="400"
                height="100vh">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="d-flex">
                    <input type="text" class="search-input">
                    <v-icon>mdi-magnify</v-icon>
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-divider></v-divider>
            <v-list dense="dense" nav="nav">
                <v-list-item v-for="item in items" :key="item.title" link="link">
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                 </v-list-item>
             </v-list>
         </v-navigation-drawer>
    </v-app-bar>
</template>

<script>
import EventBus from "../event"
import Vue from 'vue'

    export default {
        data: () => ({
            drawer: false,
            items: [
                {
                    title: '개발'
                }, {
                    title: '언어'
                }
            ],
            isLogin: Vue.$cookies.get("ACCESS_TOKEN")
        }),
       created: function() {
         EventBus.$on('isLogin', (isLogin) => this.isLogin = isLogin)
       },

        methods: {
          goToMemberRegisterPage() {
            this.$router.push('/memberRegister')
          },
          clickLoginBtn() {
            this.$emit('clickLoginBtn')
          },
          clickLogoutBtn() {
            this.$emit("clickLogoutBtn")
          },
        }
    }
</script>

<style scoped>
  .search-input {
     height: 32px;
     background-color: #f6f6f6;
     border-radius: 3px;
     color: #5f5f5f;
     border: 1px solid transparent;
     padding: 3px;
     box-shadow: none;
  }
</style>