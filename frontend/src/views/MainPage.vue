<template>
  <div>
    <main-banner></main-banner>
    <main-search-box></main-search-box>
    <main-lecture-list></main-lecture-list>
  </div>  
</template>

<script>
import MainBanner from '@/components/MainBanner.vue';
import MainLectureList from '@/components/MainLectureList.vue'
import MainSearchBox from '@/components/MainSearchBox.vue'
export default {
  components: {
    MainBanner,
    MainLectureList,
    MainSearchBox,
  }

}
</script>

<style>

</style>