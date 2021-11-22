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
          { title: '내 학습', to: '/myLecture'},
          { title: '강의노트', to: '/'},
          { title: '멘토링', to: '/'},
          { title: '작성한 게시글', to: '/myPostList'},
          { title: '수강전 문의', to: '/'},
      ]},
      { title: '수강신청관리', items:[
          { title: '수강바구니', to: '/cart'},
          { title: '위시리스트', to: '/wishlist'},
          { title: '쿠폰함', to: '/coupons'},
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
  //공지사항
  notices: [],
  notice: null,
  //스터디
  studyMembers: [],
  // 판매중인 강의 리스트
  callLecturelist: [],
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

  category: ['개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker', 'JavaScript', '게임 개발', 'Golang', '데이터 사이언스', 'Python', '인공지능', '딥러닝', '데이터베이스', 'SQL', 'MongoDB', '보안', '모바일 앱 개발', 'Swift', '안드로이드', 'Kotlin', '코딩테스트', '기타'],  
}
