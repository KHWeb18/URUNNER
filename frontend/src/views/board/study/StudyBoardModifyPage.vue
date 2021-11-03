<template>
    <div>
        <v-container>
        <study-board-modify-form v-if="board" :board="board" @submit="onSubmit"/>
        <p v-else>로딩중 .......</p>
        </v-container>
    </div>
    
</template>

<script>
import StudyBoardModifyForm from '@/components/board/study/StudyBoardModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'StudyBoardModifyPage',
    components: {
        StudyBoardModifyForm
    },
    props: {
        boardNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['board'])
    },
    methods: {
        ...mapActions(['fetchStudyBoard']),
        onSubmit (payload) {
            const { title, content } = payload
            axios.put(`http://localhost:7777/studyboard/${this.boardNo}`, { title, content })
                    .then(res => {
                        console.log(res)
                        this.$router.push({
                            name: 'StudyBoardReadPage',
                            params: { boardNo: this.boardNo }
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    created () {
        this.fetchStudyBoard(this.boardNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
    }
}
</script> 