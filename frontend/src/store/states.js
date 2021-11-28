export default {
  members: [],
  member: null,
  email: null,
  name: null,
  introduce: null,
  profiles: null,
  items: [
      { title: 'Home', to: '/home', items:[
          { title: '대시보드', to: '/'},
          { title: '블로그', to: '/'},
          { title: '알림', to: '/'},
          { title: '문의하기', to: '/inqforuser'},
      ]},
      { title: '학습관리', items:[
          { title: '최근학습', to: '/latest'},
          { title: '내 학습', to: '/myLecture'},
          { title: '강의노트', to: '/'},
          { title: '멘토링', to: '/'},
          { title: '작성한 게시글', to: '/myPostList'},
          { title: '수강전 문의', to: '/'},
      ]},
      { title: '수강신청관리', items:[
          { title: '수강바구니', to: '/cart'},
          { title: '위시리스트', to: '/wishlist'},
          { title: '포인트', to: '/my-points'},
          { title: '구매내역', to: '/orders'},
      ]},
      { title: '설정', items:[
          { title: '프로필', to: '/memberProfile'},
          { title: '알림설정', to: '/'},
      ]},
  ],
  pwRules: [
    pw => !!pw || '비밀번호를 입력해주세요.',
    pw => !(pw && pw.length <= 7) || '비밀번호는 8자 이상 입력해주세요.'
  ],
  //게시판
  boards: [],
  board: null,
  comments: [],
  comment: null,
  isLogin: true, //임시,
  isAuth: true, // 임시
  isAuth2: 'true',
  boardNo: null,
  selectMenu: 1,
  selctedComp: 'BoardListPage',
  title: '자유 주제 게시판',
  pageData: null,
  pageNumber: 0,
  //공지사항
  notices: [],
  notice: null,
  //스터디
  studyMembers: [],
  // 판매중인 강의 리스트
  callLecturelist: [],
  callLatestLecture: null,
  tempCate: null,
  //커리큘럼 관리
  sectionList: [],
  lectureVideoList: [],
  
  // 강의 불러오기
  getLectureList: [],
  lectureIndex: null,
  // 페이지 빠져나가면서 dom상태가 destroy정도 쯤에 이 값 비우도록하기    
  //질문답변
  qnaMembers: [],
  nickname: null,
  searchName: null,
  cateName: null,
  orders: [],
  reviewData: [],

  category: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker', 'JavaScript', '게임 개발', 'Golang', '데이터 사이언스', 'Python', '인공지능', '딥러닝', '데이터베이스', 'SQL', 'MongoDB', '보안', '모바일 앱 개발', 'Swift', '안드로이드', 'Kotlin', '코딩테스트', '기타'],  
  category2:[{items: [{ title: '자바', value: 1 }, { title: '개발 프로그래밍', value: 2 }, { title: '프론트엔드', value: 3 }, { title: '백엔드', value: 3 }, { title: 'Vue', value: 4 }, { title: 'React', value: 5 }, { title: 'Html Css', value: 6 }, { title: 'JavaScript', value: 9 }, { title: '게임 개발', value: 10 }, { title: 'Golang', value: 11 }, { title: 'Python', value: 13 }, { title: 'Golang', value: 11 }, { title: 'Golang', value: 11 }], title: '프로그래밍' }, { items: [{ title: '보안', value: 19 }], title: '보안' }, { items: [{ title: '인공지능', value: 14 }, { title: '딥러닝', value: 15 }], title: '머신러닝' }, { items: [{ title: '데이터베이스', value: 16 }, { title: 'SQL', value: 17 }, { title: 'MongoDB', value: 18 }], title: '데이터베이스' }, { items: [{ title: '모바일 앱 개발', value: 20 }, { title: 'Swift', value: 21 }, { title: '안드로이드', value: 22 }, { title: 'Kotlin', value: 23 }], title: '모바일' }, { items: [{ title: '코딩테스트', value: 24 }, { title: '기타', value: 25 }], title: '기타' }],
  cartList: [],
  wishList: []
}
