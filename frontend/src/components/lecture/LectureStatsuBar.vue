<template>
  <v-card class="d-sm-flex ma-3">
    <div style="min-width: 30%; min-height: 200px">
       <v-img v-if="info.thumbPath !== null"
       width="100%" max-height="200px"
        :src="`http://localhost:7777/lecture/image/${info.thumbPath}/${info.writer}`"
      >
        <v-btn icon class="float-right"><v-icon>mdi-wrench-outline</v-icon></v-btn>
      </v-img>
      <v-card-actions v-else style="padding: 0;height: 100%">
        <div class="ma-auto mt-5">
          <v-btn rounded color="primary"
            @click="goToInsertImage(info.id)">이미지등록</v-btn>
        </div>
      </v-card-actions>
    </div>
    <v-card-text style="border: 0.3rem solid lightgray;min-width: 50%">
      <!-- 이부분 매핑............... -->
      <v-btn icon class="float-right"><v-icon>mdi-wrench-outline</v-icon></v-btn>
      <div class="text-h6 black--text">Title: <span>{{ info.title }}</span></div>
      <div class="text-h6 black--text">Price: <span>{{ getCurrencyFormat(info.price) }}</span> ₩</div>
      <!-- 카테고리 나중에 dto에 추가할예정 -->
      <div class="text-h6 black--text">Category: <span>vue, front, javasecript</span></div>
      <v-btn rounded color="warning" class="mt-3 d-block" @click="goToManageLecture(info.id)">커리큘럼 관리</v-btn>
    </v-card-text>

    <v-card-actions class="pa-0" style="min-width: 20%;">
     <div v-if="info.inProgress" style="width: 100%;height: 100%">
        <p class="text-center pa-3">status: <v-chip color="green">진행중</v-chip></p>
        <v-btn color="red" class="d-block ma-auto mb-3">중단하기</v-btn>
      </div>
      <div v-else style="width: 100%;height: 100%">
        <p class="text-center pa-3">Status: <v-chip color="yellow">준비중</v-chip></p>
        <v-btn color="green" class="d-block ma-auto mb-3">진행하기</v-btn>
      </div>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    info: {
      type: Object,
      require: true
    },
  },
  computed: {
    isImgagePath() {
      return `http://localhost:7777/lecture/image/${this.info.thumbPath}/${this.info.writer}`
    }
  },
  methods: {
    getCurrencyFormat(value) {
      return this.$currencyFormat(value);
    },
    goToManageLecture(id) {
      this.$router.push({ name: 'ManageLecturePage', params: { lectureId: id }})
    },
    goToInsertImage(id) {
      this.$router.push({ name: 'registerLectureImage', params: { lectureId: id }})
    }
  }
}
</script>

<style scoped>

span {
  color: darkslategrey;
  font-weight: bold;
}

</style>