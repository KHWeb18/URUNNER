export default {
  members: [],
  member: null,
  email: null,
  name: null,//name하고
  introduce: null,//introduce도 로그인 순간 여기에 저장되도록 해주세요(10/26)
  items: [
      { title: 'Home', to: '/home', items:[
          { title: '대시보드', to: '/'},
          { title: '블로그', to: '/'},
          { title: '알림', to: '/'},
        ]},
        { title: '학습관리', items:[
          { title: '내 학습', to: '/'},
          { title: '강의노트', to: '/'},
          { title: '멘토링', to: '/'},
          { title: '작성한 게시글', to: '/'},
          { title: '수강전 문의', to: '/'},
        ]},
        { title: '수강신청관리', items:[
          { title: '수강바구니', to: '/cart'},
          { title: '위시리스트', to: '/wishlist'},
          { title: '쿠폰함', to: '/'},
          { title: '포인트', to: '/'},
          { title: '구매내역', to: '/'},
        ]},
        { title: '설정', items:[
          { title: '프로필', to: '/'},
          { title: '알림설정', to: '/'},
        ]},
      ],
      pwRules: [
        pw => !!pw || '비밀번호를 입력해주세요.',
        pw => !(pw && pw.length <= 7) || '비밀번호는 8자 이상 입력해주세요.'
    ]
}