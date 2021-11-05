<template>
    <div id="board_padding">
        <v-container>
            <study-board-list :boards="boards"/>
        </v-container>
    </div>
</template>

<script>

import StudyBoardList from '@/components/board/study/StudyBoardList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'StudyBoardListPage',
    
    components: {
        StudyBoardList
    },
    computed: {
        ...mapState(['boards'])
    },
    mounted () {
        this.fetchStudyBoardList()
    },
    methods: {
        ...mapActions(['fetchStudyBoardList'])
        
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