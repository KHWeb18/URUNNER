<template>
    <v-container>
        <inq-board-list :boards="boards"/>
    </v-container>
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