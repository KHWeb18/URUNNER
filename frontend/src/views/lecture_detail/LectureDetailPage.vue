<template>
 <lecture-detail-form
  :lectureDetailInfo="lectureDetailInfo"
  :lectureVideoList="lectureVideoList"/>
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
import LectureDetailForm from '@/components/lecture_detail/LectureDetailForm.vue'
export default {
  components: {
    LectureDetailForm
  },
  data() {
    return {
      lectureDetailInfo: {},
      lectureVideoList: [],
    }
  },
  created() {
    this.fetchLectureInfo();
  },
  methods: {
    fetchLectureInfo() {
      const lectureId = this.$route.params.lectureId
      axios.get(`${API_BASE_URL}/lecture/getLectureDetailInfo/${lectureId}`)
      //테이블 lectureId 로 lecture/lecture_list/lecture_video 데이터 불러오기
            .then(({ data }) => {
              console.log(data);
              this.lectureDetailInfo = data.opData
              this.lectureVideoList = data.opData2
            })
            .catch(err => { console.log(err); })
    }
  },
}
</script>

<style>

</style>