<template>
  <v-app>
   <urun-header></urun-header>
    <v-main>
      <router-view></router-view>
    </v-main>
    <urun-footer></urun-footer>
  </v-app>
</template>

<script>
import UrunHeader from '../src/components/UrunHeader.vue'
import UrunFooter from '../src/components/UrunFooter.vue'
export default {
  name: 'App',
  components: {
    UrunHeader,
    UrunFooter
  },
};
</script>

<style>
  
</style>