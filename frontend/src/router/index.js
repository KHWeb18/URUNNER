import Vue from 'vue';
import VueRouter from 'vue-router';

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
    }
  ]
})
