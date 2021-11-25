<template>
  <div>
    <main-banner></main-banner>
    <main-search-box></main-search-box>
    <main-lecture-list :allLectureList="allLectureList" @fetchMore="fetchMore"/>
    <main-study-list :boards="boards"/>
  </div>  
</template>

<script>
import MainBanner from '@/components/MainBanner.vue';
import MainLectureList from '@/components/MainLectureList.vue'
import MainSearchBox from '@/components/MainSearchBox.vue'
import MainStudyList from '@/components/MainStudyList.vue'
import axios from 'axios';
import {  API_BASE_URL } from '@/constants/index'
import { mapState, mapActions } from 'vuex'
import EventBus from '@/event'
export default {
  components: {
    MainBanner,
    MainLectureList,
    MainSearchBox,
    MainStudyList,
  },
  data() {
    return {
      allLectureList: [],
      wish: null,
      cart: null,
      currentPage: 0
    }
  },
  computed: {
      ...mapState(['boards'])
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
               this.allLectureList = data.data.content;
            })
    },
    fetchMore() {
      this.currentPage = this.currentPage + 1
        axios.get(`${API_BASE_URL}/lecture/getLectureBanner/${this.currentPage}`)
              .then(({ data }) => {
                console.log(data)
                  this.allLectureList = [...this.allLectureList, ...data.data.content];
              })
              .then(() => {
                EventBus.$emit('loadMore')
              })
    },
    ...mapActions(['fetchStudyBoardList'])
  },

}
</script>

<style>

</style>