<template>
  <v-container>
    <div class="d-flex justify-space-between mb-2">
      <h3>나의 강의 현황</h3>
      <v-btn color="primary" to="/mypage/lecture/registerLecture">강의 등록</v-btn>
    </div>
    <hr/>

    <lecture-status-bar 
      v-for="(info) in getList" :key="info.id"
      :info="info"
      @inProgressToFalse="inProgressToFalse"
      @inProgressToTrue="inProgressToTrue"
    ></lecture-status-bar>
  </v-container>  
</template>

<script>
import LectureStatusBar from './LectureStatsuBar.vue'
export default {
  components: {
    LectureStatusBar
  },
  computed: {
    getList: {
      cache: false,
        get() {
          return this.$store.state.getLectureList
        }
     }
  },
  methods: {
    inProgressToTrue(id) {
      this.$emit('inProgressToTrue', id)
    },
    inProgressToFalse(id) {
      this.$emit('inProgressToFalse', id)
    },
  }
}
</script>

<style>

</style>