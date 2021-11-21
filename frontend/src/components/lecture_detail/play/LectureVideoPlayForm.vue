<template style="height: 100vh">
  <div style="">
    <div>
      <header class="my-2">
       <h2 class="text-h7 text-md-h4 font-weight-bold pa-3">Topic : {{ videoInfo.title }}</h2>
      </header>

        <vue-plyr ref="plyr">
        <video
              controls
              playsinline
              @timeupdate="videoTimeUpdated"
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
    
      <footer class="ma-2 pa-2" style="border: 1px solid gray; border-radius: 5px;">
        <v-btn
         icon
         color="primary"
        @click="sheet = !sheet"
       >
        <v-icon>
          mdi-format-list-bulleted-square
        </v-icon>
       </v-btn>
        <p>강의명 : {{ lectureDetailInfo.title }}</p>
        <p>수강 기한 : 무제한</p>
        <p>진도율 : 0강/21강 (0%) | 시간 : 0분/212분</p>
        <div width="400">
          <v-progress-linear value="15"></v-progress-linear>
        </div>

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
    </div>
  </div>
</template>

<script>
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
      duration: null,
      player: null,
      id: null,
      sheet: false,
      // mdi-format-list-bulleted-square
      // mdi-message-processing
    };
  },
  components: {},
  created() {
    this.id = this.$route.params.videoId
  },
  mounted() {
    this.player = this.$refs.plyr.player
  },
  computed: {
    videoPath() {
      return `http://www.localhost:7777/lecture/videos/${this.id}`
    }
  },
  methods: {
    videoTimeUpdated: function() {
      this.duration = this.player.currentTime
    },
    calcDuration(value) {
      var minutes = Math.floor(value / 60)
      var seconds = Math.floor((value - minutes * 60))
      return `${minutes}:${seconds}`
    },
    getInfo(videoId, lectureId) {
      this.$router.push(`/lecture/${videoId}/${lectureId}`)
      this.$emit('getInfo', videoId)
      this.id = videoId
    }
  }
};
</script>

<style>

</style>