<template>
    <form @submit.prevent="onSubmit">
        <v-text-field label="제목" v-model="title" maxlength="50"></v-text-field>
        <editor placeholder="Write something …" @content="boardRegist"/>
        <!-- 이미지 등록 폼 -->
        <div style="margin-bottom: 10px">
            <div class="image-box">
                <label>이미지 추가
                    <input
                        type="file"
                        class="files"
                        id="files"
                        ref="files"
                        multiple="multiple"
                        v-on:change="handleFileUpload()"
                        style="pointer:cursor"></label>
                    <button @click="fileDeleteButton()" class="image_btn">이미지 삭제</button>
            </div>
            <div class="preview_image">
                <img :src="preview"></div>
        </div>
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
            name: this.$store.state.moduleA.name,
            content: '',
            complete: false,
            currentNum: 1,
            views: 1,
            comments: 0
        }
    },
    methods: {
        catchContent(data) {
            this.content = data
        },
        test() {
            console.log(this.name)
            console.log(this.$store.state.moduleA.email)
            console.log(this.content)
        },
        handleFileUpload () {
                this.files = this.$refs.files.files
                this.preview = URL.createObjectURL(this.files[0])
                console.log(this.files[0])
        },
        onsubmit () {
            let formData = new FormData()
            for (var idx = 0; idx < this.files.length; idx++) {
                formData.append('fileList', this.files[idx])
                console.log(this.files[idx])
            }
            
            let ownerId = this.$store.state.moduleA.email
            formData.append('id', ownerId)
            let no = this.$store.state.boardNo
            formData.append('no', no)
            axios.post('http://localhost:7777/image/uploadImg_QnA', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }                
            })
            .then (res => {
                this.response = res.data
            })
            .catch (res => {
                this.response = res.message
            }) 
            alert('Processing Complete!')
            this.$router.push({
                            name: 'QnABoardListPage'
                        })
        },
        fileDeleteButton () {
            this.files = '',
            this.preview = ''
        },       
        boardRegist (data) {            
            this.content = data
            const { title, writer, content, name, complete, currentNum, views, comments } = this
            console.log("const값좀보자")
            console.log("const값좀보자")
            console.log({ title, writer, content, name, complete, currentNum, views, comments })
            axios.post('http://localhost:7777/qnaboard/register', { title, writer, content, name, complete, currentNum, views, comments } )
                    .then(res => {
                        console.log(res.data)
                        this.$store.state.boardNo = res.data.boardNo.toString()
                        console.log(this.$store.state.boardNo)
                        this.onsubmit()
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