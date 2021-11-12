<template>
    <div>
        <v-container>
        <qna-board-modify-form v-if="board" :board="board" @submit="onSubmit"/>
        <p v-else>로딩중 .......</p>
        </v-container>
    </div>
    
</template>

<script>
import QnaBoardModifyForm from '@/components/board/qna/QnABoardModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'QnABoardModifyPage',
    components: {
        QnaBoardModifyForm
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
        ...mapActions(['fetchQnABoard']),
        onSubmit (payload) {
            const { title, content, complete, currentNum, tags } = payload
            axios.put(`http://localhost:7777/qnaboard/${this.boardNo}`, { title, content, complete, currentNum, tags })
                    .then(res => {
                        console.log(res)
                        this.$router.push({
                            name: 'QnABoardReadPage',
                            params: { boardNo: this.boardNo }
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    created () {
        this.fetchQnABoard(this.boardNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
    }
}
</script> 