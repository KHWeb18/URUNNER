<template>
    <div id="board_padding">        
        <v-container>
            <free-board-list :boards="boards"/>
        </v-container>
    </div>
</template>

<script>

import FreeBoardList from '@/components/board/free/FreeBoardList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'FreeBoardListPage',
    components: {
        FreeBoardList
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