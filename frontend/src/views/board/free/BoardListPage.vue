<template>
    <div id="board_padding">
        <v-container>
            <board-list :boards="boards"/>
        </v-container>
        <router-link :to="{ name: 'StudyBoardListPage' }">
        <v-btn>스터디 게시판</v-btn>
        </router-link>
        <router-link :to="{ name: 'QnABoardListPage' }">
        <v-btn>질문 답변 게시판</v-btn>
        </router-link>
    </div>
</template>

<script>

import BoardList from '@/components/board/free/BoardList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'BoardListPage',
    components: {
        BoardList
    },
    computed: {
        ...mapState(['boards'])
    },
    mounted () {
        this.fetchFreeBoardList()
    },
    methods: {
        ...mapActions(['fetchFreeBoardList'])
        
    }
}

</script>