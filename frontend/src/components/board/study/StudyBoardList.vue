<template>
    <div>
        <div class="main_box">
            <!-- 제목 -->
            <div class="title_box">
                <h4 class="page_title">
                    <v-icon>mdi-exclamation-thick</v-icon>
                    <span>스터디 모집</span></h4>
            </div>
            <!-- 옵션바 -->
            <div class="option_box">
                <div style="transform: scale(0.7);">
                    <v-btn-toggle v-model="toggle_exclusive" shaped mandatory>
                        <v-btn>
                            <v-icon>mdi-image</v-icon>
                        </v-btn>
                        <v-btn>
                            <v-icon>mdi-format-align-justify</v-icon>
                        </v-btn>
                    </v-btn-toggle>
                </div>
            </div>
            <!-- 검색창 -->
            <div class="searching_box">
                <div class="searching_bar">
                    <v-icon style="margin:10px">mdi-magnify</v-icon>
                    <span>
                        <input type="text" class="searching" placeholder="검색어를 입력해주세요" v-model="word"
                        @keyup.enter="searching(word)">
                    </span>
                </div>
            </div>
            <!-- 게시글 리스트 -->
            <div class="forSearching" v-show="!searchinOn">
                <div class="post_list" v-show="!toggle_exclusive">
                    <div class="post_card_box">
                        <div class="post_card" v-for="mob in paginatedData" :key="mob.boardNo">
                            <router-link
                                    :to="{ name: 'StudyBoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                            <div class="thumbnail">
                                <v-img :src="ImgRequest(mob.writer, mob.boardNo)" class="thumbnail_img"></v-img>
                            </div></router-link>
                            <div class="post_box">
                                <div class="post_tag">#TAG</div>
                                <router-link
                                    :to="{ name: 'StudyBoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                                <div class="post_title">{{ mob.title }}</div>
                                <div class="post_content">{{ replaceHtml(mob.content) }}</div>
                                <div class="post_reg_date">{{ mob.name }} | {{ $moment(mob.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</div></router-link>
                            </div>
                        </div>
                    </div>
                    <div class="button_box">
                        <v-flex text-xs-right="text-xs-right" text-sm-right="text-sm-right">
                            <router-link :to="{ name: 'StudyBoardRegisterPage' }">
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
                            <v-pagination class="btn_pagination" v-model="pageNum" :length="pageCount"></v-pagination>
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
                                    :to="{ name: 'StudyBoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                            <div class="thumbnail">
                                <v-img :src="ImgRequest(mob.writer, mob.boardNo)" class="thumbnail_img"></v-img>
                            </div></router-link>
                            <div class="post_box">
                                <div class="post_tag">#사료추천</div>
                                <router-link
                                    :to="{ name: 'StudyBoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                                <div class="post_title">{{ mob.title }}</div>
                                <div class="post_content">{{ replaceHtml(mob.content) }}</div>
                                <div class="post_reg_date">{{ $moment(mob.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</div></router-link>
                            </div>
                        </div>
                    </div>
                    <div class="button_box">
                        <v-flex text-xs-right="text-xs-right" text-sm-right="text-sm-right">
                            <router-link :to="{ name: 'StudyBoardRegisterPage' }">
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
        </div>

        <div class="forSearching"  v-show="!searchinOn">
            <div v-show="toggle_exclusive" style="max-width:1000px;">
            <v-simple-table>
                <template>
                    <thead>
                        <tr>
                            <th class="text-center" width="64">No.</th>
                            <th class="text-center" style="width:45vw;">제목</th>
                            <th class="text-center" style="min-width:8px;">작성자</th>
                            <th class="text-center" style="min-width:8px;">작성일</th>
                        </tr>
                        <tr v-if="!boards || (Array.isArray(boards) && boards.length === 0)">
                            <td colspan="4">
                                현재 등록된 게시물이 없습니다!
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="mob in paginatedData" :key="mob.boardNo" class="item">
                            <td style="text-align:center">{{ mob.boardNo }}</td>
                            <td>
                                <router-link
                                    :to="{ name: 'StudyBoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                                    {{ mob.title }}
                                </router-link>
                            </td>
                            <td style="text-align:center">{{ mob.name }}</td>
                            <td style="text-align:center">{{ $moment(mob.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</td>
                        </tr>
                    </tbody>
                </template>
            </v-simple-table>
            <div class="button_box">
                <v-flex text-xs-right="text-xs-right" text-sm-right="text-sm-right">
                    <router-link :to="{ name: 'StudyBoardRegisterPage' }">
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
                    <v-pagination class="btn_pagination" v-model="pageNum" :length="pageCount"></v-pagination>
                </div>
            </v-container>
            </div>
        </div>
        <!-- 검색결과 리스트형 -->
        <div class="forSearching"  v-show="searchinOn">
            <div v-show="toggle_exclusive" style="max-width:1000px;">
            <v-simple-table>
                <template>
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="mob in paginatedDataS" :key="mob.boardNo" class="item">
                            <td style="text-align:center">{{ mob.boardNo }}</td>
                            <td>
                                <router-link
                                    :to="{ name: 'StudyBoardReadPage',
                                                        params: { boardNo: mob.boardNo.toString() } }">
                                    {{ mob.title }}
                                </router-link>
                            </td>
                            <td style="text-align:center">{{ mob.name }}</td>
                            <td style="text-align:center">{{ $moment(mob.regDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}</td>
                        </tr>
                    </tbody>
                </template>
            </v-simple-table>
            <div class="button_box">
                <v-flex text-xs-right="text-xs-right" text-sm-right="text-sm-right">
                    <router-link :to="{ name: 'StudyBoardRegisterPage' }">
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
    </div>
</template>

<script>
import { mapState } from 'vuex'

    export default {
        name: 'StudyBoardList',
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
                searchinOn: false
            }
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
                return require(`../../../../../backend/khweb/images/study/${a}_${b}.gif`
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
.main_box {
    color: #424242;
}
.title_box span {
    font-size: 25px;
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
    width:70vw;
    max-width: 1000px;
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
    width: 50vw;
}
.post_tag {
    color: #0288D1;
    font-weight: bold;
    font-size: 13px;
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
    font-size: 13px;
    color: #757575;
    max-height: 54px;

    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    line-height: 16px;
    -webkit-line-clamp: 3; /* 표시하고자 하는 라인 수 */
    -webkit-box-orient: vertical;
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
</style>