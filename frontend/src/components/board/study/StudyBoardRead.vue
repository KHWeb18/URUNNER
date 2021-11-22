<template>
    <div>
        <div class="main_box">
            <!-- 게시글 -->
            <div class="post_list">
                <div class="post_card_box">
                    <div class="searching_message_box">
                        <div class="searching_message">
                            <div style="margin-top:20px;"><b>{{board.title}}</b></div>
                            <div class="post_tag">
                                <div v-for="tag in classifyTag(board.tags)" :key="tag">
                                        <btn class="tag_box_button">#{{ tag.text }}&nbsp;</btn>
                                </div>
                                <div v-show="board.tags != '#'" class="post_tag_either"></div>
                                <div class="post_tag_either"><h v-show="board.notice =='false'">&nbsp;/&nbsp;{{board.nickname}}&nbsp;/&nbsp;</h>
                                {{ $moment(board.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="post_content">
                    <div v-html="board.content">{{ board.content }}</div>
                </div>
                <div class="complete_btn_align">
                    <v-btn v-show="board.complete !== 'true'" @click="appl(board.boardNo)" style="margin-right:10px">지원하기</v-btn>
                    <v-btn v-show="writer = board.writer" @click="endRecruit(board.boardNo)">모집 마감</v-btn>
                </div>
                <!-- 지원자 목록 -->
                <div class="member_list">
                    <div>
                        <v-subheader>지원자 목록</v-subheader>
                            <v-expansion-panels popout>
                                <v-expansion-panel
                                v-for="(member, i) in this.$store.state.studyMembers"
                                :key="i" hide-actions>
                                <v-expansion-panel-header>
                                    <v-row align="center" class="spacer" no-gutters>
                                        <v-col class="hidden-xs-only" sm="5" md="3">
                                            <strong v-html="member.nickname"></strong>
                                        </v-col>
                                    </v-row>
                                </v-expansion-panel-header>
                                <v-expansion-panel-content>
                                    <v-divider></v-divider>
                                    <v-card-text v-text="member.introduce"></v-card-text>
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                        </v-expansion-panels>
                    </div>
                    <br>
                </div>
            </div>            
        </div>
    </div>
</template>

<script>

import axios from 'axios'
import Vue from 'vue'

export default {
    name: 'StudyBoardRead',
    data () {
        return {
            writer: Vue.$cookies.get("USER_NAME"),
            nickname: '',
            email: '',
            introduce: 'HELLO WORLD!',
            refresh: 1,
            members: this.$store.state.studyMembers,
            complete: false
        }
    },
    props: {
        board: {
            type: Object,
            required: true
        }
    },
    methods : {
        appl(data) {
            this.nickname = this.$store.state.moduleA.nickname
            this.email = this.$store.state.moduleA.email
            const { nickname, email, introduce } = this
            axios.put(`http://localhost:7777/studyboard/apply/${data}`, { nickname, email, introduce })
                    .then(res => {
                        console.log(res)
                        this.refresh += 1
                        const refresh = this.refresh
                        this.$emit('submit', refresh)
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })

        },
        endRecruit(data) {
            console.log('endRecruit에서 날아온 data(boardNo)값은 : ' + data)
            if(this.board.complete) {
                this.board.complete = false
            } else {
                this.board.complete = true
            }
            const { title, content, fit, complete, currentNum, notice} = this.board
            axios.put(`http://localhost:7777/studyboard/${data}`, { title, content, fit, complete, currentNum, notice })
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
        },
        classifyTag(data) {
            var arr = JSON.parse(data)
            console.log(arr)
            return arr
        }
    }
}
</script> 

<style scoped>
.post_list {
    display: flex;
    justify-content: center;
    flex-direction: column;
    width:95vw;
    max-width: 1000px;
    margin: 0px;
}
.main_box {
    color: #424242;
}
.title_box {   
}
.title_box span {
    font-size: 25px;
    font-weight: bold;
}
.page_title {
}
.option_box {
    display: flex;
    justify-content: flex-end;
    width: 70vw;    
    max-width: 1000px;
}
.searching_box {    
    height: 50px;
}
.searching_bar {
    display: flex;
    justify-content: row;
    height: 40px;
    width:70vw;
    max-width: 1000px;
    border: 1px solid #BDBDBD;
}
.searching {
    height: 38px !important; 
    width:60vw !important;
    max-width: 955px;
    border-style: none !important;
}
.searching_message_box {
    width:95vw;
    max-width: 1000px;
    display:flex;
    justify-content: center;
}
.searching_message {
    display: flex;
    justify-content: center;
    flex-direction: column;
    width:100vw;
    max-width: 900px;
    border-top: 1px solid #BDBDBD;
    border-bottom: 1px solid #BDBDBD;
    margin-top: 50px;
}
.searching_message div {
    text-align: center;
    font-colr: #333333;
    font-size: 20px;
}
.searching_message b {
    letter-spacing: 2px;
    font-size: 26px;
}
.searching_message p {    
    font-size: 13px;
    color: #757575;

}




.post_align {
    display: flex;
    justify-content: center;
    flex-direction: column;
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
.post_card_box {

}
.thumbnail {
    margin-right: 20px;
    height: 140px !important; 
    width: 140px !important; 
}
.thumbnail_img {
    width: 100%;
    height: 100%;
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
    justify-content: center;
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
    margin: 10vw 3vw 0vw 3vw;
    width: 60vw;
    font-size: 15px;
    color: #757575;
    padding-bottom: 10px;
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
    max-width: 50vw;
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
a { text-decoration:none !important }
a:hover { text-decoration:none !important }

.complete_btn_align {
    display: flex;
    justify-content: center;
}
.tag_box_button {
    display: flex;
    justify-content: center;
    align-content: center;
    font-size: 18px;
}
.member_list {
    display: flex;
    justify-content: center;
    align-self: center;
    width: 300px;
    margin: 30px;
}
.v-item-group.theme--light.v-expansion-panels.v-expansion-panels--popout {
    width: 300px;
}
.v-subheader.theme--light {
    display: flex;
    justify-content: center;        
}
</style>
