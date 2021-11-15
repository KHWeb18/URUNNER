<template>
  <v-container>
    <h2>강의 기본정보 등록</h2>
    <hr>
    <form @submit.prevent="onSubmit">
      <div>
        <v-text-field label="title" v-model="lectureInfo.title"></v-text-field>
        <v-text-field label="price" type="number" v-model="lectureInfo.price"></v-text-field>
        <v-select
          :items="items2"
          label="grade"
          v-model="lectureInfo.grade"
         ></v-select>
        <v-textarea label="description" v-model="lectureInfo.description"></v-textarea>
        <modify-content-editor @submitContent="submitContent"/>
        <v-select v-model="temp_category"
          :items="items"
          label="category"
          multiple
          chips
          hint="What is the lecture about?"
          persistent-hint
        ></v-select>
        <div class="text-center">
          <v-btn @click="onSubmit" color="primary" :disabled="!lectureInfo.title || !lectureInfo.price || !lectureInfo.description || !temp_category">수정</v-btn>
          <v-btn @click="deleteLectureInfo" class="ml-2" color="red">삭제</v-btn>
          <v-btn to="/mypage/lecture/lectureList" class="ml-2" color="warning">취소</v-btn>
        </div>
      </div>
    </form>
  </v-container>
</template>

<script>
import axios from 'axios';
import EventBus from '@/event'
import { API_BASE_URL } from '@/constants/index.js'
import { getLectureList } from '@/util/AxiosMethod'
import ModifyContentEditor from '@/components/lecture/editor/ModifyContentEditor.vue';
export default {
  components: {
    ModifyContentEditor
  },
  data() {
    return {
      lectureInfo: {
        title: '',
        description: '',
        price: null,
        category: '',
        content: '',
        grade: '',
      },
      temp_category: [], 
      items: this.$store.state.category,
      items2: ['입문', '초급', '중급 이상'],
    }
  },
  created() {
    this.getLectureInfo()
  },
  methods: {
    onSubmit() {
      const lectureId = this.$route.params.lectureId
      this.temp_category.filter(c => this.lectureInfo.category += c + ',')
      const lectureInfo = {
            lectureId: lectureId,
            title: this.lectureInfo.title,
            price: this.lectureInfo.price,
            category: this.lectureInfo.category,
            description: this.lectureInfo.description,
            content: this.lectureInfo.content,
            grade: this.lectureInfo.grade
          } 
      axios.put(`${API_BASE_URL}/lecture/modifyLecture`, { lectureInfo: lectureInfo })
            .then(() => {
               getLectureList();
               alert("강의 기본정보를 수정하였습니다.")
               this.$router.push('/mypage/lecture/lectureList')
            })
             .catch(err => {console.log(err);})
    },
    getLectureInfo() {
      const lectureId = this.$route.params.lectureId
       axios.get(`${API_BASE_URL}/lecture/getBasicInfo/${lectureId}`)
        .then(({ data }) => {
          EventBus.$emit('contentHtml', data.opData.content)
          this.lectureInfo.title = data.opData.title
          this.lectureInfo.price = data.opData.price
          this.lectureInfo.description = data.opData.desc
          this.lectureInfo.content = data.opData.content
          this.lectureInfo.grade = data.opData.grade
          data.opData.category.filter(c => {
            this.temp_category.push(c.categoryName)
          })
        })
        .catch(err => {console.log(err);})
    },
    deleteLectureInfo() {
      if(confirm('해당 강의를 삭제하시겠습니까?')) {
        const lectureId = this.$route.params.lectureId
        axios.delete(`${API_BASE_URL}/lecture/deleteLecture/${lectureId}`)
        .then(() => {
          alert("강의를 삭제하였습니다.")
          getLectureList();
          this.$router.push('/mypage/lecture/lectureList')
        })
        .catch(err => {console.log(err);})
      }
    },
     submitContent(content) {
      this.lectureInfo.content = content
    }

  },
}
</script>

<style>

</style>