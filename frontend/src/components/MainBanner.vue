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
                    { color : '#498FDD', img: require('@/assets/banner6.gif'), 
                     badge: { fontColor: 'black--text', color: 'yellow', text: 'best' }, title: '유러너에서 꾸준히 사랑받는 강의들', 
                     desc: '유러너의 베스트셀러도 만나고 할인까지 받아보세요 🐷',  fontColor: 'color: white;' },
                    { color : '#5D538B', img: require('@/assets/banner7.gif'), 
                     badge: { fontColor: 'white--text', color: 'black', text: '홍보이벤트' }, title: '유러너 오픈 기념! 공유하면 당첨확률 100%🎁', 
                     desc: '유러너 오픈 기념으로 친구에게 공유 시 100% 당첨 확률로 경품을 드려요',  fontColor: 'color: white;' },
                    { color : '#0C100C', img: require('@/assets/banner9.gif'), 
                     badge: { fontColor: 'white--black', color: 'white', text: '보안특전' }, title: '한주간만 진행하는 정보보안 특전!', 
                     desc: '정보보안 관련 강의를 추가 할인된 가격으로 만나보세요🤩',  fontColor: 'color: white;' },
                    { color : '#FBB206', img: require('@/assets/banner10.gif'), 
                     badge: { fontColor: 'white--text', color: '#007400', text: '프론트엔드' }, title: '프론트엔드의 핵심! 반응형 CSS 강의들❣️', 
                     desc: '데스크탑 사이징에 따라 변하는 반응형 웹사이트를 만들어보세요',  fontColor: 'color: black;' },
                    { color : '#0D47A0', img: require('@/assets/banner11.gif'), 
                     badge: { fontColor: 'white--black', color: '#e0e0e0', text: '첫구매쿠폰' }, title: '유러너 첫 구매 웰컴 혜택!', 
                     desc: '가입 후 강의를 처음 구매 하시면 30%할인 쿠폰을 드려요',  fontColor: 'color: white;' },
                 ]
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
