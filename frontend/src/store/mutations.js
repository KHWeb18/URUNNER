import {
    FETCH_MEMBER,
    FETCH_MY_INTRODUCE,
    // 게시판
    FETCH_BOARD_LIST,
    FETCH_BOARD,
    FETCH_COMMENT_LIST,
    FETCH_COMMENT,
    // 공지사항
    FETCH_NOTICE_LIST,
    FETCH_NOTICE,
    // 스터디
    FETCH_STUDY_MEMBER_LIST,
    // 판매중인 강의 리스트
    FETCH_LECTURE_LIST,
    // 회원 관리
    FETCH_MEMBER_LIST,

} from './mutation-types'

export default {
    [FETCH_MEMBER] (state, member) {
        state.member = member
    },
    [FETCH_MY_INTRODUCE] (state, introduce) {
        state.introduce = introduce
    },
    // 게시판
    [FETCH_BOARD_LIST] (state, boards) {
        state.boards = boards;
    },
    [FETCH_BOARD] (state, board) {
        state.board = board
    },
    [FETCH_COMMENT_LIST] (state, comments) {
        state.comments = comments;
    },
    [FETCH_COMMENT] (state, comment) {
        state.comment = comment
    },
    // 공지사항
    [FETCH_NOTICE_LIST] (state, notices) {
        state.notices = notices;
    },
    [FETCH_NOTICE] (state, notice) {
        state.notice = notice
    },
    // 스터디
    [FETCH_STUDY_MEMBER_LIST] (state, studyMembers) {
        state.studyMembers = studyMembers
    },
    // 판매중인 강의 리스트
    [FETCH_LECTURE_LIST] (state, callLecturelist) {
        state.callLecturelist = callLecturelist
    },
    // 커리큘럼 관리
    saveSectionList(state, data) {
        state.sectionList = data;
    },
    saveVideoList(state, data) {
        state.lectureVideoList = data;
    },
    [FETCH_MEMBER_LIST] (state,memberLists) {
        state.memberLists = memberLists
    },

}