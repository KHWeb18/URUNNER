<template>
    <div id="board_padding">        
        <v-container>
            <inq-board-list :boards="boards"/>
        </v-container>
    </div>
</template>

<script>

import InqBoardList from '@/components/board/inq/InqBoardList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'InqBoardListPage',
    components: {
        InqBoardList
    },
    computed: {
        ...mapState(['boards'])
    },
    mounted () {
        this.fetchInqBoardList()
    },
    methods: {
        ...mapActions(['fetchInqBoardList'])
        
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