<template>
    <form @submit.prevent="boardRegist">
        <v-text-field label="제목" v-model="title" maxlength="45"></v-text-field>
        <editor placeholder="Write something …" @fromEditor="boardRegist"/>
    </form>    
</template>

<script>

import axios from 'axios'
import Editor from '@/components/board/Editor.vue'

export default {
    name: 'QnABoardRegisterForm',
    components: {
        Editor
    },
    data () {
        return {
            //초기값 세팅
            title: '',
            writer: this.$store.state.moduleA.email,
            files: '',
            preview: '',
            nickname: this.$store.state.moduleA.nickname,
            content: '',
            complete: false,
            currentNum: 1,
            views: 1,
            comments: 0,
            tags: '',
        }
    },
    methods: {
        catchContent(data) {
            this.content = data
        },      
        boardRegist (data) {
            this.content = data.content
            this.tags = data.tags
            const { title, writer, content, nickname, complete, currentNum, views, comments, tags } = this
            console.log("const값좀보자")
            console.log("const값좀보자")
            console.log({ title, writer, content, nickname, complete, currentNum, views, comments })
            axios.post('http://localhost:7777/qnaboard/register', { title, writer, content, nickname, complete, currentNum, views, comments, tags } )
                    .then(res => {
                        console.log(res.data)
                        this.$store.state.boardNo = res.data.boardNo.toString()
                        this.$router.push({
                            name: 'QnABoardListPage'
                        })
                    })
                    .catch(res => {
                        alert(res.response.data.message)
                    })
        }
    }
}
</script> 

<style scoped>
.item {
    font: 12pt;
    color: white;
    font-weight: 800;
}
.button_box {    
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;
}
.v-btn {
    margin-right: 10px;
}
.preview_image img {
    width: 300px;
}
a { text-decoration:none !important }
a:hover { text-decoration:none !important }
</style>