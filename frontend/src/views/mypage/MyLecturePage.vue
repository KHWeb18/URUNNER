<template>
    <div id="board_padding">
        <v-container>
            <my-lecture :lectures="lectures"/>
        </v-container>
    </div>
</template>

<script>

import MyLecture from '@/components/mypage/MyLecture.vue'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'MyLecturePage',
  components: {
    MyLecture
  },
  computed: {
    ...mapState(['callLecturelist'])
  },
  mounted () {
    this.fetchMyLectureList()
  },
  methods: {
    ...mapActions(['fetchMyLectureList'])
  }
}
</script>

<style scoped>
</style>