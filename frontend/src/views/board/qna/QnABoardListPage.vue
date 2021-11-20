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
</style>