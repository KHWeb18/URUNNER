<template>
    <div>
        <v-container>
        <free-board-modify-form v-if="board" :board="board" @submit="onSubmit"/>
        <p v-else>로딩중 .......</p>
        </v-container>
    </div>
    
</template>

<script>
import FreeBoardModifyForm from '@/components/board/free/FreeBoardModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'FreeBoardModifyPage',
    components: {
        FreeBoardModifyForm
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
        ...mapActions(['fetchFreeBoard']),
        onSubmit (payload) {
            const { title, content, complete, currentNum, tags, notice } = payload
            axios.put(`http://localhost:7777/freeboard/${this.boardNo}`, { title, content, complete, currentNum, tags, notice })
                    .then(res => {
                        console.log(res)
                        this.$router.push({
                            name: 'FreeBoardReadPage',
                            params: { boardNo: this.boardNo }
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    created () {
        this.fetchFreeBoard(this.boardNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
    }
}
</script> 