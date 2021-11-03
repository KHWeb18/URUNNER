<template>
  <div class="mt-5">
    <div v-if="sectionId !== null">
      <div v-for="(section, index) in sectionList" :key="index" class="d-inline">
        <h2 class="d-inline" v-if="section.id == sectionId">섹션{{section.section}}. {{ section.topic }}</h2>
      </div>
      <v-btn class="float-right" @click="upload = !upload">upload lecture</v-btn>
      <hr class="mt-5"/>
      <ul v-for="(video, index) in lectureVideo" :key="index">
        <li v-if="video.sectionId == sectionId">{{ video.title }}</li>
      </ul>      
    </div>      
    <div v-else>
     <h1>섹션을 추가하고 강의를 업로드하세요.</h1>
    </div>

    <v-dialog v-model="upload" width="600">
      <v-card>
        <v-card-title>
          upload lecture
        </v-card-title>
        <v-card-text>
          <v-text-field label="Title" v-model="title"></v-text-field>
          <v-textarea label="description" v-model="description"></v-textarea>
          <v-file-input label="upload video" @change="videoFile"></v-file-input>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="registerVideo">등록</v-btn>
          <v-btn @click="cancel">취소</v-btn>
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
      type: Number
    }
  },
  data() {
    return {
      upload: false,
      title: '',
      description: '',
      video: '',
      duration: null
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

      const formData = new FormData();
      formData.append("video", this.video);

      axios.post("http://localhost:7777/lecture/upload/video/lecture/:sectionId", formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then(() => {
          const data = {
              duration: this.duration,
              title: this.title,
              description: this.description,
              sectionId: this.sectionId,
              // video_path를 따로 db에 저장하는게 좋을까요 아님 위에 axios보내고 비디오 저장 후 video_path
              // 를 res.data로 보내면 그걸 변수에 담아 여기 data에 담아 한번에 세이브가 좋을까요?
            }
          axios.post('http://localhost:7777/lecture/saveLectuerVideo', data)
              .then(() => { this.upload = !this.upload })
              .catch(err => {console.log(err);})
        }).catch(err => {
          console.log(err);
        })
    },
    getLectureVideo() {
      axios.post(`http://localhost:7777/lecture/getLectureVideo/${this.sectionId}`)
            .then(res => {
              this.$store.commit('saveVideoList', res.data)
            })
            .catch(err => { console.log(err); })
    },
    videoFile(file) {
      this.video = file

      var url = URL.createObjectURL(this.video);

        var audioElement = new Audio(url);

        var duration;
        audioElement.addEventListener("loadedmetadata", function () {
            duration = audioElement.duration;
            console.log(duration);
            this.duration = Math.round(duration)
        });
    },

  }


}
</script>

<style>

</style>