<template>
    <div class="main">
        <!-- 사이드바 -->
        <div class="sideBar hidden-sm-and-down">
            <v-card class="mx-auto0" max-width="500">
                <v-list>
                    <div class="title" @click="callAll(), path=''">전체 보기</div>
                    <v-list-group
                        v-for="item in items"
                        :key="item.title"
                        v-model="item.active"
                        :prepend-icon="item.action"
                        no-action="no-action">
                        <template v-slot:activator>
                            <v-list-item-content>
                                <v-list-item-title v-text="item.title"></v-list-item-title>
                            </v-list-item-content>
                        </template>
                        <v-list-item v-for="child in item.items" :key="child.title">
                            <v-list-item-content>
                                <!-- <v-btn @click="selectCategory(child)" style="cursor:pointer">{{child.title}}</v-btn> -->
                                <v-list-item-title class="child" v-text="child.title" @click="selectCategory(child)" style="cursor:pointer"></v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </v-list-group>
                </v-list>
            </v-card>
        </div>
        <!-- 본문 -->
        <div class="main_box">
            <!-- 검색창 + complete 분류 -->
            <v-spacer class="forLine0">
                <div class="forLine0sButton">
                </div>
                <div class="searching_box_top">
                    <div class="mr-9 hidden-sm-and-down">
                        <div class="searching" >
                            <span>
                                <input type="text" placeholder="검색어를 입력해주세요" v-model="word"
                                @keyup.enter="searchingWord(word)">
                            </span>
                               <v-icon class="searching_icon" @click="searching(word)">mdi-magnify</v-icon>
                        </div>
                    </div>
                </div>
            </v-spacer>
            <!-- 분류창 -->
            <v-spacer class="forLine">
                <h class="tag_button" @click="callAll(), word = '', path = '' ">ALL</h>&nbsp;&nbsp;&nbsp;
                <h> > </h>&nbsp;&nbsp;&nbsp;
                <h class="tag_button">{{ path }}</h>
            </v-spacer>
            <!-- 리스트 -->
            <v-container class="lecture01 mr-9 hidden-sm-and-down">
                <div v-show="!searchinOn">
                    <v-container class="lecture_box">
                        <div v-for="mob in paginatedData2" :key="mob.boardNo" class="item">
                            <v-card class="mx-auto">
                                <v-img :src="`http://localhost:7777/lecture/image/${mob[4]}/${mob[5]}`" height="200px"></v-img>
                                <!-- <div class="btn-plus"><span draggable="false"><v-icon color="white">mdi-arrow-right</v-icon></span></div> -->
                                <div class="btn-plus2"><span draggable="false"></span></div>
                                <div class="btn-plus3"><span draggable="false"><v-icon color="#E0E0E0" @click="info()">mdi-alert-circle-outline</v-icon></span></div>
                                <div class="btn-plus4"><span draggable="false"><v-rating
                                    v-model="mob.rating"
                                    background-color="orange lighten-3" small
                                    color="orange" large readonly></v-rating></span></div>                                
                                <v-card-title class="temp">
                                    <!-- title -->
                                    {{mob[0]}}
                                </v-card-title>
                                <div class="forLine4"></div>
                                <div class="card_text">
                                    <div class="nickname_txt">
                                        <!-- nickname -->
                                        {{ mob[3] }}
                                    </div>
                                    <div></div><div></div>
                                    <div v-show="path != ''" class="category_txt">
                                        {{ path }}
                                    </div>
                                    <div class="grade_txt">
                                        <!-- grade -->
                                        {{ mob[2] }}
                                    </div>
                                </div>
                                <div class="card_text2">
                                    <div>
                                        <!-- price -->
                                        ￦{{ mob[1]  | comma }}
                                    </div>
                                </div>
                            </v-card>
                        </div>
                        <v-container style="margin-top:20px;">
                        <div class="text-center">
                            <v-pagination class="btn_pagination" v-model="pageNum2" :length="pageCount2"></v-pagination>
                        </div>
                        </v-container>
                    </v-container>
                </div>
            </v-container>
            <!-- 모바일 리스트 -->
            <v-container class="lecture01 mr-9 hidden-md-and-up">
                <div v-show="!searchinOn">
                    <v-container class="lecture_box">
                        <div v-for="mob in paginatedData2" :key="mob.boardNo">
                            <div class="mx-auto2">
                                <div>
                                    <v-img :src="`http://localhost:7777/lecture/image/${mob[4]}/${mob[5]}`" height="200px" width="200px"></v-img>
                                </div>
                                <!-- <div class="btn-plus"><span draggable="false"><v-icon color="white">mdi-arrow-right</v-icon></span></div> -->
                                <div class="btn-plus2_m"><span draggable="false"></span></div>                                
                                <div class="card_info">    
                                    <div style="height:66px;"><!-- title -->
                                        {{mob[0]}}
                                    </div>
                                    <div class="forLine4"></div>
                                    <div class="card_text">
                                        <div class="nickname_txt">
                                            <!-- nickname -->
                                            {{ mob[3] }}
                                        </div>
                                        <div></div><div></div>
                                        <div v-show="path != ''" class="category_txt">
                                            {{ path }}
                                        </div>
                                        <div class="grade_txt">
                                            <!-- grade -->
                                            {{ mob[2] }}
                                        </div>
                                    </div>
                                    <div class="card_text2">
                                        <div>
                                            <!-- price -->
                                            ￦{{ mob[1]  | comma }}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <v-container style="margin-top:20px;">
                        <div class="text-center">
                            <v-pagination class="btn_pagination" v-model="pageNum2" :length="pageCount2"></v-pagination>
                        </div>
                        </v-container>
                    </v-container>
                </div>
            </v-container>
        </div>
    </div>
</template>
<script>

import { mapState, mapActions } from 'vuex'

export default {
    name: 'ForSaleLectureList',
    props: {
        callLecturelist: {
            type: Array
        },
        pageSize2: {
            type: Number,
            required: false,
            default: 6
        }
    },
    data: () => ({
        items: [
            {
            //     '개발 프로그래밍', '자바', '프론트엔드', '백엔드', 'Vue', 'React', 'Html Css', 'docker', 'JavaScript',
            //  '게임 개발', 'Golang', '데이터 사이언스', 'Python', '
            // 인공지능', '딥러닝', '데이터베이스', 'SQL', 'MongoDB', '보안', '모바일 앱 개발', 'Swift', '안드로이드', 'Kotlin', '코딩테스트', '기타'
                items: [
                    {
                        title: '4번 카테고리', value: 4
                    }
                ],
                title: '개발 프로그래밍'
            }, {
                items: [
                    {
                        title: '14번 카테고리', value: 14
                    }, {
                        title: '2번 카테고리', value: 2
                    }, {
                        title: '1번 카테고리', value: 1
                    }
                ],
                title: '게임 개발'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '프론트엔드'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '보안'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '딥러닝'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '인공지능'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '데이터베이스'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '코딩테스트'
            }, {
                items: [
                    {
                        title: 'List Item'
                    }
                ],
                title: '기타'
            }
        ],
        alert: true,
        show: false,
        pageNum2: 1,                
        pageNumS: 1,
        expanded: [],
        singleExpand: false,
        toggle_exclusive: [],
        searchingResult: [],
        searchinOn: false,
        word: '',
        headers: [
        ],
        valueDeterminate: 15,
        rating: 5,
        path: '',
    }),
    watch: {
        word(newVal) {
            if(newVal == '') {
                setTimeout(() => {
                    this.searchinOn = false
                    }, 200)
            }
        }
    },
    methods: {
        nextPage2() {
            this.pageNum2 += 1;
        },
        prevPage2() {
            this.pageNum2 -= 1;
        },
        nextPageS() {
            this.pageNumS += 1;
        },
        prevPageS() {
            this.pageNumS -= 1;
        },
        ImgRequest(boardNo) {
        try {
            return require(`../../../../../Mini/Images/lecture/${boardNo}.gif`
            )
        } catch (e) {
            return require(`@/assets/temp.png`)
            }
        },
        searching () {
            var lists = this.callLecturelist

            this.searchingResult = []
            for(var i = 0; i < lists.length; i++){
                if(lists[i].name.includes(this.word)){
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
        info() {
            alert('강의 소개 페이지로 링크')
        },
        selectCategory(data) {
            this.fetchCallLectureListWithCategory(data.value)
            this.path = data.title
        },
        callAll() {
            this.$emit("callAll", {})
        },
        searchingWord(data) {
            this.fetchCallLectureListWithFilter(data)
            this.path = ''            
        },
        ...mapActions(['fetchCallLectureListWithCategory']),
        ...mapActions(['fetchCallLectureListWithFilter'])
        
    },
    computed: {
        pageCount2() {
            let listLength = this.$store.state.callLecturelist.length, // 길이
                listSize = this.pageSize2,
                page = Math.floor(listLength / listSize);
            if (listLength % listSize > 0) 
                page += 1;
                return page;
        },
        paginatedData2() {
            // const start = (this.pageNum2 - 1) * this.pageSize2,
            //     end = start + this.pageSize2;
            console.log(this.$store.state.callLecturelist)
            return this.$store.state.callLecturelist.slice(0, 10);
        },
        ...mapState ({
        lists: state => state.lists
        })
    },
    filters : {
        comma(val){
            return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    }
}
    
</script>

<style scoped>
.main {
    display: flex;
    justify-content: start;
}
.sideBar {
    margin: 30px 30px 0px 10vw;
}
.mx-auto0.v-card.v-sheet.theme--light {
    width: 200px;
}
.v-list-item.theme--light {
    padding-left:30px!important;
    border-top: 1px solid #EEEEEE;
    background-color: white;
    font-weight: 500;
}
.v-list-group.v-list-group--no-action {
    background-color: #f8f8f8 !important;
}


.mx-auto:hover {
    transform: translate3d(0px, -1px, 0px);
    box-shadow: 10px 17px 40px 0 rgb(0 0 0 / 15%);
    transition: all 1s ease;
}
.data_table_box {
    text-align: center;
    width: 70vw;
}
.lecture01 {
    display: inline-block;
    margin: 0;
    padding: 0;
    width: 70vw;    
    min-width: 440px;
    max-width: 1000px;
}
.lecture_box {
    margin: 15px;
    padding: 0;
    width: 100vw;
    max-width: 900px;
    display: flex;
    flex-wrap: wrap;
    justify-content: start;
}
.btn_pagination {
    background-color: transparent;
    box-shadow: none;
}
p {
    margin: 0;
}
.v-btn.v-btn--text.theme--light.v-size--default {
    padding: 0;
}
.v-card__actions {
    padding: 4px;
}
.option_box {
    display: flex;
    justify-content: flex-end;
    width: 70vw;
    max-width: 1300px;
}
.searching_box_top {
    height: 50px;
    max-width: 1110px;
    display: flex;
    justify-content: space-between;
}
.searching_box {    
    height: 50px;
}
.searching_bar {
    display: flex;
    justify-content: row;
    height: 40px;
    width:70vw;
    max-width: 1300px;
    border: 1px solid #BDBDBD;
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
.title_box span {
    font-size: 25px;
    font-weight: bold;
}
.searching_message_box {
    width:70vw;
    height: 110px;
    max-width: 1300px;
    display:flex;
    justify-content: center;
}
.searching_message {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width:60vw;
    max-width: 900px;
    border-bottom: 1px solid #BDBDBD;
    margin-top: 15px;
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
.page_title {
    color: #424242;
}
.title {
    font-size: 15px;
    color: #0288D1;
    font-weight: 700;
}


.mx-auto {
  z-index: 0;
  width:inherit;
  height:inherit;
}
.mx-auto:hover .btn-plus {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus2 {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus3 {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus4 {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus_m {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus2_m {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus3_m {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus4_m {
  opacity:1;
  transform:scale(1);
}


.btn_pagination {
    background-color: transparent;
    box-shadow: none;
}
.nothing {
    width: 290px;
}
.nothing p {
    margin: 0px;
}
.item {
    margin: 10px;
    width: 250px;
    cursor: pointer;
}
.item_m {
    margin: 10px;
    width: 200px;
    cursor: pointer;
}
.btn-plus {
  position:absolute;
  top:77.5px;
  left:102.5px;
  background:rgb(65, 84, 192, 0.8);
  width:55px;
  height:55px;
  border-radius:50%;
  text-align:center;
  /* 추가된 부분 */
  opacity:0;
  transform:scale(2);
  transition:all .3s linear;
}
.btn-plus span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus2 {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:0.001px;
  background:rgba(0, 0, 0, 0.5);
  width:250px;
  height:200px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus2 span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus2_m {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:0.001px;
  background:rgba(0, 0, 0, 0.2);
  width:250px;
  height:200px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus2_m span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus3 {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:155px;
  width:470px;
  height:400px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus3 span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus4 {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:155px;
  width:160px;
  height:400px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus4 span {
  width: 10px;
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus_m {
  position:absolute;
  top:77.5px;
  left:77.5px;
  background:rgb(65, 84, 192, 0.8);
  width:55px;
  height:55px;
  border-radius:50%;
  text-align:center;
  /* 추가된 부분 */
  opacity:0;
  transform:scale(2);
  transition:all .3s linear;
}
.btn-plus_m span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus2_m {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:0.001px;
  background:rgba(0, 0, 0, 0.5);
  width:200px;
  height:200px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus2_m span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus3_m {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:155px;
  width:370px;
  height:400px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus3_m span {
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}
.btn-plus4_m {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:155px;
  width:160px;
  height:400px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
}.btn-plus4_m span {
  width: 10px;
  font-size:2.3em;
  color:#ffffff;
  user-select:none;
}


.tag_button {
    color: black;
    cursor: pointer;
}
.tag_button.on {
    color: #F9A825;
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
    height: 40px !important;
    padding-left: 2vw;
}
.forLine0sButton {
    display: flex;
    justify-content: start;
    align-items: center;
}
.forLine {
    height: 40px !important;
    border-top: 1px solid #BDBDBD;
    border-bottom: 1px solid #BDBDBD;
    padding-left: 2vw;
    display: flex;
    justify-content: start;
    align-items: center;
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
.main_box {
    justify-content: center;
    flex-direction: column;
    
    color: #424242;
    margin-top: 30px;
    max-width:900px;
}
.temp {
    font-size: 15px;
    font-weight: bold;
    color: #424242;
    padding: 5px 10px 5px 10px;
    margin-top: 10px;
    height: 50px;

    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    line-height: 25px;
    -webkit-line-clamp: 2; /* 표시하고자 하는 라인 수 */
    -webkit-box-orient: vertical;
}
.temp2 {
    font-size: 15px;
    padding: 3px;
}
.card_text {
    display: flex;
    justify-content: space-around;
    font-size:11px;
    font-weight: 00;
    color:#424242;
    margin:5px 5px 0px k5px;
    padding-bottom:5px;
    border-bottom:1px solid #ececec
}
.card_text2 {
    display: flex;
    justify-content: start;
    font-size: 18px;
    font-weight: 700;
    color:black;
    margin:5px 5px 0px 5px;
    padding-bottom:5px;
    padding-left: 5px;
    border-bottom:1px solid #ececec
}
.v-sheet.v-card:not(.v-sheet--outlined) {
    box-shadow: none !important;

}
.forLine4 {    
    margin:5px 5px 0px 5px;
    padding-bottom:5px;
    border-bottom:1px solid #ececec
}
.nickname_txt {
    margin-top: 5px;
    font-size: 13px;
    font-weight: bold;
    color: #757575;
}
.grade_txt {
    margin-top: 5px;
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
.category_txt {
    margin-top: 5px;
    background-color: #F9A825;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 20px;
    padding: 0 4px 0 4px;
    font-size: 11px;
    font-weight: bold;
}
.title {
    padding: 10px 16px !important;
    font-size: 12px !important;
    cursor: pointer;
    border: 1px solid #EEEEEE;
}
.title:hover {
    background-color: rgb(246, 246, 246);
}
.v-application .title {
    font-size: 1rem !important;
}
.v-list-item__title {
    font-size: 0.9rem;
}
.v-list-item__title.child {
    font-size: 0.8rem !important;
}
/* 사이드바 타이틀 */
.v-application .primary--text {
    color: black !important;
    font-weight: bold;
    caret-color: #BDBDBD !important;
}
.v-list-group.v-list-group--no-action {
    font-size: 5px !important;
    border-bottom: 1px solid #EEEEEE;
    border-left: 1px solid #EEEEEE;
    border-right: 1px solid #EEEEEE;
    background-color: white;
}
.mx-auto2 {
    display:flex;
    justify-content: start;
    flex-direction: row;

    width: 90vw;
    margin: 10px 0px;
}
.card_info {
    display:flex;
    justify-content: start;
    flex-direction: column;
    padding: 5px 5px;
    width: inherit;
    font-size: 15px;
    font-weight: bold;
    color: #424242;

}
.v-list-group__items {
    background-color: white;
}
</style>