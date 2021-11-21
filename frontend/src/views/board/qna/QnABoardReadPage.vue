<template>
    <div>
        <v-container>
            <qna-board-read v-if="board" :board="board" @submit="onSubmit"/>
            <p v-else>로딩중 ......</p>
            <v-container class="middle_btn_box">
                <router-link :to="{ name: 'BoardCollectionPage' }">
                        목록
                </router-link>                
                <b v-show="board.writer == this.$store.state.moduleA.email || this.$store.state.isAuth">
                    <router-link :to="{ name: 'QnABoardModifyPage', params: { boardNo } }">
                        |수정
                    </router-link>
                </b>                
                <b v-show="board.writer == this.$store.state.moduleA.email || this.$store.state.isAuth" @click="snackbar = true" class="item">
                    |삭제
                </b>
                 <!-- 게시물 삭제 클릭시 알림창 -->
                <div class="text-center">            
                    <v-snackbar v-model="snackbar"
                    :timeout="-1" centered outlined>
                    게시물을 삭제하시겠습니까?
                        <template v-slot:action="{ attrs }">
                            <v-btn color="#424242" text v-bind="attrs" @click="[onDeletePost(), snackbar = false]">
                            확인</v-btn>
                            <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
                            취소</v-btn>
                        </template>
                    </v-snackbar>
                </div>
            </v-container>
            <qna-comment-list v-if="comments" :comments="comments" @submit="onSubmit"/>
            <p v-else>로딩중 ......</p>            
        </v-container>        
    </div>
</template>

<script>
import QnaBoardRead from '@/components/board/qna/QnABoardRead.vue'
import QnaCommentList from '@/components/board/qna/QnACommentList.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'QnABoardReadPage',
    props: {
        boardNo: {
            type: String,
            required: true
        },
        boardNumber: null
    },
    data () {
        return {
            refreshCheck: 1,
            snackbar: false
        }
    },
    components: {
        QnaBoardRead,
        QnaCommentList
    },
    watch: {
        refreshCheck(newVal) {
            if(newVal >= 0) {
                console.log('refreshCheck값은 : ' + this.refreshCheck)
                console.log('데이터 변동 감지')
                this.fetchQnACommentList(this.boardNo)
                this.fetchQnABoard(this.boardNo)
                this.refreshCheck = 1
            }
        }
    },
    computed: {
        ...mapState(['board']),
        ...mapState(['comments']),
        ...mapState(['qnaMembers'])
    },    
    created () {
        this.fetchQnABoard(this.boardNo)
         .then(() => {
                    this.$store.state.boardNo = this.boardNo // comment regist 하기 위해서 boardNo 값 저장
                    console.log('댓글 load 후에 저장된 state.boardNo값은? : ' + this.$store.state.boardNo)
                    })
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })
    },
    mounted () {
        this.fetchQnACommentList(this.boardNo)
    },
    methods: {
        ...mapActions(['fetchQnABoard']),
        ...mapActions(['fetchQnACommentList']),
        onDeletePost () {
                        const { boardNo } = this.board
                        axios.delete(`http://localhost:7777/qnaboard/${boardNo}`)
                        .then(() => {
                            this.$router.push({ name: 'BoardCollectionPage' })
                        })
                        .catch(err => {
                            alert(err.response.data.message)
                        })
        },
        onSubmit (payload) {
            const refresh = payload
            this.refreshCheck = refresh
        }
    }
}
</script>

<style scoped>
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
.middle_btn_box {
    max-width: 1000px;
    text-align: center;
    margin: 0px;
    color: #757575 !important;
    font-weight: bold;
}
.v-application a {
    color: #757575 !important;
    font-weight: bold;
}
.item {
    cursor: pointer;
}
a { text-decoration:none !important }
a:hover { text-decoration:none !important }
</style>