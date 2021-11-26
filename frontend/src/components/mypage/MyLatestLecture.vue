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
            </v-spacer>
            <!-- 분류창 -->
            <v-spacer></v-spacer>
            <!-- 리스트 -->
            <v-container class="lecture01 mr-9 hidden-sm-and-down">
                <v-container class="lecture_box">
                    <div>
                        <b @click="goPage(callLatestLecture.lecture_id)">
                            <v-card class="mx-auto"
                            >
                                <v-img :src="`http://localhost:7777/lecture/image/${callLatestLecture.thumb_path}/${callLatestLecture.writer}`" max-height="200px" max-width="400"></v-img>
                                    <div class="btn-plus"><span draggable="false"><v-icon color="white">mdi-arrow-right</v-icon></span></div>
                                <div class="btn-plus2"><span draggable="false"></span></div>
                                <v-card-title class="temp">
                                    {{callLatestLecture.title}}
                                </v-card-title>
                                <div class="card_text">
                                    <div></div><div></div><div></div>
                                    <div>
                                        <!-- grade | writer -->
                                        {{ callLatestLecture.grade }} | {{ callLatestLecture.writer}}
                                    </div>
                                </div>
                                <v-card-actions>
                                </v-card-actions>
                            </v-card>
                        </b>
                    </div>
                </v-container>
            </v-container>
        </div>
    </div>
</template>

<script>
export default {
    name:'MyLatestLecture',
    props: {
        callLatestLecture: {
            type: Object
        },
    },
    data(){

    },
    methods:{
        goPage(data){
            this.$router.push( { name: 'LectureDetailPage', params: { lectureId: data.toString() } } )
        }
    }
}
</script>
<style scoped="scoped">
.forLine0 {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 40px;
    padding-left: 2vw;
    border-bottom: 1px solid #BDBDBD;
}
.title_box span {
    font-size: 25px;
    font-weight: bold;
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
.btn-plus {
  position:absolute;
  top:77.5px;
  left:163.5px;
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
.btn-plus2 {
  /* hover시 사진 어둡게 */
  position:absolute;
  top:0.001px;
  background:rgba(0, 0, 0, 0.5);
  width:400px;
  height:200px;
  text-align:center;
  border-radius: 6px 6px 0px 0px;
  cursor: pointer;
  /* 추가된 부분 */
  opacity:0;
  transition:all .6s linear;
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
.lecture01 {
    display: inline-block;
    margin: 0;
    padding: 0;
    width: 70vw;    
    min-width: 1280px;
    max-width: 1300px;
}
.mx-auto:hover {
    transform: translate3d(0px, -1px, 0px);
    box-shadow: 10px 17px 40px 0 rgb(0 0 0 / 15%);
    transition: all 1s ease;
}
.mx-auto:hover .btn-plus {
  opacity:1;
  transform:scale(1);
}
.mx-auto:hover .btn-plus2 {
  opacity:1;
  transform:scale(1);
}

</style>