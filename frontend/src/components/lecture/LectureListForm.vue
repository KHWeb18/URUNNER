<template>
  <v-container>
    <div class="d-flex justify-space-between mb-2">
      <h3>나의 강의 현황</h3>
      <v-btn color="primary" to="/mypage/lecture/registerLecture">강의 등록</v-btn>
    </div>
    <hr/>
    <!--  맵핑-->
      <!-- 오브젝트가 props에서 안찍히네요 info바로 넣고 info.price 찍으려는데 type 이랑 다 설정해도 안먹힘니다.-->
    <lecture-status-bar 
      v-for="(info) in getList" :key="info.id"
      :info="info"
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
  }
}
</script>

<style>

</style>