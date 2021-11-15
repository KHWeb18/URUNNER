<template>
  <div>
    <v-row>
      <v-col cols="12">
        <v-banner
          color="#002744" elevation="5">
          <div class="d-md-flex align-center">
            <div class="white--text">
              <p class="pa-2 mb-7 text-h5 text-md-h4 font-weight-bold">{{ lectureDetailInfo.title }}</p>
              <span class="pa-1"><v-rating :value="4" dense color="yellow" small class="d-inline"/>(4.8) 24명의 수강평 | 30명의 수강생</span>
              <div class="mt-4">
                <v-icon color="white">mdi-shield-account-outline</v-icon> <span>{{ lectureDetailInfo.writer }}</span>
              </div>
              <div class="d-flex mb-3">
                <v-icon color="white">mdi-music-accidental-sharp</v-icon>
                <v-chip-group>
                  <v-chip v-for="category in lectureDetailInfo.category" :key="category.category_id"
                  :ripple="false" dark>
                    {{ category.categoryName }}
                  </v-chip>
                </v-chip-group>
              </div>
            </div>
            <div class="order-first mx-10" align="center">
              <v-img max-width="450" style="border-radius: 10px;"
              :src="`http://localhost:7777/lecture/image/${lectureDetailInfo.thumbPath}/${lectureDetailInfo.writer}`"></v-img>
            </div>
          </div>
        </v-banner>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="8" >
        <v-container>
          <div>
            <h4 class="text-sm-h5 font-weight-bold mb-2">강의 소개</h4>
            <div style="border: 0.4rem solid #2296f3; border-radius:10px;">
              <p class="text-sm-h7 pa-3">{{ lectureDetailInfo.desc }}</p>
            </div>
          </div>
          <div align="center" class="my-7">
            <v-img max-width="450" style="border-radius: 10px;"
              :src="`http://localhost:7777/lecture/image/${lectureDetailInfo.detailPath}/${lectureDetailInfo.writer}`"></v-img>
          </div>
          <div v-html="lectureDetailInfo.content" class="my-5"></div>
          <div>
            <h4 class="text-sm-h5 font-weight-bold mb-2">커리큘럼</h4>
            <v-card class="mx-auto">
              <v-list>
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
                    :key="video.id" link
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
            </v-card>
          </div>
        </v-container>
      </v-col>
      <v-col sm="4" class="hidden-sm-and-down">
        <v-container>
          <v-card class="mx-auto" shaped>
            <v-card-title>
              <h2 class="secondary--text">
                {{ getCurrencyFormat(lectureDetailInfo.price) }}원
              </h2>
            </v-card-title>
            <v-card-actions class="pa-3">
              <v-btn class="primary" x-large block>수강하기</v-btn>
            </v-card-actions>
            <v-card-subtitle>
              <div class="d-flex justify-space-around text-h7 mb-3">
                <span><v-icon>mdi-cards-heart-outline</v-icon> 326</span> |
                <span><v-icon>mdi-share-variant-outline</v-icon> 공유</span>
              </div>
            </v-card-subtitle>
            <v-alert
              border="left"
              colored-border
              color="info"
            >
            <div class="text-h7 secondary--text">
              <p>+ 지식공유자 : {{ lectureDetailInfo.writer }}</p>
              <p>+ 평생 무제한 수강</p>
              <p>+ 수료증 발급 강의</p>
              <p>+ <span :class="grade == '입문' ? 'font-weight-bold' : ''">입문</span> 〉 <span :class="grade == '초급' ? 'font-weight-bold' : ''">초급</span> 〉 <span :class="grade == '중급 이상' ? 'font-weight-bold' : ''">중급 이상</span></p>
            </div>
            </v-alert>
          </v-card>
          <v-alert type="info">
            <div class="d-flex justify-space-between align-center">
              <h4>수강 전 궁굼한 점이 있나요?</h4>
              <v-btn icon><v-icon>mdi-chevron-right</v-icon></v-btn>
            </div>
          </v-alert>
        </v-container>
      </v-col>
    </v-row>
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
    }
  },
  computed: {
    grade() {
      return this.lectureDetailInfo.grade
    }
  },
  methods: {
    calcDuration(value) {
      var minutes = Math.floor(value / 60)
      var seconds = Math.floor((value - minutes * 60))
      return `${minutes}:${seconds}`
    },
     getCurrencyFormat(value) {
      return this.$currencyFormat(value);
    },
  }

}
</script>

<style>

</style>