<template>
  <div class="mt-5">
    <div v-if="sectionId !== null">
      <div v-for="(section, index) in sectionList" :key="index" class="d-inline">
        <h2 class="d-inline" v-if="section.id == sectionId">섹션{{section.section}}. {{ section.topic }}
        </h2>
      <v-btn class="float-right" @click="uploadLectureIdx(section.lectureList_id)">upload lecture</v-btn>
        <div></div>
      </div>
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
          <v-file-input label="upload video" ref="dur" duration='0' @change="videoFile"></v-file-input>
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
      id: null
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
      formData.append("id", this.id)

      axios.post("http://localhost:7777/lecture/upload/video/lecture", formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
      ).then(res => {

        alert(res)
        this.upload = !this.upload
      }).catch(err => {
        alert(err)
      })

    },
    // getLectureVideo() {
    //   axios.post(`http://localhost:7777/lecture/getLectureVideo/${this.sectionId}`)
    //         .then(res => {
    //           this.$store.commit('saveVideoList', res.data)
    //         })
    //         .catch(err => { console.log(err); })
    // },
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
    uploadLectureIdx(id) {
      this.upload = !this.upload
      this.id = id
    },
  

  }


}
</script>

<style>

</style>