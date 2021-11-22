<template>
  <v-app>
   <urun-header @clickLoginBtn="clickLoginBtn"
                @clickLogoutBtn="clickLogoutBtn"
                @openRegInstructor="openRegInstructor"/>
    <v-main>
      <router-view></router-view>
      <login-dialog :isLogin="isLogin" @dialogOff="dialogOff"></login-dialog>
      <reg-instructor-dialog :openRegDialog="openRegDialog"
                             @closeDialog="closeDialog"
                             @openLoginDialog="dialogOff"
                             @logout="clickLogoutBtn"/>
    </v-main>
    <urun-footer></urun-footer>
  </v-app>
</template>

<script>
import UrunHeader from '../src/components/UrunHeader.vue'
import UrunFooter from '../src/components/UrunFooter.vue'
import LoginDialog from '../src/components/LoginDialog.vue'
import RegInstructorDialog from '../src/components/common/RegInstructorDialog.vue'

import { logout } from '@/util/APIUtil'

import { mapActions } from 'vuex'
import Vue from 'vue'

export default {
  name: 'App',
  components: {
    UrunHeader,
    UrunFooter,
    LoginDialog,
    RegInstructorDialog
  },
  data() {
    return {
      isLogin: false,
      isLogout: false,
      openRegDialog: false

    }
  },
    mounted () {
        this.fetchMyIntroduce(Vue.$cookies.get("USER_NAME"))
  },
  methods: {
    clickLoginBtn() {
      this.isLogin = !this.isLogin
    },
    clickLogoutBtn() {
      this.isLogout = !this.isLogout
      logout()
    },
    dialogOff() {
      this.isLogin = !this.isLogin
    },
    dialogOff2() {
      this.isLogout = !this.isLogout
    },
    openRegInstructor() {
      this.openRegDialog = !this.openRegDialog
    },
    closeDialog() {
      this.openRegDialog = !this.openRegDialog
    },
    ...mapActions(['fetchMyIntroduce'])
  }
};
</script>

<style>
  
</style>