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
            component: () => import ('../views/MemberProfilePage.vue')
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
        }, {
            path: '/mypage',
            component: () => import ("../views/mypage/Mypage.vue"),
            children: [
                {
                    path: '/wishlist',
                    component: () => import ("../components/enrolment/WishList.vue")
                }, {
                    path: '/cart',
                    component: () => import ("../components/enrolment/Cart.vue")
                }
            ]
        }
    ]
})

