<template>
  <div>
    <lecture-video-play-form
        :lectureDetailInfo="lectureDetailInfo"
        :lectureVideoList="lectureVideoList"
        :videoInfo="videoInfo"
        />
  </div>
</template>

<script>
import LectureVideoPlayForm from '@/components/lecture_detail/play/LectureVideoPlayForm.vue'
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
export default {
  components: {
    LectureVideoPlayForm
  },
  data() {
    return {
      lectureDetailInfo: {},
      lectureVideoList: [],
      videoId: this.$route.params.videoId,
      lectureId: this.$route.params.lectureId
    }
  },
  created() {
    this.fetchLectureInfo(this.lectureId);
    this.fetchVideoInfo(this.videoId);
  },
  methods: {
    fetchLectureInfo(lectureId) {
      axios.get(`${API_BASE_URL}/lecture/getLectureDetailInfo/${lectureId}`)
            .then(({ data }) => {
              console.log(data);
              this.lectureDetailInfo = data.opData
              this.lectureVideoList = data.opData2
            })
            .catch(err => { console.log(err); })
    },
    fetchVideoInfo(videoId) {
      axios.get(`${API_BASE_URL}/lecture/getVideoInfo/${videoId}`)
            .then(({ data }) => {
              this.videoInfo = data.opData
              console.log(this.videoInfo);
            })
            .catch(err => { console.log(err); })
    },
  },
};
</script>

<style>

</style>