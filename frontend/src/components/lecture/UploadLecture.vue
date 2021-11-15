<template>
  <div class="mt-5">
    <div v-if="sectionId !== null">
      <div class="d-sm-flex justify-space-between">
        <h2>Section: {{ sectionTopic }}</h2>
      <v-btn color="secondary" @click="upload = !upload">upload lecture</v-btn>
      </div>
      <hr class="mt-5"/>

      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th></th>
              <th class="text-left">
                Title
              </th>
              <th class="text-left">
                Duration
              </th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(video, index) in lectureVideoList" :key="video.id">
              <td>{{ index + 1 }}</td>
              <td>{{ video.title }}</td>
              <td>{{ calcDuration(video.duration) }}</td>
              <td class="d-flex justify-space-around">
                <v-btn icon>
                  <v-icon @click="openChangeLectureVideo(video.id)">mdi-lead-pencil</v-icon>
                </v-btn>
                <v-btn icon @click="deleteLectureVideo(video.id)">
                  <v-icon>mdi-trash-can</v-icon>
                </v-btn>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>

    <div v-else>
     <h1>섹션을 추가하고 강의를 업로드하세요.</h1>
    </div>
    
    <v-btn to="/mypage/lecture/lectureList">강의리스트로 가기</v-btn>

    <v-dialog v-model="upload" width="600">
      <v-card>
        <v-card-title>
          upload lecture
        </v-card-title>
        <v-card-text>
          <v-text-field label="Title" v-model="title"></v-text-field>
          <v-textarea label="description" v-model="description"></v-textarea>
          <v-file-input label="upload video" ref="dur" duration='0' @change="videoFile"></v-file-input>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="registerVideo" color="primary" :disabled="!title || !description || !video">등록</v-btn>
          <v-btn @click="cancel" color="warning">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="modifyUpload" width="600">
      <v-card>
        <v-card-title>
          modify lecture
        </v-card-title>
        <v-card-text>
          <v-text-field label="Title" v-model="title"></v-text-field>
          <v-textarea label="description" v-model="description"></v-textarea>
          <v-file-input label="upload video" ref="dur" duration='0' @change="videoFile"></v-file-input>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="modifyVideo" color="primary" :disabled="!title || !description || !video">수정</v-btn>
          <v-btn @click="modifyUpload = !modifyUpload" color="warning">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </div>  
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
export default {
  props: {
    sectionId: {
      type: Number,
      require: true,
    },
    sectionTopic: {
      type: String,
      require: true
    },
    lectureVideoList: {
      type: Array,
      require: true,
    }
  },
  data() {
    return {
      upload: false,
      title: '',
      description: '',
      video: '',
      modifyUpload: false,
      videoId: null
    }
  },
  computed: {
    // lectureVideo() {
    //   return this.$store.state.lectureVideoList
    // },
    sectionList() {
      return this.$store.state.sectionList
    }
  },
  methods: {
    registerVideo() {
      var ele = this.$refs.dur
      const formData = new FormData();
      formData.append("video", this.video);
      formData.append("duration", ele.duration)
      formData.append("title", this.title)
      formData.append("description", this.description)
      formData.append("id", this.sectionId)
      axios.post("http://localhost:7777/lecture/upload/video/lecture", formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
      ).then(() => {
        this.upload = !this.upload
        this.$emit('uploadLecture')
        this.title = ''
        this.description = ''
        this.video = ''
      }).catch(err => {
        alert(err)
      })
    },
    videoFile(file) {
       this.video = file
        var url = URL.createObjectURL(this.video);
        var audioElement = new Audio(url);
        var duration;
        var ele = this.$refs.dur
        audioElement.addEventListener("loadedmetadata", function() {
          duration = audioElement.duration
          ele.duration = Math.round(duration)
        });
    },
    cancel() {
      this.upload = !this.upload
    },
    calcDuration(value) {
      var minutes = Math.floor(value / 60)
      var seconds = Math.floor((value - minutes * 60))
      return `${minutes}:${seconds}`
    },
    deleteLectureVideo(videoId) {
     if(confirm('해당 강의 영상 삭제하시겠습니까?')) {
        axios.delete(`${API_BASE_URL}/lecture/deleteLectureVideo/${videoId}`)
        .then(() => {
          alert('정상적으로 삭제되었습니다. ')
          this.$emit('uploadLecture')
        })
        .catch(err => {console.log(err);})
     }
    },
    openChangeLectureVideo(videoId) {
      axios.get(`${API_BASE_URL}/lecture/getLectureVideoInfo/${videoId}`)
      .then(({ data }) => {
        this.title = data.opData.title;
        this.description = data.opData.description;
        this.videoId = data.opData.id;

        this.modifyUpload = !this.modifyUpload
      })
      .catch(err => {console.log(err);})
    },
    modifyVideo() {
      var ele = this.$refs.dur
      const formData = new FormData();
      formData.append("video", this.video);
      formData.append("duration", ele.duration)
      formData.append("title", this.title)
      formData.append("description", this.description)
      formData.append("id", this.videoId)
      // 비디오 업로드 수정하는 메서드 sectionId는 변동 없으므로 붙여넣지 않았고 
      // 수정이기에 수정할 lecture_video 테이블의 아이디값 마지막에 append 함
      axios.post("http://localhost:7777/lecture/modify/video/lecture", formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
      ).then(() => {
        this.modifyUpload = !this.modifyUpload
        this.$emit('uploadLecture')
        this.title = ''
        this.description = ''
        this.video = ''
      }).catch(err => {
        alert(err)
      })
    }
  }
}
</script>

<style>
</style>