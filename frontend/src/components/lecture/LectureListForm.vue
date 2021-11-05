<template>
  <v-container>
    <div class="d-flex justify-space-between mb-2">
      <h3>나의 강의 현황</h3>
      <v-btn color="primary" to="/mypage/lecture/registerLecture">강의 등록</v-btn>
    </div>
    <hr/>
    <!--  맵핑-->
    <div v-for="(info, index) in this.$store.state.getLectureList" :key="index">
      <!-- 오브젝트가 props에서 안찍히네요 info바로 넣고 info.price 찍으려는데 type 이랑 다 설정해도 안먹힘니다.-->
    <lecture-status-bar :writer="info.writer" 
    :price="`${info.price}`" 
    :title="`${info.title}`" 
    :info="`${info.id}`" 
    :thum="`${info.thumbPath}`" 
    :num="`${index}`" 
    :key="`${index}`"
    ></lecture-status-bar>
    </div>
  </v-container>  
</template>

<script>
import LectureStatusBar from './LectureStatsuBar.vue'
import { getLectureList } from '../../util/AxiosMethod'

export default {
  data () {
    return {
      state: this.$store.state.getLectureList
    }
  },
  components: {
    LectureStatusBar
  },
  created() {
    return getLectureList()
  },
  computed: {
    getLectureList

  }
}
</script>

<style>

</style>