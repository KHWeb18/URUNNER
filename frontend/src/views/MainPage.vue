<template>
  <div>
    <main-banner></main-banner>
    <main-search-box></main-search-box>
    <main-lecture-list :allLectureList="allLectureList"/>
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
      cart: null
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
      axios.get(`${API_BASE_URL}/lecture/getLectureBanner/0`)
            .then(({ data }) => {
               console.log(data)
               this.allLectureList = data.data.content;
            })
    },
    ...mapActions(['fetchStudyBoardList'])
  },

}
</script>

<style>

</style>