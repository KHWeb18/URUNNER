<template>
    <div>
        <v-row justify="center">
          <v-col cols="3">
            <v-card
              color="#385F73"
              dark
            >
              <v-card-title class="text-h5-bold">
                최근 학습중인 강의
              </v-card-title>
  
              <v-card-text> {{latestLecture}} </v-card-text>
  
              <v-card-actions>
                <v-btn text>
                    내강의 전체보기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        <v-col cols="3">
            <v-card
              color="#385F73"
              dark
            >
              <v-card-title class="text-h5">
                최근 내 노트
              </v-card-title>
  
              <v-card-text> {{latestNote}} </v-card-text>
  
              <v-card-actions>
                <v-btn text>
                    <!--페이지 완성 후 URL연결-->
                  내노트 전체보기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
    </div>
</template>
<script>

import axios from 'axios'

export default {
    name:'MyPageBox',
    data(){
        return{
            latestNote:'',
            latestLecture:'',
        }
    },
    mounted(){
      alert('email '+this.$cookies.get('USER_NAME'))
        axios.get('http://localhost:7777/my-page')
        .then(res=>{
            this.latestLecture = res.data.latestLecture
            this.latestNote = res.data.latestNote
            
        })
    }
    


}
</script>