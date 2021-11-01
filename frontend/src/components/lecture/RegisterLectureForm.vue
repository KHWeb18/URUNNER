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
export default {
  data() {
    return {
      lectureInfo: {
        title: '',
        writer: null,
        description: '',
        price: null,
        category: ''
      },
      temp_category: [], 
      tags: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker']
    }
  },
  methods: {
    onSubmit() {
      this.lectureInfo.writer = 2;
      this.temp_category.filter(c => this.lectureInfo.category += c + ',')
      console.log(this.lectureInfo);
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