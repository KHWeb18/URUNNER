<template>
  <div>
    <main-banner></main-banner>
    <main-search-box></main-search-box>
    <main-lecture-list :allLectureList="allLectureList"/>
  </div>  
</template>

<script>
import MainBanner from '@/components/MainBanner.vue';
import MainLectureList from '@/components/MainLectureList.vue'
import MainSearchBox from '@/components/MainSearchBox.vue'
import axios from 'axios';
import {  API_BASE_URL } from '@/constants/index'
export default {
  components: {
    MainBanner,
    MainLectureList,
    MainSearchBox,
  },
  data() {
    return {
      allLectureList: [],
      wish: null,
      cart: null
    }
  },
  created() {
    this.fetchAllLecture();
  },
  methods: {
    fetchAllLecture() {
      axios.get(`${API_BASE_URL}/lecture/getLectureBanner/0`)
            .then(({ data }) => {
               console.log(data)
               this.allLectureList = data.data.content;
            })
    },
  },

}
</script>

<style>

</style>