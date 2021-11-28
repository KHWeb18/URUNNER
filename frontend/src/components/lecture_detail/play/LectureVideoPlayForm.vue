<template style="height: 100vh">
  <div>
    <v-row>
      <v-col cols="12" lg="8">
         <div>
          <header class="d-flex justify-space-between">
          <h2 class="text-h7 text-md-h4 font-weight-bold pa-3">TOPIC : {{ videoInfo.title }}</h2>
          <div class="mt-3 mr-4">
            <v-btn color="blue" dark @click="openStudentComment = !openStudentComment">
              <v-icon>mdi-star</v-icon>
              수강평남기기
            </v-btn>
          </div>
          </header>

            <vue-plyr ref="plyr">
            <video
                  controls
                  playsinline
                >
                  <source
                    size="720"
                    :src="`${videoPath}`"
                    type="video/mp4"
                  />
                  <source
                    size="1080"
                    :src="`${videoPath}`"
                    type="video/mp4"
                  />
                  <track
                    default
                    kind="captions"
                    label="English captions"
                    src="/path/to/english.vtt"
                    srclang="en"
                  />
            </video>
          </vue-plyr>
        </div>
      </v-col>
      <v-col cols="12" lg="4">
       <footer class="ma-2 pa-2" style="height: 100%; border: 1px solid gray; border-radius: 5px;">
          <v-btn
          color="blue"
          dark
          @click="sheet = !sheet"
        >
          <v-icon>
            mdi-format-list-bulleted-square
          </v-icon>
        </v-btn>
        <hr/>
          <p>강의명 : {{ lectureDetailInfo.title }}</p>
          <p>수강 기한 : 무제한</p>
          <p>난이도: {{ lectureDetailInfo.grade }}</p>

          <v-textarea v-model="videoInfo.description" readonly label="description" filled rounded />

          <div class="text-center">
            <v-bottom-sheet v-model="sheet">
              <v-sheet
                class="text-center"
                rounded
              >
                <v-btn
                  class="mt-6"
                  text
                  color="red"
                  @click="sheet = !sheet"
                >
                  close
                </v-btn>
                <div class="py-3">
                <v-list width="400">
                  <v-list-group
                      v-for="(section, index) in lectureVideoList"
                      :key="section.lectureList_id"
                      v-model="section.active"
                    >
                      <template v-slot:activator>
                        <v-list-item-content>
                          <v-list-item-title>
                            섹션 {{ index + 1 }}. {{ section.section }} 
                          </v-list-item-title>
                        </v-list-item-content>
                        <v-list-item-action>
                          <v-list-item-action-text>
                            {{ section.lectureVideoList.length }} 강 
                          </v-list-item-action-text>
                        </v-list-item-action>
                      </template>

                      <v-list-item
                        v-for="video in section.lectureVideoList"
                        :key="video.id" link @click="getInfo(video.id, lectureDetailInfo.id)"
                      >
                        <v-list-item-icon>
                          <v-icon>mdi-play-circle-outline</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                          <v-list-item-title v-text="video.title"></v-list-item-title>
                        </v-list-item-content>
                        <v-list-item-action>
                          <v-list-item-action-text>
                            {{ calcDuration(video.duration) }}
                          </v-list-item-action-text>
                        </v-list-item-action>
                      </v-list-item>
                    </v-list-group>
                  </v-list>
                </div>
              </v-sheet>
            </v-bottom-sheet>
          </div>
        </footer>
      </v-col>
    </v-row>
    <v-dialog v-model="openStudentComment" width="500">
      <v-card>
        <v-card-title>
          <p>힘이 되는 수강평을 남겨주세요!</p>
        </v-card-title>
        <v-card-actions>
           <v-rating
                v-model="rating"
                :length="length"
                color="yellow"
                background-color="grey lighten-1"
                large/>
        </v-card-actions>
        <v-card-text>
           <v-textarea
            filled
            auto-grow
            placeholder="좋은 수강평을 남겨주시면 지식공유자와 이후 배우는 사람들에게 큰 도움이 됩니다! 포인트도 드려요! (5자 이상)"
            rows="4"
            row-height="30"
            shaped
            v-model="content"
          ></v-textarea>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="cancel" color="warning">취소</v-btn>
          <v-btn @click="regStudentComment(lectureDetailInfo.id)" color="primary">제출하기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
import Vue from 'vue';
export default {
  props: {
    lectureVideoList: {
      type: Array,
      require: true,
    },
    lectureDetailInfo: {
      type: Object,
      require: true
    },
    videoInfo: {
      type: Object,
      require: true
    },
  },
  data() {
    return {
      id: null,
      sheet: false,
      openStudentComment: false,
      rating: 0,
      length: 5,
      content: '',
      token: Vue.$cookies.get("ACCESS_TOKEN")
    };
  },
  components: {},
  created() {
    this.id = this.$route.params.videoId
  },
  computed: {
    videoPath() {
      return `http://www.localhost:7777/lecture/videos/${this.id}/${this.token}`
    }
  },
  methods: {
    calcDuration(value) {
      var minutes = Math.floor(value / 60)
      var seconds = Math.floor((value - minutes * 60))
      return `${minutes}:${seconds}`
    },
    getInfo(videoId, lectureId) {
      this.$router.push(`/lecture/${videoId}/${lectureId}`)
      this.$router.go(this.$router.currentRoute)
    },
    regStudentComment(lectureId) {
      axios.post(`${API_BASE_URL}/manageLecture/regStudentComment`, {
        lectureId: lectureId,
        rating: this.rating,
        content: this.content
      })
      .then(() => {
        alert('수강평 제출을 완료했습니다!')
        this.openStudentComment = !this.openStudentComment
        this.refreshData();
      })
      .catch((err) => {
        console.log(err);
      })
    },
    cancel() {
      this.openStudentComment = !this.openStudentComment
      this.refreshData()
    },
    refreshData() {
      this.rating = 0
      this.content = ''
    }
  }
}
</script>

<style>

</style>