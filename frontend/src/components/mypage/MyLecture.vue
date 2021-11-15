<template>
    <div>
        <!-- 제목 -->
        <div class="title_box">
            <h4 class="page_title">
                <v-icon>mdi-food-apple</v-icon>
                <span>내 수강 리스트</span>
            </h4>
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
        <div class="searching_message_box" v-show="searchinOn">
            <div class="searching_message">
                <div><b>{{ word }}</b> 검색 결과</div>
                <div><p>&nbsp;&nbsp; <b>{{this.searchingResult.length}}</b>개 검색되었습니다.</p></div>
            </div>
        </div>
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
                            <v-card-title>
                                {{mob.boardNo}}. 
                                {{mob.title}}
                            </v-card-title>
                            <v-card-subtitle style="height:187px">
                                <br>
                                    {{ mob.introduce }}
                                <br>
                            </v-card-subtitle>
                            <v-card-actions>
                                <v-dialog width="600px">
                                    <template v-slot:activator="{ on }">
                                        <p>
                                            <v-btn style="font-weight:bold; font-size:15px; color:#039BE5;" 
                                            text="text">수강완료</v-btn></p>
                                        <p v-show="checkAFFO(mob) == '수강중'">
                                            <v-btn v-bind="attrs" v-on="on" style="font-weight:bold; font-size:15px; color:#DD2C00;" 
                                            text="text">{{checkAFFO(mob)}}</v-btn></p>
                                    </template>
                                    <v-card>
                                        <v-card-title>
                                            <span class="text-h5">AAFCO 인증이란 무엇인가요?</span>
                                        </v-card-title>
                                        <v-card-text>
                                            zzzz<br>
                                        </v-card-text>
                                    </v-card>
                                </v-dialog>
                                <v-dialog width="600px">
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn v-bind="attrs" v-on="on" color="purple" text="text">
                                            {{mob.nickname}}</v-btn>
                                    </template>
                                    <v-card>
                                        <v-card-title>
                                            <span class="text-h5">zz</span>
                                        </v-card-title>
                                        <v-card-text>
                                            zzzzzzzzzz
                                        </v-card-text>
                                    </v-card>
                                </v-dialog>
                                <v-spacer></v-spacer>
                                <v-btn icon="icon" @click="show = !show">
                                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon></v-btn>
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
        <br>        
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
                ]
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
                return require(`@/assets/logo.png`)
                }
            },
            checkAFFO(mob) {
                if (mob.protein <= 29) {
                    return '미달'
                } else if (mob.fat <= 10) {
                    return '미달'
                } else if (mob.mineral < 2.6) {
                    return '미달'
                } else {
                    return '인증'
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
    transform: translate3d(0px, -10px, 0px);
    box-shadow: 10px 17px 40px 0 rgb(0 0 0 / 45%);
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
    width: 70vw;
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
    height: 38px !important; 
    width:60vw !important;
    max-width: 955px;
    border-style: none !important;
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
</style>