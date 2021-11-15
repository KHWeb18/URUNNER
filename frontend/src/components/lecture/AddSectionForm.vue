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
          <v-list-item-icon>
             <v-btn icon @click="openModifyTopic(section.lectureList_id, section.topic)">
               <v-icon>mdi-lead-pencil</v-icon>
             </v-btn>
             <v-btn icon @click="deleteSection(section.lectureList_id)">
               <v-icon>mdi-trash-can</v-icon>
             </v-btn>
          </v-list-item-icon>
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

    <v-dialog v-model="modifyTopic" width="400">
      <v-card>
        <v-card-title>
          Modify Topic
        </v-card-title>
        <v-card-text>
          <v-text-field label="topic" v-model="topic"/>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="modifySectionTopic">수정</v-btn>
          <v-btn @click="modifyTopic = !modifyTopic">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import axios from 'axios';
import { 
    API_BASE_URL,
} from '@/constants/index'
export default {
  components: {
  },
  data() {
    return {
      addSecttion: false,
      topic: '',
      modifyTopic: false,
      lectureId: this.$route.params.lectureId,
      lectureListId: null
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
    },
    openModifyTopic(id, topic) {
      this.lectureListId = id
      this.topic = topic,
      this.modifyTopic = !this.modifyTopic
    },
    modifySectionTopic() {
      axios.put(`${API_BASE_URL}/lecture/modifySectionTopic`,{
        lectureList_id: this.lectureListId,
        topic: this.topic
      })
            .then(() => {
              this.getLectureSection()
              this.modifyTopic = !this.modifyTopic
            })
            .catch(err => {console.log(err);})
    },
    deleteSection(lectureListId) {
      if(confirm('해당 섹션을 삭제하시겠습니까?')) {
         axios.delete(`${API_BASE_URL}/lecture/deleteSection/${lectureListId}`)
        .then(() => {
          alert('삭제되었습니다.')
          this.getLectureSection()
          this.topic= ''
        })
        .catch(err => {console.log(err);})
      }
    }
  },
}
</script>

<style>

</style>