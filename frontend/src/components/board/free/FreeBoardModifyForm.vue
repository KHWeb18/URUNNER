<template>
    <div @submit.prevent="onSubmit">
        <div class="main_box">
            <div class="title_box">
                <h4 class="page_title">
                    <span>자유 게시판</span></h4>
            </div>
            <div class="post_list">
                <div class="post_card_box">
                    <div class="searching_message_box">
                        <div class="searching_message">
                            <div class="post_tag">
                                <div v-for="tag in classifyTag(board.tags)" :key="tag">
                                        <btn class="tag_box_button">#{{ tag.text }}&nbsp;</btn>
                                </div>
                                <div v-show="board.tags != '#'" class="post_tag_either">/&nbsp;</div>
                                <div class="post_tag_either">{{board.nickname}} / {{ $moment(board.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 제목 -->
            <v-text-field label="제목" v-model="title"></v-text-field>
            <!-- 게시글 -->
            <editor :board="board" @fromEditor="onSubmit"/>
        </div>
    </div>
</template>

<script>

import Editor from '@/components/board/Editor.vue'

export default {
    name: 'FreeBoardModifyForm',
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
            content: this.board.content,
            complete: this.board.complete,
            currentNum: this.board.currentNum,
            notice: this.board.notice
        }
    },
    methods: {
        onSubmit (data) {
            this.content = data.content
            this.tags = data.tags
            this.notice = data.notice            
            console.log('modifyForm 단계의 notice 값 : ' + this.notice)
            const { title, content, complete, currentNum, tags, notice } = this
            this.$emit('submit', { title, content, complete, currentNum, tags, notice })
        },
        ImgRequest() {
            try {
                return require(`../../../../../backend/khweb/images/free/${this.board.writer}_${this.board.boardNo}.gif`
                )
            } catch (e) {
                return require(`@/assets/logo.png`)
            }
        },
        classifyTag(data) {
            var arr = JSON.parse(data)
            console.log(arr)
            return arr
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
    display: flex;
    justify-content: start;
    align-content: center;
    color: #0288D1;
    font-weight: bold;
    font-size: 16px !important;    
    letter-spacing: 0px !important;
    margin-bottom: 20px;
}
.post_tag_either {
    display: flex;
    justify-self: center;
    align-self: center;
    font-size: 15px !important;
    color: #757575;
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