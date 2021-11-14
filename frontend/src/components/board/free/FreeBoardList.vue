<template>
    <div class="main">
        <div class="main_box">
            <!-- 제목 -->
            <div class="mr-9 hidden-sm-and-down">
                <div class="title_box">
                    <h2 class="page_title">
                        <span>자유 게시판</span></h2>
                </div>
            </div>
            <!-- 검색창 + complete 분류 -->
            <v-spacer class="forLine0">
                <div class="forLine0sButton">
                    <div class="tag_button" :class="{ on2 : completeSelect1 }" @click="fetchFreeBoardList(),
                                        completeSelect1 = true, completeSelect2 = false, completeSelect3 = false, word = '' ">전체</div>&nbsp;&nbsp;&nbsp;
                </div>
                <div class="searching_box_top">
                    <div class="mr-9 hidden-sm-and-down">
                        <div class="searching" >
                            <span>
                                <input type="text" placeholder="검색어를 입력해주세요" v-model="word"
                                @keyup.enter="searching(word)">
                            </span>
                            <v-icon class="searching_icon" @click="searching(word)">mdi-magnify</v-icon>
                        </div>
                    </div>
                </div>
            </v-spacer>
            <!-- 분류창 -->
            <v-spacer class="forLine">
                <li class="tag_button" @click="word = ''">ALL</li>&nbsp;&nbsp;&nbsp;
                <li class="tag_button" :class="{ on : tagSelect1 }" @click="tagSelect1 = !tagSelect1, searchingTag('Java')">Java</li>&nbsp;&nbsp;&nbsp;
                <li class="tag_button" :class="{ on : tagSelect2 }" @click="tagSelect2 = !tagSelect2, searchingTag('Spring')">Spring</li>&nbsp;&nbsp;&nbsp;
                <li class="tag_button" :class="{ on : tagSelect3 }" @click="tagSelect3 = !tagSelect3, searchingTag('Python')">Python</li>&nbsp;&nbsp;&nbsp;
            </v-spacer>
            <!-- 게시글 리스트 -->
            <div class="forSearching" v-show="!searchinOn">
                <div class="post_list">
                    <div class="for_line"></div>
                    <div class="post_card_box">
                        <div v-for="mob in paginatedData" :key="mob.boardNo">
                            <div class="post_card" :class="{ on : mob.notice == 'true' }">
                                <div class="post_num" v-show="mob.notice == 'false'"><div class="mr-9 hidden-sm-and-down"><div style="width:16px;text-align:center;margin-right:3vw" >{{ mob.boardNo }}</div></div></div>
                                <div class="post_num" v-show="mob.notice == 'true'"><div class="mr-9 hidden-sm-and-down"><div style="width:54px;text-align:center;margin-right:3vw" >
                                    <div class="completeDisplay">공지사항</div></div></div></div>
                                <div class="post_title">
                                    <router-link :to="{ name: 'FreeBoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                        <div class="item4">{{ mob.title }}</div>
                                    </router-link>                                    
                                    <router-link :to="{ name: 'FreeBoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                        <div class="tag_box">
                                            <div class="post_reg_date">{{ calcTime(mob.regDate) }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                                <div class="hidden-md-and-up">
                                                    <div class="tag_box">
                                                        <!-- 여기는 모바일 사이즈 때 보이는 정보 -->
                                                        <div class="item2">
                                                            <v-icon size="18px" color="#9e9e9e">mdi-eye</v-icon>
                                                            <div style="padding-top:3px">&nbsp;{{ mob.views }}</div>
                                                        </div>
                                                        <div class="item3">
                                                            &nbsp;&nbsp;<v-icon size="18px" color="#9e9e9e">mdi-comment</v-icon>
                                                            <div style="padding-top:3px">&nbsp;{{ mob.comments }}</div>
                                                        </div>
                                                        <div class="item3">
                                                            &nbsp;&nbsp;<v-icon size="18px" color="#E57373">mdi-heart</v-icon>
                                                            <div style="padding-top:3px">&nbsp;{{ mob.currentNum }}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            <div v-show="mob.notice == 'true'">
                                                <div class="hidden-md-and-up">
                                                    <div class="completeDisplay">공지사항</div>
                                                </div>
                                            </div>
                                            <div v-show="mob.notice == 'false'" v-for="tag in classifyTag(mob.tags)" :key="tag.text">
                                                <div class="mr-9 hidden-sm-and-down">
                                                    <div class="tag_box_button_box">
                                                        <btn class="tag_box_button" @click="tagSelect0 = !tagSelect0,searchingTag(tag.text)">#{{ tag.text }}&nbsp;</btn>
                                                    </div>
                                                </div>
                                            </div>
                                            <div v-show="mob.complete == 'true'" class="completeDisplay2" @click="selectComplete('true')">답변완료</div>
                                        </div>
                                    </router-link>                                    
                                </div>
                                <router-link class="post_vnc" :to="{ name: 'FreeBoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                    <div class="mr-2 hidden-sm-and-down">
                                    <div class="item2">
                                        <v-icon size="18px" color="#9e9e9e">mdi-eye</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.views }}</div>
                                    </div>
                                    <div class="item3">
                                        <v-icon size="18px" color="#9e9e9e">mdi-comment</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.comments }}</div>
                                    </div>
                                    <div class="item3">
                                        <v-icon size="18px" color="#E57373">mdi-heart</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.currentNum }}</div>
                                    </div>
                                    </div>
                                </router-link>
                                <div class="post_name_box">
                                    <div class="mr-9 hidden-sm-and-down"><div class="post_name">{{ mob.nickname }}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="button_box">
                        <v-flex hidden-sm-and-down text-sm-right="text-sm-right">
                            <router-link :to="{ name: 'FreeBoardRegisterPage' }">
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
                <div class="post_list">
                    <div class="for_line"></div>
                    <div class="post_card_box">
                        <div v-for="mob in paginatedDataS" :key="mob.boardNo">
                            <div class="post_card" :class="{ on : mob.notice == 'true' }">
                                <div class="post_num" v-show="mob.notice == 'false'"><div class="mr-9 hidden-sm-and-down"><div style="width:16px;text-align:center;margin-right:3vw" >{{ mob.boardNo }}</div></div></div>
                                <div class="post_num" v-show="mob.notice == 'true'"><div class="mr-9 hidden-sm-and-down"><div style="width:54px;text-align:center;margin-right:3vw" >
                                    <div class="completeDisplay">공지사항</div></div></div></div>
                                <div class="post_title">
                                    <router-link :to="{ name: 'FreeBoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                        <div class="item4">{{ mob.title }}</div>
                                    </router-link>                                    
                                    <router-link :to="{ name: 'FreeBoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                        <div class="tag_box">
                                            <div class="post_reg_date">{{ calcTime(mob.regDate) }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                                <div class="hidden-md-and-up">
                                                    <div class="tag_box">
                                                        <!-- 여기는 모바일 사이즈 때 보이는 정보 -->
                                                        <div class="item2">
                                                            <v-icon size="18px" color="#9e9e9e">mdi-eye</v-icon>
                                                            <div style="padding-top:3px">&nbsp;{{ mob.views }}</div>
                                                        </div>
                                                        <div class="item3">
                                                            &nbsp;&nbsp;<v-icon size="18px" color="#9e9e9e">mdi-comment</v-icon>
                                                            <div style="padding-top:3px">&nbsp;{{ mob.comments }}</div>
                                                        </div>
                                                        <div class="item3">
                                                            &nbsp;&nbsp;<v-icon size="18px" color="#E57373">mdi-heart</v-icon>
                                                            <div style="padding-top:3px">&nbsp;{{ mob.currentNum }}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            <div v-show="mob.notice == 'true'">
                                                <div class="hidden-md-and-up">
                                                    <div class="completeDisplay">공지사항</div>
                                                </div>
                                            </div>
                                            <div v-show="mob.notice == 'false'" v-for="tag in classifyTag(mob.tags)" :key="tag.text">
                                                <div class="mr-9 hidden-sm-and-down">
                                                    <div class="tag_box_button_box">
                                                        <btn class="tag_box_button" @click="tagSelect0 = !tagSelect0,searchingTag(tag.text)">#{{ tag.text }}&nbsp;</btn>
                                                    </div>
                                                </div>
                                            </div>
                                            <div v-show="mob.complete == 'true'" class="completeDisplay2" @click="selectComplete('true')">답변완료</div>
                                        </div>
                                    </router-link>                                    
                                </div>
                                <router-link class="post_vnc" :to="{ name: 'FreeBoardReadPage', params: { boardNo: mob.boardNo.toString() } }" >
                                    <div class="mr-2 hidden-sm-and-down">
                                    <div class="item2">
                                        <v-icon size="18px" color="#9e9e9e">mdi-eye</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.views }}</div>
                                    </div>
                                    <div class="item3">
                                        <v-icon size="18px" color="#9e9e9e">mdi-comment</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.comments }}</div>
                                    </div>
                                    <div class="item3">
                                        <v-icon size="18px" color="#E57373">mdi-heart</v-icon>
                                        <div style="padding-top:3px">&nbsp;{{ mob.currentNum }}</div>
                                    </div>
                                    </div>
                                </router-link>
                                <div class="post_name_box">
                                    <div class="mr-9 hidden-sm-and-down"><div class="post_name">{{ mob.nickname }}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="button_box">
                        <v-flex hidden-sm-and-down text-sm-right="text-sm-right">
                            <router-link :to="{ name: 'FreeBoardRegisterPage' }">
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
                            <v-pagination class="btn_pagination" v-model="pageNumS" :length="pageCountS"
                                prev-icon="mdi-chevron-left" next-icon="mdi-chevron-right" light
                                ></v-pagination>
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
        <!-- 모바일 사이즈 때 나타나는 글쓰기 버튼 -->
        <router-link :to="{ name: 'FreeBoardRegisterPage' }">
            <v-btn fab dark large color="primary" fixed right class="hidden-md-and-up">
                <v-icon dark>mdi-plus</v-icon>
            </v-btn>
        </router-link>
        링크용
        <router-link :to="{ name: 'StudyBoardListPage' }">
            <v-btn>스터디 게시판</v-btn>
        </router-link>
        <router-link :to="{ name: 'QnABoardListPage' }">
            <v-btn>QnA 게시판</v-btn>
        </router-link>
        <router-link :to="{ name: 'InqBoardListPage' }">
            <v-btn>문의 게시판</v-btn>
        </router-link>
        <router-link :to="{ name: 'InqBoardListForUserPage' }">
            <v-btn>문의 for user 게시판</v-btn>
        </router-link>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
    name: 'FreeBoardList',
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
            complete: '',

            interval: {},
            value: '',
            value2: 20,
            tagSelect0: false,
            tagSelect1: false,
            tagSelect2: false,
            tagSelect3: false,
            completeSelect1: true,
            completeSelect2: false,
            completeSelect3: false
        }
    },
    beforeDestroy () {
    clearInterval(this.interval)
    },
    watch: {
        word(newVal) {       //이런식으로 watch 사용
            if(newVal == '') {
                    this.searchinOn = false
                    this.tagSelect0 = false
                    this.tagSelect1 = false // All 클릭시 초기화용
                    this.tagSelect2 = false
                    this.tagSelect3 = false
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
        //     console.log(this.$store.state.nickname)
        //     this.$store.state.nickname = '임시닉네임'
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
                const regex = new RegExp(this.word, "gi");
                const comparison = regex.test(lists[i].title)
                const comparison2 = regex.test(lists[i].content)
                const comparison3 = regex.test(lists[i].tags)
                if(comparison | comparison2 | comparison3){
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
        searchingTag (tag) {
            var lists = this.boards
            this.searchingResult = []
            for(var i = 0; i < lists.length; i++){                    
                const regex = new RegExp(tag, "gi");
                const comparison = regex.test(lists[i].tags)
                if(comparison){
                    this.searchingResult.push(lists[i])
                }
            }
            this.searchinOn = true
            var b = '#'
            this.word = b.concat(tag)

            // 하나라도 true면 if문 생략
            if (!this.tagSelect0 && !this.tagSelect1 && !this.tagSelect2 && !this.tagSelect3) {
                this.searchinOn = false
                this.word = ''
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
        },
        classifyTag(data) {
            var arr = JSON.parse(data)
            console.log(arr)
            return arr
        },
        selectComplete(data) {
            if(data == 'true') {
                this.completeSelect1 = false
                this.completeSelect2 = false
                this.completeSelect3 = true
            } else {
                this.completeSelect1 = false
                this.completeSelect2 = true
                this.completeSelect3 = false
            }
            this.fetchFreeBoardListWithFilter(data)
        },
        ...mapActions(['fetchFreeBoardList']),
        ...mapActions(['fetchFreeBoardListWithFilter'])
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
        })
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
.tag_button.on2 {
    color: rgb(53, 53, 53);
    font-weight: bold;
}
.tag_button:hover {
    color: rgb(63, 63, 63);
    cursor: pointer;
    transition: all 0.5s ease;
}
.forLine0 {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 40px;
    padding-left: 2vw;
}
.forLine0sButton {
    display: flex;
    justify-content: start;
    align-items: center;

}
.forLine {
    height: 40px;
    border-top: 1px solid #BDBDBD;
    border-bottom: 1px solid #BDBDBD;
    padding-left: 2vw;
    display: flex;
    justify-content: start;
    align-items: center;
}
.main_box {
    color: #424242;
}
.title_box {
    margin-top: 100px;
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
    min-width: 300px;
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
    display:flex;
    justify-content: center;
    flex-direction: column;
    align-content: center;
}
.searching_message {
    display: flex;
    justify-content: center;
    flex-direction: column;
    border-bottom: 1px solid #BDBDBD;
    margin-top: 20px;
    height: 150px;
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
.for_line {
    border-bottom: 1px solid #BDBDBD;
    margin: 0vw 1vw
}
.post_list {
    min-width: 475px;
    max-width: 1500px;
    margin-right: 10px;
    margin-top: 30px;
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
    height: 62px;
    border-bottom: 1px solid #BDBDBD;
}
.post_card.on {
    display: flex;
    justify-content: flex-start;
    margin: 0vw 1vw;
    height: 62px;
    border-bottom: 1px solid #BDBDBD;
    background-color: #F5F5F5;
}
.post_card.on:hover {
    box-shadow: 10px 17px 40px 0 rgb(0 0 0 / 4%);
    background-color: rgb(241, 241, 241);
    cursor: pointer;
    transition: all 0.1s ease;
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
    text-align: right;
    margin: 0 0 0 2.5vw;
}
.post_title {
    display: flex;
    flex-direction: column;    
    align-self: center;
    margin: 0px;
    width: 57vw;
    max-width: 750px;
}
.item4 {
    display: flex;
    justify-self: start;
    font-size: 15px !important;
    font-weight: bold !important;
    color: #2b2b2b;
    max-width: 55vw;
    min-width: 450px;

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
    min-width: 37px;
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
    min-width: 35px;
}
.item3 {
    display: flex;
    flex-direction: row;
    font-size: 11px;
    color: #757575;
    margin-right: 6px;
}
.post_reg_date {
    min-width: 51px;
    padding-top: 2px;
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
.tag_box {
    display:flex;
    justify-content: start;
    flex-direction: row;
    align-content: center;
    height: 20px
}
.tag_box_button {
    display: flex;
    align-self: center;
    font-size: 12px;
    min-width: 20px;
    font-weight: 500;
    color: #01579B;
    transition: all 0.4s ease;
}
.tag_box_button:hover {
    font-size: 12px;
    min-width: 30px;
    color: #01579B;
    font-weight: bold;
    transition: all 0.4s ease;
}
.completeDisplay {
    background-color: #FFAB00;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 20px;
    padding: 0 4px 0 4px;
    font-size: 11px;
    font-weight: bold;
}
.completeDisplay2 {
    background-color: #C2185B;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 20px;
    padding: 0 4px 0 4px;
    font-size: 11px;
    font-weight: bold;
}
.v-application .mr-9 {
    margin: 0px !important;
}
.btn_position {
    position: fixed;
}
.v-btn--fixed {
    top:700px !important;
}
</style>
