<template>
  <v-container>
    <h2>강의 이미지 등록</h2>
    <hr>
    <form @submit.prevent="onSubmit">
      <div>
        <label for="thumb">썸네일 이미지: </label>
        <v-file-input label="upload tumbnail image" id="thumb" @change="thumbFile" accept="image/png, image/jpeg, image/jpg"></v-file-input>
        <v-alert border="top" colored-border type="info" elevation="2">
         <ul>
            <li>파일 사이즈 : 5M 이하</li>
            <li>파일 확장자 : png, jpg, jpeg만 가능</li>
          </ul>
        </v-alert>
        <div class="text-center">
          <v-btn @click="onSubmit" color="primary" :disabled="!thumbnailImage">등록</v-btn>
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
      show: null,
    }
  },
  methods: {
     onSubmit() {      
        const lectureId = 1
        const formData = new FormData();
        formData.append("thumbnailImage", this.thumbnailImage);
        formData.append("lectureId", lectureId);

        console.log(formData);
        console.log(this.thumbnailImage);

        axios.post( API_BASE_URL + "/profile/upload/image/thumbnail", formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then(() => {
          this.$router.push('/mypage/lecture/lectureList')
        })
        .catch(err => {
          console.log(err);
        })
    },
    // 파일 변경 시 이벤트 핸들러
    thumbFile(file) {
      this.thumbnailImage = file;
    },
    detailFile(file) {
      this.detailImage = file;
    }
  }

}
</script>

<style>

</style>