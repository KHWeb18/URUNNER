<template>
    <div id="board_padding">
        <v-progress-circular
        :rotate="-90"
        :size="100"
        :width="15"
        :value="value2"
        color="primary"
        >
        {{ value }} 
        </v-progress-circular>
        <!-- 위는 게시판하고 관련없는 거에요 삭제 예정-->
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
    data () {
      return {
        interval: {},
        value: "1 ／ 5",
        value2: 20
      }
    },
    beforeDestroy () {
      clearInterval(this.interval)
    },
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