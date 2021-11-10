ㅂ<template>
    <div class="main">
        <div class="main_box">
            <!-- 제목 -->
            <div class="title_box">
                <h2 class="page_title">
                    <span>질문답변 게시판</span></h2>
            </div>
            <!-- 검색창 -->
            <div class="searching_box_top">
                <div class="searching_barp">
                    <div class="searching" >
                        <span>
                            <input type="text" placeholder="검색어를 입력해주세요" v-model="word"
                            @keyup.enter="searching(word)">
                        </span>
                        <v-icon class="searching_icon" @click="searching(word)">mdi-magnify</v-icon>
                    </div>
                </div>
                <!-- 글쓰기 -->
                <div style="width:50px;">
                    <router-link :to="{ name: 'QnABoardRegisterPage' }">
                        <v-btn
                            v-if="this.$store.state.isLogin"
                            color="blue darken-3 text center"
                            class="change-font">
                            글쓰기
                        </v-btn>
                    </router-link>
                </div>
            </div>
            <!-- 분류창 -->
            <v-spacer class="forLine">
                <li class="tag_button" :class="{ on : tagSelect1 }" @click="tagSelect1 = !tagSelect1">Vue</li>&nbsp;&nbsp;&nbsp;
                <li class="tag_button" :class="{ on : tagSelect2 }" @click="tagSelect2 = !tagSelect2">Spring</li>&nbsp;&nbsp;&nbsp;
                <li class="tag_button" :class="{ on : tagSelect3 }" @click="tagSelect3 = !tagSelect3">Python</li>&nbsp;&nbsp;&nbsp;
            </v-spacer>
            <!-- 게시글 리스트 -->
            <div class="forSearching" v-show="!searchinOn">
                <div class="post_list">
                    <div class="post_card_box">
                        <div v-for="mob in paginatedData" :key="mob.boardNo">
                            <router-link class="post_card" :to="{ name: 'QnABoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                <div class="post_num">{{ mob.boardNo }}</div>
                                <div class="post_title">
                                    <div class="item4">{{ mob.title }}</div>
                                    <div class="post_reg_date">{{ calcTime(mob.regDate) }}</div>
                                </div>
                                <div class="post_vnc">
                                    <div class="item2">
                                        <v-icon size="18px" color="#9e9e9e">mdi-eye</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.views }}</div>
                                    </div>
                                    <div class="item3">
                                        <v-icon size="18px" color="#9e9e9e">mdi-comment</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.comments }}</div>
                                    </div>
                                </div>
                                <div class="post_name_box">
                                    <div class="post_name">{{ mob.name }}</div>
                                </div>
                            </router-link>
                        </div>
                    </div>
                    <div class="button_box">
                        <v-flex text-xs-right="text-xs-right" text-sm-right="text-sm-right">
                            <router-link :to="{ name: 'QnABoardRegisterPage' }">
                                <v-btn
                                    v-if="this.$store.state.isLogin"
                                    color="blue darken-3 text center"
                                    class="change-font">
                                    글쓰기
                                </v-btn>
                            </router-link>
                        </v-flex>
                    </div>
                    <!-- 페이지네이션 -->
                    <v-container style="margin-top:20px;">
                        <div class="text-center">
                            <v-pagination class="btn_pagination" v-model="pageNum" :length="pageCount"
                                prev-icon="mdi-chevron-left" next-icon="mdi-chevron-right" light
                                ></v-pagination>
                        </div>
                    </v-container>
                </div>
            </div>
            <!-- 검색결과 -->
            <div class="forSearching"  v-show="searchinOn">
                <div class="searching_message_box">
                    <div class="searching_message">
                        <div><b>{{ word }}</b> 검색 결과</div>
                        <div><p>{{this.searchingResult.length}} 건의 게시물이 검색되었습니다.</p></div>
                    </div>
                </div>
                <div class="post_list" v-show="!toggle_exclusive">
                    <div class="post_card_box">
                        <div class="post_card" v-for="mob in paginatedDataS" :key="mob.boardNo">
                            <router-link
                                    :to="{ name: 'QnABoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                            <div class="thumbnail">
                                <v-progress-circular
                                :rotate="-90"
                                :size="100"
                                :width="15"
                                :value="value2"
                                color="primary"
                                >
                                {{ value }} 
                                </v-progress-circular>
                            </div></router-link>
                            <div class="post_box">
                                <div class="post_tag">#</div>
                                <router-link
                                    :to="{ name: 'QnABoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                                <div class="post_title">{{ mob.title }}</div>
                                <div class="post_content">{{ replaceHtml(mob.content) }}</div>
                                <div class="post_reg_date">{{ $moment(mob.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</div></router-link>
                                <div class="post_title">{{ mob.complete }}</div>
                            </div>
                            <div v-show="mob.complete == 'true'">
                                <img src="@/assets/complete.png" v-show="mob.complete" width="130" class="item">
                            </div>
                        </div>
                    </div>
                    <div class="button_box">
                        <v-flex text-xs-right="text-xs-right" text-sm-right="text-sm-right">
                            <router-link :to="{ name: 'QnABoardRegisterPage' }">
                                <v-btn
                                    v-if="this.$store.state.isLogin"
                                    color="light-blue lighten-1 text center"
                                    class="change-font">
                                    글쓰기
                                </v-btn>
                            </router-link>
                        </v-flex>
                    </div>
                    <v-container style="margin-top:20px;">
                        <div class="text-center">
                            <v-pagination class="btn_pagination" v-model="pageNumS" :length="pageCountS"></v-pagination>
                        </div>
                    </v-container>
                </div>
            </div>
            <!-- 검색창 -->
            <div class="searching_box">
                <div class="searching_bar">
                    <div class="searching" >
                        <span>
                            <input type="text" placeholder="검색어를 입력해주세요" v-model="word"
                            @keyup.enter="searching(word)">
                        </span>
                        <v-icon class="searching_icon" @click="searching(word)">mdi-magnify</v-icon>
                    </div>
                </div>
            </div>
        </div>        
    </div>
</template>

<script>
import { mapState } from 'vuex'

    export default {
        name: 'QnABoardList',
        props: {
            boards: {
                type: Array
            },
            pageSize: {
                type: Number,
                required: false,
                default: 10
            }
        },
        data() {
            return {
                pageNum: 1,
                pageNumS: 1,
                toggle_exclusive: [],
                word: '',
                searchingResult: [],
                searchinOn: false,

                interval: {},
                value: '',
                value2: 20,
                tagSelect1: false,
                tagSelect2: false,
                tagSelect3: false,
            }
        },
        beforeDestroy () {
        clearInterval(this.interval)
        },
        watch: {
            word(newVal) {       //이런식으로 watch 사용
                if(newVal == '') {
                    setTimeout(() => {
                        this.searchinOn = false
                        }, 200)
                }
            }
        },
        methods: {
            calcTime(data) { 
                const moment = require("moment");
                var d = new Date();
                var regDate = moment(data).add(0, 'hours')
                var calcM = -regDate.diff(d, 'minute')
                var calcH = -regDate.diff(d, 'hours')
                var calcD = -regDate.diff(d, 'days')
                let checkM = Number(calcM)



                if (checkM < 60) {
                    return (calcM + ' 분 전')
                } else if(checkM < 1440) {
                    return(calcH + ' 시간 전')
                } else {
                    return(calcD + ' 일 전')
                }
            },
            // test() {
            //     console.log(this.$store.state.name)
            //     this.$store.state.name = '임시닉네임'
            // },
            nextPage() {
                this.pageNum += 1;
            },
            prevPage() {
                this.pageNum -= 1;
            },
            nextPageS() {
                this.pageNumS += 1;
            },
            prevPageS() {
                this.pageNumS -= 1;
            },
            ImgRequest( a, b ) {
                console.log(a + '_' + b)
            try {
                return require(`../../../../../backend/khweb/images/qna/${a}_${b}.gif`
                )
            } catch (e) {
                return require(`@/assets/logo.png`)
                }
            },
            searching () {
                var lists = this.boards

                this.searchingResult = []
                for(var i = 0; i < lists.length; i++){
                    if(lists[i].title.includes(this.word || lists[i].content.includes(this.word))){
                        this.searchingResult.push(lists[i])
                    }
                }
                console.log('searching 결과 : ' + this.searchingResult)
                console.log('0번 값은? : ' + this.searchingResult[0])
                this.searchinOn = true
                
                if (this.word == '') {
                    this.searchinOn = false
                }                
            },
            replaceHtml(data) {
                var text = data.replace(/(<([^>]+)>)/ig,"");
                return text
            },
            selectTag(data) {
                switch(data) {
                    case 1:
                        this.checkTag1 = "black"
                        break;
                    default:
                        break;                   
                }
            }
        },
        computed: {
            pageCount() {
                let listLength = this.boards.length, // 길이
                    listSize = this.pageSize,
                    page = Math.floor(listLength / listSize);
                if (listLength % listSize > 0) 
                    page += 1;
                return page;
            },
            paginatedData() {
                const start = (this.pageNum - 1) * this.pageSize,
                    end = start + this.pageSize;
                return this
                    .boards
                    .slice(start, end);

            },
            pageCountS() {
                let listLength = this.searchingResult.length, // 길이
                    listSize = this.pageSize,
                    page = Math.floor(listLength / listSize);
                if (listLength % listSize > 0) 
                    page += 1;
                return page;
            },
            paginatedDataS() {
                const start = (this.pageNumS - 1) * this.pageSize,
                    end = start + this.pageSize;
                return this
                    .searchingResult
                    .slice(start, end);

            },
            ...mapState ({
            lists: state => state.lists
            }),
        }
    }
</script>

<style scoped>
.tag_button {
    color: #BDBDBD;
    cursor: pointer;
}
.tag_button.on {
    color: black;
}
.tag_button:hover {
    color: rgb(63, 63, 63);
    cursor: pointer;
    transition: all 0.5s ease;
}
.forLine {
    height: 40px;
    border-bottom: 1px solid #BDBDBD;
    padding-left: 2vw;
    display: flex;
    justify-content: start;
    align-items: center;
}
.main_box {
    margin-top: 100px;
    color: #424242;
}
.title_box {
    margin-bottom: 100px;
}
.title_box span {
    font-size: 55px;
    font-weight: bold;
}
.option_box {
    display: flex;
    justify-content: flex-end;
    align-items: stretch;
    width: 70vw;    
    max-width: 1000px;
}
.searching_box {    
    height: 50px;
}
.searching_box_top {
    height: 50px;
    max-width: 1110px;
    display: flex;
    justify-content: space-between;
}
.searching_bar {
    display: flex;
    justify-content: center;
    height: 40px;
}
.searching {
    display: flex;
    justify-content: space-between;
    height: 43px; 
    width: 350px;
    padding-left: 10px;
    max-width: 955px;
    border: 1px solid #BDBDBD;
}
.searching:hover {
    display: flex;
    justify-content: space-between;
    height: 43px; 
    width: 350px;
    padding-left: 10px;
    max-width: 955px;
    border: 1px solid rgb(155, 155, 155);
}
.searching span {
    display: flex;
    align-self: center;
}
.searching span input {
    margin-top: 3px;
    width: 280px;
}
.searching_icon {
    padding: 11px 10px 10px 10px;
    border-left: 1px solid #BDBDBD;
    background-color: #FAFAFA;
}
.searching_icon:hover {
    padding: 11px 10px 10px 10px;
    border-left: 1px solid #BDBDBD;
    background-color: #dfdfdf;
}
input:focus {
    outline:none;
}
.searching_message_box {
    width:70vw;
    height: 180px;
    max-width: 1000px;
    display:flex;
    justify-content: center;
}
.searching_message {
    display: flex;
    justify-content: center;
    flex-direction: column;
    width:60vw;
    max-width: 900px;
    border-bottom: 1px solid #BDBDBD;
    margin-top: 20px;
}
.searching_message div {
    text-align: center;
    font-size: 20px;
    margin-bottom: 10px;
}
.searching_message b {
    letter-spacing: 3px;
    color: #0288D1;
    font-size: 30px;
}
.searching_message p {    
    font-size: 13px;
    color: #757575;

}




.post_list {
    min-width: 475px;
    max-width: 1500px;
    margin-right: 10px;
}
.post_card_box {
    min-width: 475px;
}
.post_card:hover {
    box-shadow: 10px 17px 40px 0 rgb(0 0 0 / 4%);
    background-color: rgb(241, 241, 241);
    cursor: pointer;
    transition: all 0.1s ease;
}
.post_card {
    display: flex;
    justify-content: flex-start;
    margin: 0vw 1vw;
    max-height: 12vh;
    height: 8vh;
    border-bottom: 1px solid #BDBDBD;
}
.post_card a {
    width: 1000px;
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
.post_num {
    display: flex;
    justify-content: center;
    align-self: center;
    width: 3vw;
    text-decoration: none;
    color: #757575;
    font-weight: 500;
    font-size: 14px;
    margin: 0 0 0 30px;
}
.post_title {
    display: flex;
    flex-direction: column;    
    align-self: center;
    margin: 0 0 0 30px;
    width: 57vw;
    max-width: 750px;
}
.item4 {
    font-size: 15px !important;
    font-weight: bold !important;
    color: #2b2b2b;
    max-width: 720px;

    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1; /* 표시하고자 하는 라인 수 */
    -webkit-box-orient: vertical;
}
.post_name_box {
    display: flex;
    align-self: center;
    justify-content: center;
    font-size: 13px;
    color: #757575;
    margin-right: 5vw; 
}
.post_name {
    text-align: center;
	width: 100px;
    max-width: 100px;
}
.post_vnc {
    width: 10vw;
    display: flex;
    flex-direction: column;
    align-self: center;
    color: #757575;
    font-weight: 500;
}
.item2 {
    display: flex;
    flex-direction: row;
    align-items: center;
    font-size: 11px;
    color: #757575;
}
.item3 {
    display: flex;
    flex-direction: row;
    font-size: 11px;
    color: #757575;
}
.post_reg_date {
    font-size: 11px;
    font-weight: 500 !important;
    color: #757575;
    letter-spacing: -1px;
}
.btn_pagination {
    background-color: transparent;
    box-shadow: none;
}
.button_box {
    max-width: 1140px;
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;
}
.change-font{
    font: 12pt;
    color: white !important;
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
.crawl_head {
    height:52px;
    width:50px;
    padding-top:16px;
    font-size: 14px;
    font-weight: 700;
}
.v-application a {
    color: #01579B !important;
    font-weight: 600;
}
a { text-decoration:none !important }
a:hover { text-decoration:none !important }
ul {
    list-style:none;
}
</style>