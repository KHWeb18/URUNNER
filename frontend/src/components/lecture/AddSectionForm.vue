<template>
  <div>
    <v-card class="mt-5">
      <v-card-actions>
         <v-btn class="d-block" @click="addSecttion = !addSecttion" color="purple" outlined>
           add section
        </v-btn>
      </v-card-actions>
      <v-divider></v-divider>
    <v-list>
      <v-list-item-group
        mandatory
        color="purple"
      >
        <v-list-item v-for="(section, index) in sectionList" :key="section.id">
          <v-list-item-content>
            <v-list-item-title @click="goToUploadLecture(section.lectureList_id, section.topic)">
              <div class="text-h7">섹션{{ index + 1 }}.{{ section.topic }}</div>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
    <v-dialog v-model="addSecttion" width="400">
      <v-card>
        <v-card-title>
          add section
        </v-card-title>
        <v-card-text>
          <h2>{{ section }}.section</h2>
          <v-text-field label="topic" v-model="topic"/>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="registerSection">등록</v-btn>
          <v-btn @click="cancel">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  components: {
  },
  data() {
    return {
      addSecttion: false,
      topic: '',
      lectureId: this.$route.params.lectureId
    }
  },
  computed: {
    sectionList() {
      return this.$store.state.sectionList;
    },
    section() {
      return this.$store.state.sectionList.length + 1;
    }
  },
  created() {
    this.getLectureSection();
  },
  methods: {
    registerSection() {
      const data = {
        topic: this.topic,
        lectureId: this.lectureId, 
      }
      axios.post('http://localhost:7777/mypage/lecture/saveLectureSection', data)
          .then(() => {
            this.getLectureSection();
            this.addSecttion = !this.addSecttion;
            this.topic = '';
          })
          .catch(error => {
            console.log(error);
          })
    },
    getLectureSection() {
      const data = {
        lectureId: this.lectureId
      }
      axios.post('http://localhost:7777/mypage/lecture/getLectureSection', data)
          .then(res => {
            console.log(res.data);
            // 페이징 data구성됨
            this.$store.commit('saveSectionList', res.data.content);
          })
          .catch(error => {
            console.log(error);
          })
    },
    cancel() {
      this.addSecttion = !this.addSecttion
    },
    goToUploadLecture(id, topic) {
      const info = {id, topic}
      this.$emit('goToUploadLecture', info);
    }
  },
}
</script>

<style>

</style>