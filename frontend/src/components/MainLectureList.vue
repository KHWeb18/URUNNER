<template>
  <div class="ma-2">
    <swiper class="swiper" :options="swiperOption">
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
    </swiper-slide>
    <swiper-slide>
      <main-lecture-card/>
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