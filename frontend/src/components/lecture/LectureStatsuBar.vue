<template>
  <v-card
    class="mx-auto d-sm-flex"
  >
    <v-img v-if="isImgagePath"
      height="200" width="200"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-text v-else class="pa-0" style="width: 200px; height: 200px;">
      <div style="width: 100%; height: 100%;" class="d-flex align-center">
        <v-btn rounded color="primary" class="d-block ma-auto"
          to="/mypage/lecture/registerLectureImage">이미지등록</v-btn>
      </div>
    </v-card-text>

    <v-card-text>
      <v-btn icon class="float-right"><v-icon>mdi-wrench-outline</v-icon></v-btn>
      <div class="text-h6 black--text">title: testtest</div>
      <div class="text-h6 black--text">price: 112,000 ₩</div>
      <div class="text-h6 black--text">category: vue, front, javasecript</div>
      <v-btn rounded color="warning" class="mt-5 d-block">커리큘럼 관리</v-btn>
    </v-card-text>

    <v-card-actions class="pa-0" style="min-width: 150px;">
     <div v-if="isProgress" style="width: 100%;" >
        <p class="text-center">status: <v-chip color="green">진행중</v-chip></p>
        <v-btn color="red" class="d-block mx-auto">중단하기</v-btn>
      </div>
      <div v-else style="width: 100%;" >
        <p>status: <v-chip color="yellow">준비중</v-chip></p>
        <v-btn color="green" class="d-block mx-auto">진행하기</v-btn>
      </div>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      isImgagePath: '',
      isProgress: false,
      lectureList: []
    }
  },
  methods: {
    getLectureList() {
      // const id = this.$store.state.userId
      // axios.get('/lecture/getLectureList', {id: id})
      //       .then(res => {
      //         this.lectureList = res.data
      //       })
      //       .catch(err => {
      //         console.log(err);
      //       })
      // 사용자의 아이디를 바디에다 보내면 아이디와 일치하는 lecture table과 lecture_image의 전부 보냄 그럼 lectureList에 담아서 v-for 로 뿌려줄 것
    }
  }
}
</script>

<style scoped>
</style>