<template>
  <v-app>
   <urun-header @clickLoginBtn="clickLoginBtn"  @clickLogoutBtn="clickLogoutBtn"></urun-header>
    <v-main>
      <router-view></router-view>
      <login-dialog :isLogin="isLogin" @dialogOff="dialogOff"></login-dialog>
    </v-main>
    <urun-footer></urun-footer>
  </v-app>
</template>

<script>
import UrunHeader from '../src/components/UrunHeader.vue'
import UrunFooter from '../src/components/UrunFooter.vue'
import LoginDialog from '../src/components/LoginDialog.vue'
import { logout } from '@/util/APIUtil'

export default {
  name: 'App',
  components: {
    UrunHeader,
    UrunFooter,
    LoginDialog,
  },
  data() {
    return {
      isLogin: false,
      isLogout: false
    }
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
    }
  }
};
</script>

<style>
  
</style>