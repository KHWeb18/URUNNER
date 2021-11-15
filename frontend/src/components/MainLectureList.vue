<template>
  <div class="ma-2">
    <h2>따끈따끈, 신규 강의를 만나보세요! 🙋🏻‍♀️ NEW!! </h2>
    <swiper class="swiper" :options="swiperOption">
    <swiper-slide v-for="lecture in allLectureList" :key="lecture.id" class="ma-2">
      <main-lecture-card :lecture="lecture"/>
    </swiper-slide>
    <div class="swiper-pagination" slot="pagination"></div>
  </swiper> 
  </div>
</template>

<script>
  import { Swiper, SwiperSlide } from 'vue-awesome-swiper'
  import 'swiper/css/swiper.css'
  import MainLectureCard from './MainLectureCard.vue'

  export default {
    name: 'swiper-example-responsive-breakpoints',
    title: 'Responsive breakpoints',
    components: {
      Swiper,
      SwiperSlide,
      MainLectureCard
    },
    props: {
      allLectureList: {
        type: Array,
        require: true,
      }
    },
    data() {
      return {
        swiperOption: {
          slidesPerView: 5,
          spaceBetween: 50,
          pagination: {
            el: '.swiper-pagination',
            clickable: true
          },
          breakpoints: {
            1024: {
              slidesPerView: 4,
              spaceBetween: 40
            },
            768: {
              slidesPerView: 3,
              spaceBetween: 30
            },
            640: {
              slidesPerView: 2,
              spaceBetween: 20
            },
            320: {
              slidesPerView: 2,
              spaceBetween: 10
            }
          }
        }
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>