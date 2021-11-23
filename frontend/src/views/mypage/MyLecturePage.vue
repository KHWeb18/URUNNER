<template>
    <div id="board_padding">
      <my-lecture :callLecturelist="callLecturelist"/>
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
  created() {
  },
  computed: {
    ...mapState(['callLecturelist'])
  },
  mounted () {
    this.fetchMyLectureList(this.$store.state.profile.member_no)
  },
  methods: {
    ...mapActions(['fetchMyLectureList'])
  }
}
</script>

<style scoped>
</style>