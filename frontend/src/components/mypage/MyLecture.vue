<template>
    <div class="main">
        <div class="main_box">
            <!-- 제목 -->
            <div class="mr-9 hidden-sm-and-down">
                <div class="title_box">
                    <h2 class="page_title">
                        <h3>{{ isAuth }}</h3>
                        <span>질문답변 게시판</span></h2>
                </div>
            </div>
            <!-- 검색창 + complete 분류 -->
            <v-spacer class="forLine0">
                <div class="forLine0sButton">
                    <div class="tag_button" :class="{ on2 : completeSelect1 }" @click="fetchQnABoardList(),
                                        completeSelect1 = true, completeSelect2 = false, completeSelect3 = false, word = '' ">전체</div>&nbsp;&nbsp;&nbsp;
                    <div class="tag_button" :class="{ on2 : completeSelect2 }"  @click="selectComplete('false'), word = ''">수강중</div>&nbsp;&nbsp;&nbsp;
                    <div class="tag_button" :class="{ on2 : completeSelect3 }" @click="selectComplete('true'), word = ''">수강완료</div>&nbsp;&nbsp;&nbsp;
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
            <!-- 리스트 -->
            <v-container class="lecture01">
                <div v-show="!searchinOn">
                    <v-container class="lecture_box">
                        <div v-for="mob in paginatedData2" :key="mob.boardNo" class="item">
                            <v-card class="mx-auto">
                                <v-img :src="ImgRequest(mob.boardNo)" height="200px"></v-img>
                                <div class="btn-plus"><span draggable="false"><v-icon color="white">mdi-arrow-right</v-icon></span></div>
                                <v-dialog width="700px">
                                    <template v-slot:activator="{ on }">
                                        <div class="btn-plus2" v-on="on"><span draggable="false"></span></div>
                                    </template>
                                    <v-card>
                                        <v-img :src="ImgRequest(mob.boardNo)"></v-img>
                                    </v-card>
                                </v-dialog>
                                <v-card-title class="temp">
                                    {{mob.title}}
                                </v-card-title>
                                 <v-card-title class="temp2">
                                    <v-progress-linear
                                        v-model="valueDeterminate" color="indigo darken-2"></v-progress-linear>
                                    </v-card-title>
                                    <div style="font-size:12px;color:#424242;padding-left:5px;">
                                    전체 진도율 : 15%
                                    </div>

                                <v-card-actions>
                                    <v-dialog width="600px">
                                        <template v-slot:activator="{ on, attrs }">
                                            <v-btn v-bind="attrs" v-on="on" color="#424242" text="text">
                                                {{mob.grade}}</v-btn>
                                        </template>
                                        <v-card>
                                            <v-card-title>
                                                <span class="text-h5">Grade 구분이 뭔데</span>
                                            </v-card-title>
                                            <v-card-text>
                                                대충 정보
                                            </v-card-text>
                                        </v-card>
                                    </v-dialog>
                                    <v-dialog width="600px">
                                        <template v-slot:activator="{ on, attrs }">
                                            <v-btn v-bind="attrs" v-on="on" color="#424242" text="text">
                                                {{mob.nickname}}</v-btn>
                                        </template>
                                        <v-card>
                                            <v-card-title>
                                                <span class="text-h5">ㅇㅇㅋ</span>
                                            </v-card-title>
                                            <v-card-text>
                                                강의자 소개? 혹은 여유되면 강의자가 만든 강의 목록 페이지로 가게 할까
                                            </v-card-text>
                                        </v-card>
                                    </v-dialog>
                                    <v-spacer></v-spacer>
                                </v-card-actions>                            
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
        </div>
    </div>
</template>

<script>
    export default {
        name: 'MyLecture',
        props: {
            myLecturelist: {
                type: Array
            },
            pageSize2: {
                type: Number,
                required: false,
                default: 10
            }
        },
        data() {
            return {
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
                valueDeterminate: 15
            }
        },
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
                var lists = this.myLecturelist

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
            }
            
        },
        computed: {
            pageCount2() {
                let listLength = this.$store.state.myLecturelist.length, // 길이
                    listSize = this.pageSize2,
                    page = Math.floor(listLength / listSize);
                if (listLength % listSize > 0) 
                    page += 1;
                    return page;
            },
            paginatedData2() {
                // const start = (this.pageNum2 - 1) * this.pageSize2,
                //     end = start + this.pageSize2;
                return this.$store.state.myLecturelist.slice(0, 10);
            },
        }
    }
</script>

<style scoped="scoped" > 
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
    min-width: 1280px;
    max-width: 1300px;
}
.lecture_box {
    position: relative;
    margin: 0;
    padding: 0;
    width: 100vw;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
.item {
    margin: 10px;
    width: 300px;
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
    width: 300px;
}
.btn-plus {
  position:absolute;
  top:77.5px;
  left:122.5px;
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
  background:rgba(0, 0, 0, 0.2);
  width:300px;
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
    display: flex;
    justify-content: center;
    flex-direction: column;
    
    color: #424242;
}
.temp {
    font-size: 15px;
    font-weight: bold;
    color: #424242;
    padding: 5px 10px 5px 10px;

    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    line-height: 30px;
    -webkit-line-clamp: 1; /* 표시하고자 하는 라인 수 */
    -webkit-box-orient: vertical;
}
.temp2 {
    font-size: 15px;
    padding: 3px;
}
</style>