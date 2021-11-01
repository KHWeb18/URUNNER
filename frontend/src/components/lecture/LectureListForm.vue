<template>
  <v-container>
    <div class="d-flex justify-space-between mb-2">
      <h3>나의 강의 현황</h3>
      <v-btn color="primary" to="/mypage/lecture/registerLecture">강의 등록</v-btn>
    </div>
    <hr/>
    <lecture-status-bar></lecture-status-bar>
  </v-container>  
</template>

<script>
import LectureStatusBar from './LectureStatsuBar.vue'
export default {
  components: {
    LectureStatusBar
  }
}
</script>

<style>

</style>