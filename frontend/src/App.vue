<template>
  <v-app>
   <urun-header @clickLoginBtn="clickLoginBtn"></urun-header>
    <v-main>
      <router-view></router-view>
      <login-dialog :isLogin="isLogin"></login-dialog>
    </v-main>
    <urun-footer></urun-footer>
  </v-app>
</template>

<script>
import UrunHeader from '../src/components/UrunHeader.vue'
import UrunFooter from '../src/components/UrunFooter.vue'
import LoginDialog from '../src/components/LoginDialog.vue'
export default {
  name: 'App',
  components: {
    UrunHeader,
    UrunFooter,
    LoginDialog,
  },
  data() {
    return {
      isLogin: false
    }
  },
  methods: {
    clickLoginBtn() {
      this.isLogin = !this.isLogin
    }
  }
};
</script>

<style>
  
</style>