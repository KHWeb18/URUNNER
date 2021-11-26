<template>
    <div class="main">
        <div class="main_box">
            <!-- 제목 -->
            <div class="mr-9 hidden-sm-and-down">
                <div class="title_box">
                    <h2 class="page_title">
                        <span>최근학습</span></h2>
                </div>
            </div>
            <!-- 검색창 + complete 분류 -->
            <v-spacer class="forLine0">
                <div class="forLine0sButton">
                    <div class="tag_button" @click="copiedOrignList = callLecturelist, word = ''">전체</div>&nbsp;&nbsp;&nbsp;
                </div>
                <div class="searching_box_top">
                    <div class="mr-9">
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
            <v-spacer></v-spacer>
            <!-- 리스트 -->
            <v-container class="lecture01">
                <v-container class="lecture_box">                 
                    <div v-for="mob in originList" :key="mob.boardNo" class="item_m">
                        <b @click="goPage(mob[5])">
                            <v-card class="mx-auto">
                                <v-img :src="`http://localhost:7777/lecture/image/${mob[3]}/${mob[4]}`" height="200px"></v-img>
                                <div class="btn-plus_m"><span draggable="false"><v-icon color="white">mdi-arrow-right</v-icon></span></div>
                                <div class="btn-plus2_m"><span draggable="false"></span></div>
                                <v-card-title class="temp">
                                    {{mob[0]}}
                                </v-card-title>
                                <v-card-title class="temp2">
                                    <v-progress-linear
                                        v-model="valueDeterminate" color="indigo darken-2"></v-progress-linear>
                                </v-card-title>
                                <div class="card_text">
                                    <div>
                                        전체 진도율 : 15% |
                                    </div>
                                    <div></div><div></div><div></div>
                                    <div>
                                        {{ mob[4] }} | {{ mob[2] }}
                                    </div>
                                </div>
                                <v-card-actions>
                                </v-card-actions>
                            </v-card>
                        </b>
                    </div>
                    <v-container style="margin-top:20px;">
                    <div class="text-center">
                        <v-pagination class="btn_pagination" v-model="pageNum" :length="pageCount"></v-pagination>
                    </div>
                    </v-container>
                </v-container>
            </v-container>
        </div>
    </div>
</template>

<script>
export default {
    name:'MyLatestLecture',
    props: {
        callLecturelist: {
            type: Array
        },
        pageSize: {
            type: Number,
            required: false,
            default: 6
        }
    },
    data(){
        return {
            pageNum: 1,
            searchingResult: [],
            word: '',
            lectureList: this.callLecturelist,
            valueDeterminate: 15,
            originList: null,
            test: 1,
            test2: null
        }
    },
    created() {
        setTimeout(() => {
            this.originList = this.copiedOrignList
        },100)
    },
    computed: {
        pageCount() {
            let listLength = this.lectureList.length, // 길이
                listSize = this.pageSize,
                page = Math.floor(listLength / listSize);
            if (listLength % listSize > 0) 
                page += 1;
                return page;
        },
        paginatedData() {
            return this.lectureList.slice(0, 10);
        },
        copiedOrignList: {
            get () {
                console.log('get')
                return this.callLecturelist
            },   
            set (data) {
                console.log('set')
                this.originList = data
            }
        }
    },
    watch: {
        word(newVal) {
            if(newVal == '') {
                this.copiedOrignList = this.callLecturelist
            }
        }
    },
    methods:{
        goPage(data){
            this.$router.push( { name: 'LectureDetailPage', params: { lectureId: data.toString() } } )
        },
        searching () {
            var lists = this.copiedOrignList // 가공용 리스트 새로 생성
            this.searchingResult = [] // 검색결과 초기화

            for(var i = 0; i < lists.length; i++){
              const regex = new RegExp(this.word, "gi")
              const comparison = regex.test(lists[i][0])
              console.log(comparison)
              if(comparison){
                this.searchingResult.push(lists[i])
              }
            }
            this.copiedOrignList = this.searchingResult
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
    margin: 5px;
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
    width: 212px;
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
  width:212px;
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
    border-bottom: 1px solid #BDBDBD;
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
.card_text {
    display: flex;
    justify-content: space-around;
    flex-direction: column;
    font-size:11px;
    font-weight: 00;
    color:#424242;
    margin:5px 5px 0px 5px;
    padding-bottom:5px;
    border-bottom:1px solid #ececec
}
.card_text2 {
    font-size:12px;
    font-weight: 500;
    color:#424242;
    margin:0px 0px;
    padding:0 0 10px 0;
}
.v-sheet.v-card:not(.v-sheet--outlined) {
    box-shadow: 0 1px 1px -1px rgb(0 0 0 / 10%), 0 2px 2px 0 rgb(0 0 0 / 4%), 0 1px 5px 0 rgb(0 0 0 / 6%) !important;

}
</style>