<template>
  <lecture-list-form
      @inProgressToFalse="inProgressToFalse"
      @inProgressToTrue="inProgressToTrue"></lecture-list-form>
</template>

<script>
import LectureListForm from '@/components/lecture/LectureListForm.vue'
import { API_BASE_URL } from '@/constants/index'
import axios from 'axios'

export default {
  components: {
    LectureListForm,
  },
  created() {
    this.getLectureList()
  },
  methods: {
    getLectureList() {
      axios.get(`${API_BASE_URL}/lecture/getLectureList`)
        .then(res => {
            this.$store.state.getLectureList = res.data 
            console.log(JSON.stringify(res.data))
        })
        .catch(err => {
            alert(err)
        })
    },
    inProgressToFalse(id) {
      axios.post(`${API_BASE_URL}/lecture/inProgressToFalse/${id}`)
            .then(() => {
              alert('강의 상태를 "중단" 처리하였습니다.')
              this.getLectureList();
              // lecture 테이블의 해당 id 값을 필드에서 inprogress를 false로 
            })
            .catch((err) => {console.log(err);})
    },
    inProgressToTrue(id) {
      axios.post(`${API_BASE_URL}/lecture/inProgressToTrue/${id}`)
            .then(() => {
              alert('강의 상태를 "진행" 처리하였습니다.')
              // lecture 테이블의 해당 id 값을 필드에서 inprogress를 true로 
              this.getLectureList()
            })
            .catch((err) => {console.log(err);})
    },
  }
}
</script>

<style>

</style>