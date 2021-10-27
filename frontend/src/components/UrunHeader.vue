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
            </div>
            <div class="end d-flex">
                <div class="search hidden-md-and-down mr-6">
                    <input type="text" class="search-input">
                        <v-icon>mdi-magnify</v-icon>
                    </div>
                     <div v-if="isLogin" class="d-flex">
                      <v-btn color="primary" class="mr-5 hidden-md-and-down">최근강의 <v-icon>mdi-lead-pencil</v-icon></v-btn>

                        <v-menu offset-y open-on-hover>
                          <template v-slot:activator="{ on, attrs }">
                            <v-icon color="primary" v-on="on" v-bind="attrs" class="mr-4">mdi-cart-outline</v-icon>
                          </template>
                          <open-main-menu-cart/>
                        </v-menu>

                        <v-menu offset-y open-on-hover>
                          <template v-slot:activator="{ on, attrs }">
                            <v-icon color="primary" v-on="on" v-bind="attrs" class="mr-4">mdi-bell-ring-outline</v-icon>
                          </template>
                          <open-main-menu-bell/>
                        </v-menu>
                        
                        <v-menu offset-y open-on-hover>
                          <template v-slot:activator="{ on, attrs }">
                            <v-icon color="primary" v-on="on" v-bind="attrs" class="mr-3">mdi-run-fast</v-icon>
                          </template>
                          <open-main-menu-logo @clickLogoutBtn="clickLogoutBtn"/>
                        </v-menu>
                    </div>
                    <div v-else class="d-flex">
                        <v-btn outlined="outlined" class="mr-1 pa-1" @click="clickLoginBtn">로그인</v-btn>
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
import OpenMainMenuBell from '../components/headerLoginMenu/OpenMainMenuBell.vue'
import OpenMainMenuCart from '../components/headerLoginMenu/OpenMainMenuCart.vue'
import OpenMainMenuLogo from '../components/headerLoginMenu/OpenMainMenuLogo.vue'

    export default {
      components: {
        OpenMainMenuBell,
        OpenMainMenuCart,
        OpenMainMenuLogo
      },
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
            this.$emit('clickLogoutBtn')
          } 
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