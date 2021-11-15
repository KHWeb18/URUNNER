<template>
    <div id="board_padding">        
        <v-container>
            <inq-board-for-user-list :boards="boards"/>
        </v-container>
    </div>
</template>

<script>

import InqBoardForUserList from '@/components/board/inq/InqBoardForUserList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'InqBoardListForUserPage',
    components: {
        InqBoardForUserList
    },
    computed: {
        ...mapState(['boards'])
    },
    mounted () {
        this.fetchInqBoardForUserList(this.$store.state.moduleA.email)
    },
    methods: {
        ...mapActions(['fetchInqBoardForUserList'])
        
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