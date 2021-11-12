<template>
  <div class="mt-5">
    <div v-if="sectionId !== null">
      <div class="d-flex justify-space-between">
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
              <td>
                <v-btn icon>
                  <v-icon>mdi-wrench-outline</v-icon>
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

  </div>  
</template>

<script>
import axios from 'axios'
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
    }
  },
  computed: {
    lectureVideo() {
      return this.$store.state.lectureVideoList
    },
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
    }
  }
}
</script>

<style>
</style>