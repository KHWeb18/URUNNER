<template>
  <swiper class="swiper" :options="swiperOption">
    <swiper-slide v-for="bannerInfo in banners" :key="bannerInfo.title">
      <main-banner-form :bannerInfo="bannerInfo"></main-banner-form>
    </swiper-slide>
    <div class="swiper-pagination" slot="pagination"></div>
    <div class="swiper-button-prev" slot="button-prev"></div>
    <div class="swiper-button-next" slot="button-next"></div>
  </swiper>
</template>

<script>
  import { Swiper, SwiperSlide } from 'vue-awesome-swiper'
  import 'swiper/css/swiper.css'
  import MainBannerForm from '@/components/common/MainBannerForm.vue'

  export default {
    title: 'Fraction pagination',
    components: {
      Swiper,
      SwiperSlide,
      MainBannerForm
    },
    data() {
      return {
        swiperOption: {
          pagination: {
            el: '.swiper-pagination',
            type: 'fraction'
          },
          autoplay: {
            delay: 5000,
            disableOnInteraction: false
          },
          navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
          }
        },
        banners: [ 
                    { color : '#FBFBF3', img: require('@/assets/banner1.gif'), 
                     badge: { fontColor: 'white--text', color: 'error', text: 'sale' }, title: '지금 할인중인 강의 💸', 
                     desc: '지금 할인중인 강의를 찾아보세요. 당장 오늘 마감이 될 수도 있어요.' },
                    { color : '#F8CEB5', img: require('@/assets/banner2.png'), 
                     badge: { fontColor: 'white--text', color: 'red', text: '특별할인 10%' }, title: '독서의 계절, 가을엔 강의 한 편...🍁', 
                     desc: '붙잡고 싶은 가을, 유러너와 함께해요!' },
                    { color : '#ddff68', img: require('@/assets/banner3.gif'), 
                     badge: { fontColor: 'white--text', color: '#62f745', text: 'new' }, title: '신규 강의가 도착했어요 🌱', 
                     desc: '11월! 새로 업데이트된 신규 강의를 만나보세요.'},
                    { color : '#086394', img: require('@/assets/banner4.gif'), 
                     badge: { fontColor: 'black--text', color: 'white', text: '지식공유신청' }, title: '나누는 지식만큼 커지는 보람과 보상', 
                     desc: '소중한 지식을 공유하고 더 나은 가치를 창조해보세요', fontColor: 'color: white;' },
                    { color : '#5520f0', img: require('@/assets/banner5.png'), 
                     badge: { fontColor: 'black--text', color: 'yellow', text: 'best' }, title: '유러너에서 꾸준히 사랑받는 강의들', 
                     desc: '유러너의 베스트셀러도 만나고 할인까지 받아보세요 🐷',  fontColor: 'color: white;' },
                 ]
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
