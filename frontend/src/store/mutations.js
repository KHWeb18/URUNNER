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
    }
}