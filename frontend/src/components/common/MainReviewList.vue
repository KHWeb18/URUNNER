<template>
  <div class="ma-2 wrape">
    <v-row>
      <v-col cols="12" lg="5">
        <div>
          <h1 class="text-h5 text-lg-h3 font-weight-bold mt-2 mb-6">{{ lectureCount }}개의 강의가<span class="primary--text">유러너</span>와 함께합니다!🌱</h1>
          <p class="text-h6">유러너는 강의의 수강생수, 평점을 투명하게 공개합니다.
            실제로 많은 온오프라인 학원들은 단기적 성과를 높이기 위해 잘나온 특정 후기만 노출 하거나, 아예 숨겨버리는 경우가 많습니다.
            반면 인프런은 강의에 대한 후기, 학생수 등 정보를 투명하게 공개합니다. 신뢰성을 바탕으로 학습자들이 더 좋은 컨텐츠를 선택하고 교육의 질을 높입니다.
          </p>
        </div>
      </v-col>
      <v-col cols="12" lg="7" class="box">
          <div v-for="comment in commentList" :key="comment.content">
            <review-card :comment="comment"></review-card>
          </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import ReviewCard from '@/components/common/ReviewCard'

export default {
  components: {
    ReviewCard
  },
  props: {
    commentList: {
      type: Array,
      required: true
    },
    lectureCount: {
      type: Number,
      required: true
    },
  },
data() {
      return {
      }
    }
  }
</script>

<style scoped>
  .wrape{
    height: 500px;
  }
  .box{ 
    height: 500px;
    overflow-y: scroll;
    cursor: pointer;
    -ms-overflow-style: none; 
  } 
  .box::-webkit-scrollbar{ 
    display:none; 
  }

</style>