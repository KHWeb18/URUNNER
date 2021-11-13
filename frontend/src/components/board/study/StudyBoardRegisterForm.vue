<template>
    <form @submit.prevent="boardRegist">
        <v-text-field label="제목" v-model="title" maxlength="45"></v-text-field>
        <v-text-field label="인원설정" placeholder="모집하는 인원수를 정해주세요" type="number" v-model="fit"></v-text-field>
        <editor placeholder="Write something …" @fromEditor="boardRegist"/>
    </form>    
</template>

<script>


import axios from 'axios'
import Editor from '@/components/board/Editor.vue'


export default {
    name: 'StudyBoardRegisterForm',
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
            fit: 1,
            currentNum: 1,
            views: 1,
            comments: 0,
            qna: '',
            tags: '',
            notice: false
        }
    },
    methods: {
        catchContent(data) {
            this.content = data
        },       
        boardRegist (data) {
            this.content = data.content
            this.tags = data.tags
            this.notice = data.notice //여기까지 에디터 data
            const { title, writer, content, nickname, complete, fit, currentNum, views, comments, tags, notice } = this
            axios.post('http://localhost:7777/studyboard/register', { title, writer, content, nickname, complete, fit, currentNum, views, comments, tags, notice } )
                    .then(res => {
                    console.log(res.data)
                    this.$store.state.boardNo = res.data.boardNo.toString()
                    this.$router.push({
                        name: 'StudyBoardListPage'
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