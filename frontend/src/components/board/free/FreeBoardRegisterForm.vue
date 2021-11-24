<template>
    <form @submit.prevent="boardRegist">
        <v-text-field label="제목" v-model="title" maxlength="45"></v-text-field>
        <editor placeholder="Write something …" @fromEditor="boardRegist"/>        
    </form>    
</template>

<script>

import axios from 'axios'
import Editor from '@/components/board/Editor.vue'
import Vue from 'vue'

export default {
    name: 'FreeBoardRegisterForm',
    components: {
        Editor
    },
    data () {
        return {
            //초기값 세팅
            title: '',
            writer: Vue.$cookies.get("USER_NAME"),
            files: '',
            preview: '',
            nickname: Vue.$cookies.get("NICKNAME"),
            content: '',
            complete: false,
            currentNum: 1,
            views: 1,
            comments: 0,
            tags: '',
            notice: false,
        }
    },
    methods: {      
        boardRegist (data) {
            this.content = data.content
            this.tags = data.tags
            this.notice = data.notice //여기까지 에디터 data
            const { title, writer, content, nickname, complete, currentNum, views, comments, tags, notice } = this
            axios.post('http://localhost:7777/freeboard/register', { title, writer, content, nickname, complete, currentNum, views, comments, tags, notice } )
                    .then(res => {
                        this.$store.state.boardNo = res.data.boardNo.toString()
                        this.$router.push({
                            name: 'BoardCollectionPage'
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