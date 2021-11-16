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
          <v-col cols="3">
            <v-card
              color="#385F73"
              dark
            >
              <v-card-title class="text-h5-bold">
                이메일 인증하기
              </v-card-title>

              <v-card-text> {{email}} </v-card-text>

              <v-card-actions>
                <v-btn text @click="certfiEmail">
                    인증하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="3">
            <v-card
              color="#385F73"
              dark
            >
              <v-card-title class="text-h5-bold">
                관리자 등급 업그레이드
              </v-card-title>
              <v-card-text> {{manager}} </v-card-text>
              <v-card-actions>
                <v-btn text @click="getManagerAuth">
                    요청
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
    </div>
</template>
<script>

import axios from 'axios'
import { logout } from '@/util/APIUtil'

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
    },
    methods:{
      certfiEmail(){
        axios.get('http://localhost:7777/my-page/mailcert')
        .then()
        alert('이메일 보내기완료!')
      },
      getManagerAuth(){
        this.email = this.$cookies.get('USER_NAME')
          //console.log(this.name)
          if(this.$cookies.get('ROLES') =="ROLE_USER"){axios.patch(`http://localhost:7777/memberManagement/getManager/${this.email}`)
          .then( () =>{
                    alert('관리자 등급을받았습니다. 다시로그인해주세요')
                    logout()
                    this.$router.push({name: 'MainPage'})
                })
            }
            else{
                alert("이미관리자등급입니다.")
            }
        }     
  },
}
</script>