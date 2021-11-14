<template>
    <div>
        <v-container>
        <inq-board-modify-form v-if="board" :board="board" @submit="onSubmit"/>
        <p v-else>로딩중 .......</p>
        </v-container>
    </div>
    
</template>

<script>
import InqBoardModifyForm from '@/components/board/inq/InqBoardModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'InqBoardModifyPage',
    components: {
        InqBoardModifyForm
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
        ...mapActions(['fetchInqBoard']),
        onSubmit (payload) {
            const { title, content, complete, currentNum, tags, notice } = payload
            axios.put(`http://localhost:7777/inqboard/${this.boardNo}`, { title, content, complete, currentNum, tags, notice })
                    .then(res => {
                        console.log(res)
                        this.$router.push({
                            name: 'InqBoardReadPage',
                            params: { boardNo: this.boardNo }
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    created () {
        this.fetchInqBoard(this.boardNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
    }
}
</script> 