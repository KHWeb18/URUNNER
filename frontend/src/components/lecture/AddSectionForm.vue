<template>
  <div>
    <v-btn class="d-block" @click="addSecttion = !addSecttion">add section</v-btn>
        <v-list dense>
          <v-list-item v-for="(section, index) in sectionList" :key="index">
            <v-list-item-title @click="goToUploadLecture(section.LectureList_id)">
              섹션{{ index + 1 }}.{{ section.topic }}
            </v-list-item-title>
          </v-list-item>
       </v-list>

    <v-dialog v-model="addSecttion" width="400">
      <v-card>
        <v-card-title>
          add section
        </v-card-title>
        <v-card-text>
          <h2>section</h2>
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
      topic: 'tt',
      lectureId: this.$store.state.lectureIndex
    }
  },
  computed: {
    sectionList() {
      return this.$store.state.sectionList;
    },
  },
  created() {
    this.getLectureSection();
  },
  methods: {
    registerSection() {
      const data = {
        topic: this.topic,
        lectureId: this.lectureId, // 강의 현황에서 강의 리스트중 특정 리스트의 커리큘럼 관리 버튼을 클릭했을 때 나오는 것으로 id를 파라미터도 받음
      }
      axios.post('http://localhost:7777/mypage/lecture/saveLectureSection', data)
          .then(res => {
            console.log(res.data);
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
            console.log(this.$store.state.sectionList);
          })
          .catch(error => {
            console.log(error);
          })
    },
    cancel() {
      this.addSecttion = !this.addSecttion
    },
    goToUploadLecture(id) {
      this.$emit('goToUploadLecture', id)
    }
  },
}
</script>

<style>

</style>