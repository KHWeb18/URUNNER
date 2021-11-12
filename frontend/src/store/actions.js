import {
    FETCH_MEMBER,
    FETCH_MY_INTRODUCE,
    // 게시판
    FETCH_BOARD_LIST,
    FETCH_BOARD,
    FETCH_COMMENT_LIST,
    // 공지사항
    FETCH_NOTICE_LIST,
    FETCH_NOTICE,
    // 스터디
    FETCH_STUDY_MEMBER_LIST

} from './mutation-types'

import axios from 'axios'

export default {
    fetchMember ({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/memberManagement/${memberNo}`)
                .then((res) => {
                    commit(FETCH_MEMBER, res.data)
                })
    },
    fetchMyIntroduce ({ commit }, userId) {
        return axios.get(`http://localhost:7777/profile/introduce/${userId}`)
                .then((res) => {
                    commit(FETCH_MY_INTRODUCE, res.data)
                    console.log('res데이타는')
                    console.log(res.data)
                    this.$store.state.introduce = res.data
                })
    },
    // 자유게시판
    fetchFreeBoardList ({ commit }) {
        return axios.get('http://localhost:7777/freeboard/lists')
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    fetchFreeBoard ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/freeboard/${boardNo}`)
                .then((res) => {
                    commit(FETCH_BOARD, res.data)
                })
    },
    fetchFreeCommentList ({ commit }, No) {
        console.log('fetchCommentList 작동중 받은 No(stated의 boardNo) 값은 : ' + No)
        return axios.get(`http://localhost:7777/freeboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                    console.log(res.data)
                    console.log(res.data[0][0])
                })

    },
    // 공지사항
    fetchNoticeList ({ commit }) {
        return axios.get('http://localhost:7777/notice/noticeLists')
                .then((res) => {
                    commit(FETCH_NOTICE_LIST, res.data)
                })
    },
    fetchNotice ({ commit }, noticeNo) {
        return axios.get(`http://localhost:7777/notice/${noticeNo}`)
                .then((res) => {
                    commit(FETCH_NOTICE, res.data)
                })
    },    
    // 스터디 게시판
    fetchStudyBoardList ({ commit }) {
        return axios.get('http://localhost:7777/studyboard/lists')
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    fetchStudyBoard ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/studyboard/${boardNo}`)
                .then((res) => {
                    commit(FETCH_BOARD, res.data)
                })
    },
    fetchStudyCommentList ({ commit }, No) {
        console.log('fetchStudyCommentList 작동중 받은 No(stated의 boardNo) 값은 : ' + No)
        return axios.get(`http://localhost:7777/studyboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                })

    },
    fetchStudyMemberList ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/studyboard/memberList/${boardNo}`)
                .then((res) => {
                    console.log('fetchStudyMemberList DATA')
                    console.log(res.data)
                    commit(FETCH_STUDY_MEMBER_LIST, res.data)
                })
    },
    fetchStudyBoardListWithFilter ({ commit }, complete) {
        console.log(complete)
        return axios.get(`http://localhost:7777/studyboard/lists/${complete}`)
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    // 질문답변 게시판
    fetchQnABoardList ({ commit }) {
        return axios.get('http://localhost:7777/qnaboard/lists')
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    fetchQnABoard ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/qnaboard/${boardNo}`)
                .then((res) => {
                    commit(FETCH_BOARD, res.data)
                })
    },
    fetchQnACommentList ({ commit }, No) {
        console.log('fetchQnACommentList 작동중 받은 No(stated의 boardNo) 값은 : ' + No)
        return axios.get(`http://localhost:7777/qnaboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                })

    },
    fetchQnAMemberList ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/qnaboard/memberList/${boardNo}`)
                .then((res) => {
                    console.log('fetchQnAMemberList DATA')
                    console.log(res.data)
                    commit(FETCH_STUDY_MEMBER_LIST, res.data)
                })
    },
    fetchQnABoardListWithFilter ({ commit }, complete) {
        console.log(complete)
        return axios.get(`http://localhost:7777/qnaboard/lists/${complete}`)
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    }
}
