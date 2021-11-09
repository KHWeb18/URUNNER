<template>
    <div id="board_padding">
        <v-container>
            <qna-board-list :boards="boards"/>
        </v-container>
    </div>
</template>

<script>

import QnaBoardList from '@/components/board/qna/QnABoardList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'QnABoardListPage',
    components: {
        QnaBoardList
    },
    computed: {
        ...mapState(['boards'])
    },
    mounted () {
        this.fetchQnABoardList()
    },
    methods: {
        ...mapActions(['fetchQnABoardList'])
        
    }
}

</script>

<style scoped>
.board_title {
    font-size: 32px;
    font-weight: 700;
    line-height: 32px;
    color: #333d4b;
    margin-bottom: 60px;
    margin-top: 70px;
}
.v-progress-circular {
  margin: 1rem;
}
</style>