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
        <v-btn @click="onSubmit">등록</v-btn>
        <v-btn to="/mypage/lecture/lectureList">취소</v-btn>
      </div>
    </form>
  </v-container>
</template>

<script>
<<<<<<< HEAD
<<<<<<< HEAD
import axios from 'axios';
import { API_BASE_URL } from '@/constants/index.js'

=======
<<<<<<< HEAD
=======
import axios from 'axios';
import { API_BASE_URL } from '@/constants/index.js'

>>>>>>> upstream/main
>>>>>>> upstream/main
=======
import axios from 'axios';
import { API_BASE_URL } from '@/constants/index.js'

>>>>>>> upstream/main
export default {
  data() {
    return {
      lectureInfo: {
        title: '',
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
        writer: null,
=======
>>>>>>> upstream/main
>>>>>>> upstream/main
=======
>>>>>>> upstream/main
        description: '',
        price: null,
        category: ''
      },
      temp_category: [], 
<<<<<<< HEAD
<<<<<<< HEAD
      tags: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker'],
      dto: []
=======
<<<<<<< HEAD
      tags: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker']
=======
      tags: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker'],
      dto: []
>>>>>>> upstream/main
>>>>>>> upstream/main
=======
      tags: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker'],
      dto: []
>>>>>>> upstream/main
    }
  },
  methods: {
    onSubmit() {
<<<<<<< HEAD
<<<<<<< HEAD
      this.temp_category.filter(c => this.lectureInfo.category += c + ',')
=======
<<<<<<< HEAD
      this.lectureInfo.writer = 2;
      this.temp_category.filter(c => this.lectureInfo.category += c + ',')
      console.log(this.lectureInfo);
=======
=======
>>>>>>> upstream/main
      this.temp_category.filter(c => this.lectureInfo.category += c + ',')
>>>>>>> upstream/main

      axios.post(API_BASE_URL + "/lecture/newlecture", { lectureInfo:this.lectureInfo })
      .then(res => {
        alert(res.data)
      })
      .catch(err => {
        alert(err)
      })

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> upstream/main
>>>>>>> upstream/main
=======
>>>>>>> upstream/main
      // axios.post(/lecture/registerLecture)
      //       .then(res => { console.log(res.data); })
      //       .catch(err => { console.log(err); })
      // 강의 기본 정보를 저장
      this.$router.push('/mypage/lecture/lectureList');
    }
  },
}
</script>

<style>

</style>