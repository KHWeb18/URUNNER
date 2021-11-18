export default {
  members: [],
  member: null,
  email: null,
  name: null,
  introduce: null,
  items: [
      { title: 'Home', to: '/home', items:[
          { title: '대시보드', to: '/'},
          { title: '블로그', to: '/'},
          { title: '알림', to: '/'},
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
          { title: '프로필', to: '/'},
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
  //내학습
  myLecturelist: [ { "lecture_id": 4, "title": "[리뉴얼] 렛츠기릿 자바스크립트", "introduce": "본 강의에서는 자바스크립트를 활용해 프로그래밍 사고력을 기르는 연습을 합니다. 웹 게임인 구구단을 시작으로 끝말잇기, 숫자 야구, 반응 속도 테스트, 틱택토, 로또 추첨기, 가위바위보, 카드 짝맞추기 게임, 텍스트 RPG, 지뢰 찾기, 2048게임, 두더지 잡기까지 함께 만들어봅시다.", "writer": "start132@naver.com", "nickname": "조헌영", "grade": "초급자", "currentNum": 1, "views": 8, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "true", "regDate": "2021-11-13T14:17:51.686+00:00", "upDate": "2021-11-13T14:17:51.686+00:00" }, { "boardNo": 1, "title": "ㅎㅇㅋㅋ", "content": "<p>ㅎㅇㅋㅋ</p>", "writer": "start132@naver.com", "nickname": "야옹이", "complete": "false", "currentNum": 1, "views": 1, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "true", "regDate": "2021-11-13T14:17:38.461+00:00", "upDate": "2021-11-13T14:17:38.461+00:00" }, { "boardNo": 9, "title": "test", "content": "<p>testtesttest</p>", "writer": "", "nickname": "야옹이", "complete": "false", "currentNum": 1, "views": 8, "comments": 2, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "false", "regDate": "2021-11-14T14:37:58.944+00:00", "upDate": "2021-11-14T14:37:58.944+00:00" }, { "boardNo": 8, "title": "test", "content": "<p>test</p>", "writer": "", "nickname": "야옹이", "complete": "false", "currentNum": 1, "views": 1, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"},{\"text\":\"Vue\",\"color\":\"red\"},{\"text\":\"Spring\",\"color\":\"green\"}]", "notice": "false", "regDate": "2021-11-14T14:35:54.089+00:00", "upDate": "2021-11-14T14:35:54.089+00:00" }, { "boardNo": 7, "title": "ㅎㅇㅋㅋ", "content": "<p>ㅎㅇㅎㅇㅎㅇ</p>", "writer": "", "nickname": "야옹이", "complete": "false", "currentNum": 1, "views": 3, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "false", "regDate": "2021-11-14T14:34:01.981+00:00", "upDate": "2021-11-14T14:34:01.981+00:00" }, { "boardNo": 6, "title": "글입니다", "content": "<p>글입니다</p>", "writer": "start132@naver.com", "nickname": "야옹이", "complete": "true", "currentNum": 1, "views": 65, "comments": 3, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"},{\"text\":\"Vue\",\"color\":\"red\"},{\"text\":\"Spring\",\"color\":\"green\"},{\"text\":\"Python\",\"color\":\"purple\"},{\"text\":\"SQL\",\"color\":\"indigo\"}]", "notice": "false", "regDate": "2021-11-13T14:19:00.122+00:00", "upDate": "2021-11-13T14:19:00.122+00:00" }, { "boardNo": 5, "title": "dd", "content": "<p>dddz</p>", "writer": "start132@naver.com", "nickname": "야옹이", "complete": "false", "currentNum": 1, "views": 4, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "false", "regDate": "2021-11-13T14:18:46.357+00:00", "upDate": "2021-11-13T14:18:46.357+00:00" }, { "boardNo": 3, "title": "ㅎㅇ", "content": "<p>ㅎㅇ</p>", "writer": "start132@naver.com", "nickname": "야옹이", "complete": "true", "currentNum": 1, "views": 29, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "false", "regDate": "2021-11-13T14:17:45.629+00:00", "upDate": "2021-11-13T14:17:45.629+00:00" }, { "boardNo": 2, "title": "ㅎㅇ", "content": "<p>ㅎㅇ</p>", "writer": "start132@naver.com", "nickname": "야옹이", "complete": "true", "currentNum": 1, "views": 5, "comments": 0, "tags": "[{\"text\":\"Java\",\"color\":\"blue\"}]", "notice": "false", "regDate": "2021-11-13T14:17:42.581+00:00", "upDate": "2021-11-13T14:17:42.581+00:00" } ],
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
  memberLists: [],
}
