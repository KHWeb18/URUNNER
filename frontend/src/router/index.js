import Vue from 'vue';
import VueRouter from 'vue-router';

// 회원 탈퇴
import LeaveMemberPage from '@/views/member/LeaveMemberPage.vue'

Vue.use(VueRouter)

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: () => import('../views/MainPage.vue'),
    },
    {
      path: '/memberRegister',
      component: () => import('../views/MemberRegisterPage.vue')
    },
    // 회원 탈퇴
    {
      path: '/member/:memberNo',
      name: 'LeaveMemberPage',
      components: {
        default: LeaveMemberPage
      },
      props: {
        default: true
      }
    }
  ]
})
