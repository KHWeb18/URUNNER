<template>
  <div>
    <main-banner></main-banner>
    <main-search-box></main-search-box>
    <main-lecture-list :allLectureList="allLectureList"></main-lecture-list>
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
    }
  },
  created() {
    this.fetchAllLecture();
  },
  methods: {
    fetchAllLecture() {
      axios.get(`${API_BASE_URL}/lecture/getAllLecture`)
            .then(({ data }) => {
               console.log(JSON.stringify(data))
               this.allLectureList = data;
            })
    }
  },

}
</script>

<style>

</style>