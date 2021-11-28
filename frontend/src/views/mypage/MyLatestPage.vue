<template>
    <div id="board_padding">
      <my-latest-lecture :callLatestLecture="callLatestLecture"/>
    </div>
</template>

<script>

import MyLatestLecture from '@/components/mypage/MyLatestLecture.vue'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'MyLatestLecturePage',
  components: {
    MyLatestLecture
  },
  created() {
  },
  computed: {
    ...mapState(['callLatestLecture'])

  },
  mounted () {
    this.fetchMyLatestLectureList()

  },
  methods: {
    ...mapActions(['fetchMyLatestLectureList'])
  }
}
</script>

<style scoped>
</style>