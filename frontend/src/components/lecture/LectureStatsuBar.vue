<template>
  <v-card
    class="mx-auto d-sm-flex"
  >
    <v-img v-if="true"
      height="200" width="200"
      :src="this.isImgagePath"
    ></v-img>
    <v-card-text v-else class="pa-0" style="width: 200px; height: 200px;">
      <div style="width: 100%; height: 100%;" class="d-flex align-center">
        <v-btn rounded color="primary" class="d-block ma-auto"
          to="/mypage/lecture/registerLectureImage">이미지등록</v-btn>
      </div>
    </v-card-text>
    <v-card-text>
      <!-- 이부분 매핑............... -->
      <v-btn icon class="float-right"><v-icon>mdi-wrench-outline</v-icon></v-btn>
      <div class="text-h6 black--text">title: {{ title }}</div>
      <div class="text-h6 black--text">price: {{ price }} ₩</div>
      <!-- 카테고리 나중에 dto에 추가할예정 -->
      <div class="text-h6 black--text">category: vue, front, javasecript</div>
      <v-btn rounded color="warning" class="mt-5 d-block" @click="lectureList">커리큘럼 관리</v-btn>
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
      isImgagePath: `http://localhost:7777/lecture/image/${this.thum}/${this.writer}`,
      isProgress: false,
      // lectureList: [],
    }
  },
  props: {
    info: {
      require: true
    },
    title: {
      require: true
    },
    thum: {
      require: false
    },
    price: {
      require: false
    },
    writer: {
      require: false
    }
  },
  computed: {
    reload() {
      return this.isImgagePath
    }

  },
  methods: {
    lectureList() {
      this.$store.state.lectureIndex = this.info
      
      this.$router.push({ name: 'ManageLecturePage'})
    },
  }
}
</script>

<style scoped>
</style>