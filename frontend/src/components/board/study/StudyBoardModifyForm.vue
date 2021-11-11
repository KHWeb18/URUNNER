<template>
    <div @submit.prevent="onSubmit">
        <div class="main_box">
            <div class="title_box">
                <h4 class="page_title">
                    <v-icon>mdi-exclamation-thick</v-icon>
                    <span>자유게시판</span></h4>
            </div>
            <div class="post_list">
                <div class="post_card_box">
                    <div class="searching_message_box">
                        <div class="searching_message">
                            <div><p><b class="post_tag">#사료추천</b> / {{board.nickname}} / {{ $moment(board.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</p></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 제목 -->
            <v-text-field label="제목" v-model="title"></v-text-field>
            <!-- 인원설정 -->
            <input type="number" v-model="fit">
            <!-- 마감 -->
            {{ complete }}
            <!-- 게시글 -->
            <editor :content="content" @content="onSubmit"/>
            <!-- 이미지 -->
            <div class="content_img">
                <img :src="ImgRequest()" class="test">
            </div>

        </div>
        <div class="button_box">
            <router-link :to="{ name: 'StudyBoardListPage' }">
                <v-btn>
                    취소
                </v-btn>
            </router-link>
            <v-btn color="light-blue lighten-1 text center" @click="onSubmit" class="item">
                등록
            </v-btn>
            <v-btn @click="test()">테스트</v-btn>
            {{board}}
        </div>
    </div>
</template>

<script>

import Editor from '@/components/board/Editor.vue'

export default {
    name: 'StudyBoardModifyForm',
    components: {
        Editor
    },
    props: {
        board: {
            type: Object,
            required: true
        }
    },
    data () {
        return {
            title: '',
            content: '',
            fit: this.board.fit,
            complete: this.board.complete,
            currentNum: this.board.currentNum
        }
    },
    methods: {
        onSubmit (data) {
            this.content = data
            const { title, content, fit, complete, currentNum } = this
            this.$emit('submit', { title, content, fit, complete, currentNum })
        },
        ImgRequest() {
            try {
                return require(`../../../../../backend/khweb/images/free/${this.board.writer}_${this.board.boardNo}.gif`
                )
            } catch (e) {
                return require(`@/assets/logo.png`)
            }
        }
    },
    created () {
        this.title = this.board.title
        this.content = this.board.content
    }
}
</script> 

<style scoped>
.item {
    font: 12pt;
    color: white;
    font-weight: 800;
}
.item_list {
    margin-top: 10px;
    display: flex;
}
.button_box {    
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;
}
.v-btn {
    margin-right: 10px;
}
.post_list {
    width:70vw;
    max-width: 1000px;
}
.main_box {
    color: #424242;
}
.title_box span {
    font-size: 25px;
    font-weight: bold;
}
.post_card:hover {
    transform: scale(1.005);
    box-shadow: 10px 17px 40px 0 rgb(0 0 0 / 4%);
    cursor: pointer;
    transition: all 0.1s ease;
}
.post_card {
    display: flex;
    justify-content: row;
    margin: 15px;
    padding: 20 20 5 5;
    height: 150px;
    border-bottom: 1px solid #BDBDBD;
}
.content_img {
    text-align: center;
    width: 70vw;
    max-width: 1000px;
}
.post_box {
    margin: 10px 10px 20px 5px;
    display: flex;
    flex-direction: column;
    height: 100px;
    width: 500px;
}
.post_tag {
    color: #0288D1;
    font-weight: bold;
    font-size: 16px !important;    
    letter-spacing: 0px !important;
}
.post_title {
    margin: 0 0 0 0px;
    font-size: 17px;
    font-weight: bold;
    color: #424242;
}
.post_title:hover {
    color: #29B6F6;
    text-decoration: underline;
}
.post_content {
    font-size: 15px;
    color: #757575;
    padding-bottom: 0px;
    height: 500px;
    width: 50vw;
}
.post_reg_date {
    font-size: 13px;
    color: #757575;
}
.btn_pagination {
    background-color: transparent;
    box-shadow: none;
}
.button_box {
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;
}
.change-font {
    font: 12pt;
    color: white;
    font-weight: 800;
}
.v-input__slot::before {
  border-style: none !important;
}
::placeholder {
    font-size: 13px;
    letter-spacing: 0;
    color: #757575d0;    
}
.post_image {
    margin: 50px;
    width: 40vw;
}
.test {
    width: 50vw;
    max-width: 60vw;
    margin: 80px 0 30px 0;
}
.v-application a {
    color: #757575 !important;
    font-weight: bold;
}
.middle_btn_box {
    margin-top: 10px;
}
.item {
    cursor: pointer;
}
</style>