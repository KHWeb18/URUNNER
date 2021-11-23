<template>
    <div id="board_padding">        
        <v-container class="main">
            <for-sale-lecture-list :callLecturelist="callLecturelist" @callAll="fetchCallLectureList"/>
        </v-container>
    </div>
</template>

<script>

import ForSaleLectureList from '@/components/forSale/lecture/ForSaleLectureList.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'ForSaleLecturePage',
    components: {
        ForSaleLectureList
    },
    computed: {        
        ...mapState(['callLecturelist'])
    },
    mounted () {
        console.log(this.$store.state.searchName)
        this.fetchCallLectureListWithCategory(this.$store.state.cateName, this.$store.state.searchName)
    },
    methods: {
        ...mapActions(['fetchCallLectureListWithCategory','fetchCallLectureList'])
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
.main {
    margin: 0;
}
</style>