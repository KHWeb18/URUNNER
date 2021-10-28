import {
    FETCH_MEMBER,
    FETCH_MY_INTRODUCE

} from './mutation-types'

export default {
    [FETCH_MEMBER] (state, member) {
        state.member = member
    },
    [FETCH_MY_INTRODUCE] (state, introduce) {
        state.introduce = introduce
    }
}