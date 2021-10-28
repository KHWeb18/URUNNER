import Vue from 'vue';
import VueRouter from 'vue-router';

// 회원 탈퇴
import LeaveMemberPage from '@/views/member/LeaveMemberPage.vue'

//마이 페이지
import MyPageStatus from '@/views/mypage/MyPageStatus.vue'

Vue.use(VueRouter)

export default new VueRouter({
  mode: 'history',
  routes: [
      {
          path: '/',
          name: 'MainPage',
          component: () => import ('../views/MainPage.vue')
      }, {
          path: '/memberRegister',
          component: () => import ('../views/MemberRegisterPage.vue')
      }, {
          path: '/memberProfile',
          name: 'memberProfile',
          component: () => import ('../views/MemberProfilePage.vue')
      },
      // 회원 탈퇴
      {
          path: '/leave-member',
          name: 'LeaveMemberPage',
          components: {
              default: LeaveMemberPage
          },
          props: {
              default: true
          }
      },
      //myPage
      {
          path: '/my-page-status',
          name: 'MyPageStatus',
          components: {
              default: MyPageStatus
          },
          props: {
              default: true
          }
      }, 
      {
          path: '/mypage',
          component: () => import ("../views/mypage/Mypage.vue"),
          children: [
              {
                  path: '/wishlist',
                  component: () => import ("../components/enrolment/WishList.vue")
              }, {
                path: '/forgot',
                component: () => import ("../views/ForgotPasswordPage.vue")

              }, {
                path: '/changepw',
                component: () => import ("../components/ChangePasswordForm.vue")

            },
            {
              path: '/coupons',
              component: () => import("../components/enrolment/Coupon.vue")
            },
            {
              path: '/my-points',
              component: () => import("../components/enrolment/Mypoints.vue")
            }
          ]
      },
      {
          path: '/cart',
          component: () => import ("../components/enrolment/Cart.vue")
      },
      {
        path: '/orders',
        component: () => import("../components/enrolment/Orders.vue")
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