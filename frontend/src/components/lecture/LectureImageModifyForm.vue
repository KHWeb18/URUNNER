<template>
  <v-container>
    <h2>강의 이미지 수정 및 삭제</h2>
    <hr>
    <form @submit.prevent="onSubmit">
      <div>
        <label for="thumb">썸네일 이미지: </label>
        <v-file-input label="upload tumbnail image" id="thumb" @change="thumbFile" accept="image/png, image/jpeg, image/jpg"></v-file-input>
        <div v-if="lectureInfo.thumbPath" class="ma-3">
          <v-img :src="`http://localhost:7777/lecture/image/${lectureInfo.thumbPath}/${lectureInfo.writer}`" width="250">
          </v-img>
        </div>
        <v-alert border="top" colored-border type="info" elevation="2">
         <ul>
            <li>파일 사이즈 : 5M 이하</li>
            <li>파일 확장자 : png, jpg, jpeg만 가능</li>
          </ul>
        </v-alert>

        <label for="detail">디테일 이미지: </label>
        <v-file-input label="upload detail image" id="detil" @change="detailFile" accept="image/png, image/jpeg, image/jpg"></v-file-input>
        <div v-if="lectureInfo.detailPath" class="ma-3">
          <v-img :src="`http://localhost:7777/lecture/image/${lectureInfo.detailPath}/${lectureInfo.writer}`" width="250">
          </v-img>
        </div>
        <v-alert border="top" colored-border type="info" elevation="2">
         <ul>
            <li>파일 사이즈 : 5M 이하</li>
            <li>파일 확장자 : png, jpg, jpeg만 가능</li>
          </ul>
        </v-alert>

        <div class="text-center">
          <v-btn @click="onSubmit" color="primary" :disabled="!thumbnailImage || !detailImage">수정</v-btn>
          <v-btn to="/mypage/lecture/lectureList" color="warning" class="ml-2">취소</v-btn>
        </div>
      </div>
    </form>
  </v-container>
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index.js'
export default {
  data() {
    return {
      thumbnailImage: "",
      detailImage: "",
      show: null,
    }
  },
  props: {
    lectureInfo: {
      type: Object,
      require: true
    }
  },
  methods: {
     async onSubmit() {
      try {
        const lectureId = this.$route.params.lectureId
        const formData = new FormData();
        formData.append("thumbnailImage", this.thumbnailImage);
        formData.append("imageDetail", this.detailImage);
        formData.append("lectureId", lectureId);

        console.log(formData);
        console.log(this.thumbnailImage);

        const { data1 } = await axios.post( API_BASE_URL + "/lecture/upload/image/thumbnail", formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        console.log(data1);
        this.$router.push('/mypage/lecture/lectureList')
      } catch (err) {
        console.log(err);
      }
    },
    // 파일 변경 시 이벤트 핸들러
    thumbFile(file) {
      this.thumbnailImage = file;
    },
    detailFile(file) {
      this.detailImage = file;
    },
  }

}
</script>

<style>

</style>