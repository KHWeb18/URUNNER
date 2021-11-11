<template>
  <v-container>
    <h2>강의 기본정보 등록</h2>
    <hr>
    <form @submit.prevent="onSubmit">
      <div>
        <v-text-field label="title" v-model="lectureInfo.title"></v-text-field>
        <v-text-field label="price" type="number" v-model="lectureInfo.price"></v-text-field>
        <v-textarea label="description" v-model="lectureInfo.description"></v-textarea>
        <v-select v-model="temp_category"
          :items="tags"
          label="category"
          multiple
          chips
          hint="What is the lecture about?"
          persistent-hint
        ></v-select>
        <div class="text-center">
          <v-btn @click="onSubmit" color="primary" :disabled="!lectureInfo.title || !lectureInfo.price || !lectureInfo.description || !temp_category">등록</v-btn>
          <v-btn to="/mypage/lecture/lectureList" class="ml-2" color="warning">취소</v-btn>
        </div>
      </div>
    </form>
  </v-container>
</template>

<script>
import axios from 'axios';
import { API_BASE_URL } from '@/constants/index.js'
import { getLectureList } from '@/util/AxiosMethod'

export default {
  data() {
    return {
      lectureInfo: {
        title: '',
        description: '',
        price: null,
        category: '',
        lectureId: 1
      },
      temp_category: [], 
      tags: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker'],
      dto: []
    }
  },
  methods: {
    onSubmit() {
      this.temp_category.filter(c => this.lectureInfo.category += c + ',')

      axios.put(API_BASE_URL + "/lecture/modifyLectureV2", { lectureInfo: this.lectureInfo })
      .then(res => {
        getLectureList();
        alert("강의 기본정보 등록을 성공하였습니다.")
        console.log(res.data);
        this.$router.push('/mypage/lecture/lectureList');
      })
      .catch(err => {
        alert(err)
      })
    }
  },
}
</script>

<style>

</style>