<template>
 <v-container>
    <h2>강의 커리큘럼 관리</h2>
    <hr>
    <v-row>
      <v-col cols="12" md="3">
        <add-section-form @goToUploadLecture="goToUploadLecture"></add-section-form>
      </v-col>
      <v-col cols="12" md="9">
        <upload-lecture :sectionId="sectionId"
                        :sectionTopic="sectionTopic"
                        :lectureVideoList="lectureVideoList"
                        @uploadLecture="uploadLecture"></upload-lecture>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AddSectionForm from '@/components/lecture/AddSectionForm'
import UploadLecture from '@/components/lecture/UploadLecture'
import axios from 'axios'
export default {
  components: {
    AddSectionForm,
    UploadLecture
  },
  data() {
    return {
      sectionId: null,
      sectionTopic: ''
    }
  },
  computed: {
    lectureVideoList() {
      return this.$store.state.lectureVideoList
    }
  },
  methods: {
    goToUploadLecture(info) {
       this.sectionId = info.id;
       this.sectionTopic = info.topic
       this.getLectureVideoList();
    },
    getLectureVideoList() {
      const data = {
        id: this.sectionId
      }
      axios.post("http://localhost:7777/mypage/lecture/getLectureVideoList", data)
            .then(res => {
              console.log(res.data);
              this.$store.state.lectureVideoList = res.data
            })
            .catch(err => {
              console.log(err);
            })
    },
    uploadLecture() {
      this.getLectureVideoList()
    }
  }
}
</script>

<style>

</style>