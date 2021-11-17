<template>
<div>
    댓글 <b style="color:#00897B">{{comments.length}}</b>
    <div class="comment_list">
        <div>
            <!-- 댓글 목록 -->
            <div class="post_list">
                <div class="post_card_box">
                    <div  v-for="mob in paginatedData" :key="mob.commentNo">
                        <div class="post_card" :class="{ on : mob.layer!==0 }">
                            <!-- 섬네일 -->
                            <div class="thumbnail">
                                <v-avatar color="black" size="48" >
                                    <span><img :src="ImgRequest(mob.writer)" style="width:60px;height:60px;object-fit: cover"></span></v-avatar>
                            </div>
                            <!-- 댓글 박스 -->
                            <div class="post_box"  @click="temp00(mob)">
                                <div class="post_title">{{ mob.nickname }}</div>
                                <div class="post_reg_date">{{ $moment(mob.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</div>
                                <div class="post_content" v-html="mob.content">{{ mob.content }}</div>
                                <!-- <div class="post_reg_date">commentNO: {{ mob[0] }}</div>
                                <div class="post_reg_date">groupNO: {{ mob[3] }}</div>
                                <div class="post_reg_date">layer: {{ mob[4] }}</div> -->
                            </div>
                            <div v-show="mob.writer == writer || writer=='admin01'" class="delete_box"><img src="@/assets/delete-empty.png" 
                                @click="snackbar = true"></div>
                        </div>
                            <!-- 대댓글 입력창 -->
                            <div class="adit_comment_area" v-show="temp && mob.layer==0 && mob.commentNo==commentNo">
                                <tr>
                                    <textarea class="adit_comment_register_box"
                                    v-model="content2" placeholder="댓글을 입력해주세요"></textarea>
                                </tr>
                                <td class="comment_register_btn">
                                    <v-btn color="blue-grey darken-1 white-text" @click="submit">댓글 등록</v-btn>
                                </td>
                            </div>    
                            <!-- 댓글 삭제 클릭시 알림창 -->
                            <div class="text-center">            
                                <v-snackbar v-model="snackbar"
                                :timeout="-1" centered outlined>
                                댓글을 삭제하시겠습니까?
                                    <template v-slot:action="{ attrs }">
                                        <v-btn color="#424242" text v-bind="attrs" @click="[isNameProblem(mob.commentNo), snackbar = false]">
                                        확인</v-btn>
                                        <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
                                        취소</v-btn>
                                    </template>
                                </v-snackbar>
                            </div>
                    </div>
                </div>
                <v-container style="margin-top:20px;">
                    <div class="text-center">
                        <v-pagination class="btn_pagination" v-model="pageNum" :length="pageCount"></v-pagination>
                    </div>
                </v-container>
            </div>
            <!-- 댓글 입력창 -->
            <div class="comment_area" @click="temp = false, groupNo = 0, layer = 0, commentNo = 0">
                <editor-for-comment @fromEditor="commentSubmit"/>
            </div>
        </div>
        <!-- 하단 밑줄용 -->
        <v-container class="button_container"></v-container>
        </div>
    </div>
</template>

<script>

import axios from 'axios'
import EditorForComment from '@/components/board/EditorForComment.vue'

export default {
    name: 'CommentList',
    components: {
        EditorForComment
    },
    data () {
        return {
            content: '',
            writer: this.$store.state.moduleA.email,
            nickname: this.$store.state.moduleA.nickname,
            boardNo: '',
            refresh: 1,
            pageNum: 1,
            pageNumS: 1,
            snackbar: false,

            //대댓글
            layer: 0,
            groupNo: null,
            temp: false,
            commentNo: 0,
            content2: ''
        }
    },
    props: {
        comments: {
            type: Array
        },
        pageSize: {
            type: Number,
            required: false,
            default: 10
        }
    },
    computed: {
        pageCount() {
                let listLength = this.comments.length, // 길이
                    listSize = this.pageSize,
                    page = Math.floor(listLength / listSize);
                if (listLength % listSize > 0) 
                    page += 1;
                return page;
        },
        paginatedData() {
            const start = (this.pageNum - 1) * this.pageSize,
            end = start + this.pageSize;
            return this.comments.slice(start, end);
        }
    },
    methods: {
        commentSubmit (data) {
            console.log(data)
            this.content = data
            console.log('저장하는 순간 store boardNo 값 : ' + this.$store.state.boardNo)            
            if(this.layer==1) {
                this.content = this.content2
            }
            
            this.boardNo = this.$store.state.boardNo
            this.groupNo = this.commentNo
            const { boardNo, content, writer, nickname, layer, groupNo } = this
            console.log('저장하는 순간 const값')
            console.log({ boardNo, content, writer, nickname, layer, groupNo })
            axios.post('http://localhost:7777/inqboard/comment/register', { boardNo, content, writer, nickname, layer, groupNo } )
                    .then(res => {
                        console.log('댓글등록완료 |' + res.status)
                         this.content = ''
                         this.content2 = ''
                         let listLength = this.comments.length, // 길이
                         listSize = this.pageSize,
                         page = Math.floor(listLength / listSize);
                         if (listLength % listSize >= 0)
                         page += 1;
                         if (page !== 1) {
                             this.pageNum = page
                         }
                         this.refresh += 1
                         const refresh = this.refresh
                         this.$emit('submit', refresh)

                         this.groupNo = 0 // 초기화
                         this.layer = 0
                         this.temp = false
                    })
                    .catch(res => {
                        alert(res.response.data.message + '설마 이거 뜨나?')
                    })
        },
        isNameProblem (commentNumero) {
            console.log('commentNumero 값은? : ' + commentNumero)
            console.log('commentNumero 형식은? : ' + typeof commentNumero)
            let commentNo2 = commentNumero
            const commentNo = commentNo2
            console.log('const { commentNo } 값은? : ' + commentNo)
            axios.delete(`http://localhost:7777/inqboard/comment/${commentNo}`)
                    .then(() => {
                        this.refresh += 1;
                        const refresh = this.refresh;
                        this.$emit('submit', refresh);                   
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        },
        nextPage() {
                this.pageNum += 1;
        },
        prevPage() {
            this.pageNum -= 1;
        },
        ImgRequest(data) {
            try {
                var cutId = data.substring(0, data.length-4); // email 뒤 .com 삭제
                console.log(cutId)
                return require(`../../../../../backend/khweb/images/profiles/${cutId}.gif`)               
            
            } catch (e) {
                return require(`@/assets/logo.png`)
            }
        },
        temp00(data) {
            this.commentNo = data.commentNo
            this.layer = 1
            if(this.temp == true) {
                this.temp = false
                this.temp = !this.temp
            } else {
                this.temp = !this.temp
            }
        }
    }
}

</script>

<style scoped>
.comment_list {
    width:95vw;
    max-width: 1050px;
    margin-top: 10px;
    border-top: #BDBDBD solid 1px;
    padding-top: 20px;
}
.post_list {
    width:100vw;
    max-width: 1000px;
}
.post_card {
    display: flex;
    justify-content: row;
    padding: 20 20 5 5;
    margin-bottom: 20px;
}
.post_card.on {
    display: flex;
    justify-content: row;
    padding: 20 20 5 5;
    margin-bottom: 20px;
    margin-left: 5vw;
}
.thumbnail {
    margin: 10px 20px 0px 10px;
    height: 40px !important; 
    width: 40px !important; 
}
.thumbnail_img {
    width: 100%;
    height: 100%;
}
.post_box {
    display: flex;
    flex-direction: column;
    width: 70vw;
}
.post_tag {
    color: #0288D1;
    font-weight: bold;
    font-size: 13px;
}
.post_title {
    margin-top: 15px;
    font-size: 17px;
    font-weight: bold;
    color: #424242;
}
.post_content {
    font-size: 15px;
    color: #424242;
}
.post_reg_date {
    font-size: 11px;
    color: #757575;
}
.btn_pagination {
    transform: scale(0.6);
    background-color: transparent;
    box-shadow: none;
}
.v-application.primary {
    background-color: #757575 !important;
    border-color: #757575 !important;
}
.delete_box {
    margin-top: 30px;
    height: 100%;
    display: flex;
    cursor: pointer;
}
.comment_register_box {
    height:150px;
    width:65vw;
    max-width: 1040px;
    border: 1px solid #BDBDBD;    
    padding: 10px;
}
.comment_register_btn {
    text-align: right;
    padding: 0px;
}
::placeholder {
    font-size: 16px;
    letter-spacing: 0;
    color: #757575;    
}
.button_container {
    max-width: 1040px;
    border-top: 1px solid #BDBDBD;
    margin-top: 15px;
    margin-right: 300px;
}
.adit_comment_area {
    margin-left: 5vw;
}
.adit_comment_register_box {
    height:100px;
    width:62vw;
    max-width: 1000px;
    border: 1px solid #BDBDBD;    
    padding: 10px;
    margin-left: 20px;
}
</style>