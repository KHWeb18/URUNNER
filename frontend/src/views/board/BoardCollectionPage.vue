<template>
    <div>
        <!-- 제목 -->
        <v-container class="main">
            <div class="mr-9 hidden-sm-and-down">
                <div class="title_box">
                    <h2 class="page_title">
                        <h3>{{ isAuth }}</h3>
                        <span>{{ title }}</span></h2>
                </div>
            </div>
            <div class="mr-9 hidden-md-and-up">
                <div class="title_box2">
                </div>
            </div>
            <!-- 게시판 분류 -->
            <v-spacer class="forLine0 mr-9 hidden-sm-and-down">
                <div class="forLine0sButton">
                    <div class="tag_button" :class="{ on2 : selectMenu == 1 }" @click="changeComp('BoardListPage')">자유 게시판</div>&nbsp;&nbsp;&nbsp;
                    <div class="tag_button" :class="{ on2 : selectMenu == 2 }"  @click="changeComp('QnABoardListPage')">질문 게시판</div>&nbsp;&nbsp;&nbsp;
                    <div class="tag_button" :class="{ on2 : selectMenu == 3 }" @click="changeComp('StudyBoardListPage')">스터디 게시판</div>&nbsp;&nbsp;&nbsp;
                </div>
            </v-spacer>
            <!-- 게시판 분류 for mobile-->
            <v-spacer class="forLine0 mr-9 hidden-md-and-up">
                <div class="forLine0sButton">
                    <div class="tag_button" :class="{ on2 : selectMenu == 1 }" @click="changeComp('BoardListPage')">자유</div>&nbsp;&nbsp;&nbsp;
                    <div class="tag_button" :class="{ on2 : selectMenu == 2 }"  @click="changeComp('QnABoardListPage')">질문</div>&nbsp;&nbsp;&nbsp;
                    <div class="tag_button" :class="{ on2 : selectMenu == 3 }" @click="changeComp('StudyBoardListPage')">스터디</div>&nbsp;&nbsp;&nbsp;
                </div>
            </v-spacer>
        </v-container>
        <v-spacer class="forLine001 hidden-md-and-up"></v-spacer>
        <v-spacer class="forLine00 hidden-sm-and-down"></v-spacer>
        <div class="tab-item">
            <component v-bind:is="selctedComp" @boardNo="saveBoardNo"/>
        </div>
        <!-- 모바일 사이즈 때 나타나는 글쓰기 버튼 -->
        <router-link :to="{ name: selectRegister() }">
            <v-btn v-if="isAdmin()" fab dark color="primary" class="hidden-md-and-up" fixed right style="top:80vh;">
                <v-icon dark>mdi-plus</v-icon>
            </v-btn>
        </router-link>
    </div>
</template>

<script>

import BoardListPage from '@/views/board/free/BoardListPage.vue'
import QnABoardListPage from '@/views/board/qna/QnABoardListPage.vue'
import StudyBoardListPage from '@/views/board/study/StudyBoardListPage.vue'

export default {
    name: 'BoardCollectionPage',
    components: {
        BoardListPage,
        QnABoardListPage,
        StudyBoardListPage
    },
    data () {
        return {
            selctedComp: this.$store.state.selctedComp,
            completeSelect1: '',
            completeSelect2: '',
            completeSelect3: '',
            selectMenu: this.$store.state.selectMenu,
            title: this.$store.state.title
        }
    },
    methods : {
        changeComp(data) {
            this.$store.state.selctedComp = data
            this.selctedComp = data

            switch(data) {
                case 'BoardListPage':
                    this.selectMenu = 1;
                    this.$store.state.selectMenu = 1;
                    this.$store.state.title = '자유 주제 게시판'
                    this.title = '자유 주제 게시판'
                    break;
                case 'QnABoardListPage':
                    this.selectMenu = 2;
                    this.$store.state.selectMenu = 2;
                    this.$store.state.title = '질문&답변 게시판'
                    this.title = '질문&답변 게시판'
                    break;
                case 'StudyBoardListPage':
                    this.selectMenu = 3;
                    this.$store.state.selectMenu = 3;
                    this.$store.state.title = '스터디 모집 게시판'                    
                    this.title = '스터디 모집 게시판'
                    break;
                default:
                    break;
            }
        },
        selectRegister() {
            var select = this.selctedComp

            switch(select) {
                case 'BoardListPage':
                    this.selectMenu = 1;
                    this.$store.state.selectMenu = 1;
                    return 'FreeBoardRegisterPage'
                case 'QnABoardListPage':
                    this.selectMenu = 2;
                    this.$store.state.selectMenu = 2;
                    return 'QnABoardRegisterPage'
                case 'StudyBoardListPage':
                    this.selectMenu = 3;
                    this.$store.state.selectMenu = 3;
                    return 'StudyBoardRegisterPage'
                default:
                    break;
            }
        },
        isAdmin() {
            if(this.$cookies.get('ROLES') == null) {
                return false
            } else {
                return true
            }
        },
    }
}
</script>


<style scoped>
.main {
    padding: 15px 20px 0px 20px;
}
.title_box {
    color: #424242;
}
.title_box2 {
    margin-top: 10px;
}
.title_box span {
    font-size: 55px;
    font-weight: bold;
}
.forLine0 {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 40px;
    padding: 0vw 2vw;
    margin: 10px 0vw 0 0vw;
}
.forLine0sButton {
    display: flex;
    justify-content: start;
    align-items: center;
}
.forLine00 {
    margin: 0 5vw 40px 5vw;
    border-bottom: 1px solid #BDBDBD;
}
.forLine001 {
    margin: 0px;
    border-bottom: 1px solid #BDBDBD;
}
.tag_button {
    color: #BDBDBD;
    cursor: pointer;
}
.tag_button.on {
    color: black;
}
.tag_button.on2 {
    color: #F57F17;
    font-weight: bold;
    transition: all 0.3s ease;
}
.tag_button:hover {
    color: rgb(63, 63, 63);
    cursor: pointer;
    transition: all 0.5s ease;
}
</style>