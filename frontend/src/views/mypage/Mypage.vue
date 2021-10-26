<template>
  <div>
    <app-bar/>
    <v-container>
      <Index/>
    </v-container>
  </div>
</template>

<script>
import Index from '@/components/mypage/Index.vue'
import AppBar from '../../components/mypage/App-bar.vue'

export default {
  components: {
    Index,
    AppBar
  }

}
</script>

<style>

</style>