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


import axios from "axios";


// axios.defaults.headers.common['Authorization'] = Vue.$cookies.get("ACCESS_TOKEN");

// Add a request interceptor

// 요청전 전처리
// 주의할점 두번 요청할경우 문제발생
axios.interceptors.request.use(function (config) {
    // Do something before request is sent

    // if(!Vue.$cookies.get("ACCESS_TOKEN")) {
    //   return Promise.reject("No access token set")
    // }
    if(!Vue.$cookies.get("ACCESS_TOKEN")) {
      config.headers.Authorization = Vue.$cookies.get("REFRESH_TOKEN");
    }else{
      config.headers.Authorization = Vue.$cookies.get("ACCESS_TOKEN");
    }
    alert("요청")

    return config;
  }, function (error) {
    // Do something with request error

    return Promise.reject(error);
  });
 
// Add a response interceptor

// 응답받기전 전처리
axios.interceptors.response.use(function (response) {

    // Do something with response data
    return response;
  }, function (error) {

  alert(error.response.data.error)

  // let getRefresh = error.response.data.error

  // if (getRefresh.includes("The Token has expired")){
  //     refreshToken()
  // }



    // alert(res.response.data.error)

    // Do something with response error
    return Promise.reject(error);
  });



// //   https://www.npmjs.com/package/axios