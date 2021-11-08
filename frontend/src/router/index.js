import Vue from 'vue';
import VueRouter from 'vue-router';

// 회원 탈퇴
import LeaveMemberPage from '@/views/member/LeaveMemberPage.vue'

//마이 페이지
import MyPageStatus from '@/views/mypage/MyPageStatus.vue'

// 게시판
import FreeBoardListPage from '@/views/board/free/BoardListPage.vue'
import FreeBoardRegisterPage from '@/views/board/free/BoardRegisterPage.vue'
import FreeBoardReadPage from '@/views/board/free/BoardReadPage.vue'
import FreeBoardModifyPage from '@/views/board/free/BoardModifyPage.vue'

// 공지 게시판
import NoticeRegisterPage from '@/views/notice/NoticeRegisterPage.vue'
import NoticeListPage from '@/views/notice/NoticeListPage.vue'
import NoticeReadPage from '@/views/notice/NoticeReadPage.vue'
import NoticeModifyPage from '@/views/notice/NoticeModifyPage.vue'

// 스터디 게시판
import StudyBoardRegisterPage from '@/views/board/study/StudyBoardRegisterPage.vue'
import StudyBoardListPage from '@/views/board/study/StudyBoardListPage.vue'
import StudyBoardReadPage from '@/views/board/study/StudyBoardReadPage.vue'
import StudyBoardModifyPage from '@/views/board/study/StudyBoardModifyPage.vue'

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
       // 강의 관리 페이지 (강의 등록 시스템)
       {
        path: '/mypage/lecture/lectureList',
        name: 'lectureListPage',
        component: () => import ('../views/lecture/LectureListPage.vue')
      },
      {
          path: '/mypage/lecture/registerLecture',
          name: 'registerLecture',
          component: () => import ('../views/lecture/RegisterLecturePage.vue')
      },
      {
        path: '/mypage/lecture/registerLectureImage/:lectureId',
        name: 'registerLectureImage',
        component: () => import ('../views/lecture/LectureImageRegisterPage.vue')
      },
      {
        path: '/mypage/lecture/manageLecture/:lectureId',
        name: 'ManageLecturePage',
        component: () => import ('../views/lecture/ManageLecturePage.vue'),
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
      },
      {
        path: '/freeBoard',
        name: 'FreeBoardListPage',
        components: {
          default: FreeBoardListPage
        }
      },
      {
        path: '/freeBoard/create',
        name: 'FreeBoardRegisterPage',
        components: {
          default: FreeBoardRegisterPage
        }
      },
      {
        path: '/freeBoard/:boardNo',
        name: 'FreeBoardReadPage',
        components: {
          default: FreeBoardReadPage
        },
        props: {
          default: true
        }
      },
      {
        path: '/freeBoard/:boardNo/edit',
        name: 'FreeBoardModifyPage',
        components: {
          default: FreeBoardModifyPage
        },
        props: {
          default: true
        }
      },
      // 공지 게시판
      {
        path: '/notice/create',
        name: 'NoticeRegisterPage',
        components: {
          default: NoticeRegisterPage
        }
      },
      {
        path: '/notice',
        name: 'NoticeListPage',
        components: {
          default: NoticeListPage
        }
      },
      {
        path: '/notice/:noticeNo',
        name: 'NoticeReadPage',
        components: {
          default: NoticeReadPage
        },
        props: {
          default: true
        }
      },
      {
        path: '/notice/:noticeNo/edit',
        name: 'NoticeModifyPage',
        components: {
          default: NoticeModifyPage
        },
        props: {
          default: true
        }
      },
      // 스터디 게시판
      {
        path: '/study/create',
        name: 'StudyBoardRegisterPage',
        components: {
          default: StudyBoardRegisterPage
        }
      },
      {
        path: '/study',
        name: 'StudyBoardListPage',
        components: {
          default: StudyBoardListPage
        }
      },
      {
        path: '/study/:boardNo',
        name: 'StudyBoardReadPage',
        components: {
          default: StudyBoardReadPage
        },
        props: {
          default: true
        }
      },
      {
        path: '/study/:boardNo/edit',
        name: 'StudyBoardModifyPage',
        components: {
          default: StudyBoardModifyPage
        },
        props: {
          default: true
        }
      },
      {
        path: '/*',
        component: () => import( "../util/Oauth")
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
