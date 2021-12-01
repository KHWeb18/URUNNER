<template>
  <div>
    <main-banner></main-banner>
    <main-search-box></main-search-box>
    <main-lecture-list :allLectureList="allLectureList" :allReivewLectureList="allReivewLectureList" @fetchMore="fetchMore"/>
    <main-study-list :boards="boards"/>
    <main-review-list :commentList="commentList" :lectureCount="lectureCount"></main-review-list>
  </div>  
</template>

<script>
import MainBanner from '@/components/MainBanner.vue';
import MainLectureList from '@/components/MainLectureList.vue'
import MainSearchBox from '@/components/MainSearchBox.vue'
import MainStudyList from '@/components/MainStudyList.vue'
import MainReviewList from '@/components/common/MainReviewList.vue'
import axios from 'axios';
import { API_BASE_URL } from '@/constants/index'
import { mapState, mapActions } from 'vuex'
import EventBus from '@/event'

export default {
  components: {
    MainBanner,
    MainLectureList,
    MainSearchBox,
    MainStudyList,
    MainReviewList,
  },
  data() {
    return {
      allLectureList: [],
      allReivewLectureList: [],
      commentList: [],
      wish: null,
      cart: null,
      currentPage: 0,
      lectureCount: 0,

    }
  },
  computed: {
      ...mapState(['boards']),
  },
  created() {
    this.fetchAllLecture();
    this.fetchStudyBoardList()
  },
  mounted () {
  },
  methods: {
    fetchAllLecture() {
      axios.get(`${API_BASE_URL}/lecture/getLectureBanner/${this.currentPage}`)
            .then(({ data }) => {
               console.log(data)
               this.allReivewLectureList = data.reviewData.content;
               this.allLectureList = data.data.content;
               this.commentList = data.comment;
               this.lectureCount = data.lectureCount
            })
    },
    fetchMore() {
      this.currentPage = this.currentPage + 1
        axios.get(`${API_BASE_URL}/lecture/getLectureBanner/${this.currentPage}`)
              .then(({ data }) => {
                console.log(data)
                  this.allLectureList = [...this.allLectureList, ...data.data.content];
                  this.allReivewLectureList = [...this.allReivewLectureList, ...data.reviewData.content];
              })
              .then(() => {
                EventBus.$emit('loadMore')
              })
    },
    fetchLatestLecture(){
      axios.get(`${API_BASE_URL}/myPage/my-latest-lecture/`)
      .then(({ data }) => {
        console.log("현재최근강의"+ data)
        this.latestLecture = data
        console.log("최근강의")
        console.log(data)
      })
    }
    ,
    ...mapActions(['fetchStudyBoardList']),
  },

}
</script>

<style>

</style>