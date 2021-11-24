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
    FETCH_STUDY_MEMBER_LIST,
    // 강의 리스트
    FETCH_LECTURE_LIST,
    // 회원 관리
    FETCH_MEMBER_LIST,

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
    // 자유 게시판
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
        return axios.get(`http://localhost:7777/freeboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                })

    },
    fetchFreeBoardListWithFilter ({ commit }, complete) {
        return axios.get(`http://localhost:7777/freeboard/lists/${complete}`)
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
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
        return axios.get(`http://localhost:7777/studyboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                })

    },
    fetchStudyMemberList ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/studyboard/memberList/${boardNo}`)
                .then((res) => {
                    commit(FETCH_STUDY_MEMBER_LIST, res.data)
                })
    },
    fetchStudyBoardListWithFilter ({ commit }, complete) {
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
        return axios.get(`http://localhost:7777/qnaboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                })

    },
    fetchQnAMemberList ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/qnaboard/memberList/${boardNo}`)
                .then((res) => {
                    commit(FETCH_STUDY_MEMBER_LIST, res.data)
                })
    },
    fetchQnABoardListWithFilter ({ commit }, complete) {
        return axios.get(`http://localhost:7777/qnaboard/lists/${complete}`)
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    // 1:1 문의 게시판
    fetchInqBoardList ({ commit }) {
        return axios.get('http://localhost:7777/inqboard/lists')
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    fetchInqBoardForUserList ({ commit }, userId) {
        // var userId = 'start132@naver.com'
        return axios.get(`http://localhost:7777/inqboard/lists/1/${userId}`)
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    fetchInqBoard ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/inqboard/${boardNo}`)
                .then((res) => {
                    commit(FETCH_BOARD, res.data)
                })
    },
    fetchInqCommentList ({ commit }, No) {
        return axios.get(`http://localhost:7777/inqboard/comment/${No}`)
                .then((res) => {
                    commit(FETCH_COMMENT_LIST, res.data)
                })

    },
    fetchInqMemberList ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/inqboard/memberList/${boardNo}`)
                .then((res) => {
                    commit(FETCH_STUDY_MEMBER_LIST, res.data)
                })
    },
    fetchInqBoardListWithFilter ({ commit }, complete) {
        return axios.get(`http://localhost:7777/inqboard/lists/${complete}`)
                .then((res) => {
                    const reverse = [...res.data].reverse();
                    commit(FETCH_BOARD_LIST, reverse)
                })
    },
    // 내학습 게시판
    fetchMyLectureList ({ commit }, memberNo) {
        return axios.get(`http://localhost:7777/myPage/myLecturelist/${memberNo}`)
                .then((res) => {
                    commit(FETCH_LECTURE_LIST, res.data)
                    console.log(res.data)
        }).catch(err=>{alert(err.response.data.message)})
    },
    fetchMemberList({ commit }) {
        return axios.get('http://localhost:7777/memberManagement/memberList')
        .then( (res) => {
            commit(FETCH_MEMBER_LIST, res.data)
        }).catch(err=>{alert(err.response.data.message)})
    },
    // 판매중인 수강 리스트
    // fetchCallLectureList({ commit }) {
    //     return axios.get('http://localhost:7777/callLecture/lists')
    //     .then( (res) => {
    //         commit(FETCH_LECTURE_LIST, res.data)
    //     }).catch(err=>{alert(err.response.data.message)})
    // },
    fetchCallLectureList({ commit }) {
        let categoryName = new Object();
        categoryName['category'] = null
        return axios.post('http://localhost:7777/manageLecture/mainSearch', { page : 0, name: null, categoryName})
        .then( (res) => {
            commit(FETCH_LECTURE_LIST, res.data.data.content)
            console.log(res)
        }).catch(err=>{alert(err.response.data.message)})
    },
    fetchCallLectureListWithCategory({ commit, state }, cateName) {
        let categoryName = new Object();
        categoryName['category'] = cateName
        console.log("cateName : " + cateName)
        return axios.post('http://localhost:7777/manageLecture/mainSearch', { page : 0, name: state.searchName, categoryName})
        .then( (res) => {
            commit(FETCH_LECTURE_LIST, res.data.data.content)
            state.reviewData = res.data.reviewData
            console.log(state.reviewData)
            console.log(res.data.data.content)
        }).catch(err=>{alert(err.response.data.message)})
    },
    fetchCallLectureListWithFilter ({ commit }, word) {
        let categoryName = new Object();
        categoryName['category'] = null
        return axios.post(`http://localhost:7777/manageLecture/mainSearch`, { page : 0, name: word, categoryName})
                .then((res) => {
                    commit(FETCH_LECTURE_LIST, res.data.data.content)
                    console.log(res.data.data.content)
        }).catch(err=>{alert(err.response.data.message)})
    }
}
