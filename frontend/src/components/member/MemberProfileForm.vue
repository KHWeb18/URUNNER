<template>
    <v-container class="box0">
        <form @submit.prevent="onSubmit">
            <v-container class="box0">
                <div class="register_box_content">
                    <!-- 프로필 사진 -->
                    <div style="text-slign:center;">
                        
                    <v-avatar color="black" size="100" v-if="preview == ''">
                        <span class="white--text text-h5"><img :src="ImgRequest()" style="width:110px;height:110px;object-fit: cover"></span>
                    </v-avatar>
                    <v-avatar color="black" size="100" v-if="preview !== ''">
                        <span class="white--text text-h5"><img :src="preview" style="width:110px;height:110px;object-fit: cover"></span>
                    </v-avatar><br>
                    <label style="cursor:pointer" class="profile_change">프로필 변경
                    <input v-show="false"
                        type="file"
                        class="files"
                        id="files"
                        ref="files"
                        multiple="multiple"
                        v-on:change="handleFileUpload()"
                        style="pointer:cursor">
                    </label>
                    </div>
                <fieldset class="box1">
                    <!-- 닉네임 -->
                    <div class="box2">
                        <v-text-field class="int" v-model="name" 
                        placeholder="닉네임" maxlength="10" @input="searchChangeFunc4($event)"></v-text-field>
                        <span class="validation2" style="left: 85%">
                            <div v-show='toggle4' class="validation_with_length">
                                <div class="count_name">
                                {{ count_name }}/10
                                </div>
                                <div>
                                <v-icon class="delete_all_btn" @click="deleteContent4" tabindex="-1">mdi-close-circle-outline</v-icon>
                                </div>
                            </div>
                        </span>
                        <div class="profile_row_title">닉네임 변경</div>
                    </div>
                    <!-- 비밀번호 -->
                    <div class="box2" style="margin-bottom:10px">
                        <v-text-field type="password" class="int" v-model="password" placeholder="변경하실 비밀번호를 입력해주세요" 
                            maxlength="32" @input="searchChangeFunc2($event)"
                            ></v-text-field>
                        <span class="validation">
                            <v-icon v-show='toggle2' class="delete_all_btn" @click="deleteContent2" tabindex="-1">mdi-close-circle-outline</v-icon>
                        </span>                        
                    </div>
                    <span class="guide_messgage2">
                        <div v-show='toggle_friend2' class="error_line">
                            <div class="error_message">✘ 8자 이상 입력</div>                            
                        </div>
                        <div v-show='toggle_friend_check2' class="error_line_none">
                            <div class="pass_message" style="top:30px">✔ 8자 이상 입력</div>
                        </div>
                        <div v-show='toggle_friend2_1' class="error_line" style="top:-10px">    
                            <div class="error_message" style="top:30px">✘ 영문/숫자/특수문자만 허용하며, 특수문자를 포함하여 입력</div>
                        </div>
                        <div v-show='toggle_friend_check2_1' class="error_line_none" style="top:-147px">    
                            <div class="pass_message" style="top:170px">✔ 영문/숫자/특수문자만 허용하며, 특수문자를 포함하여 입력</div>
                        </div>
                    </span>
                    <!-- 자기소개 -->
                    <fieldset class="box3">
                        <textarea style="height:140px;width:300px;border:1px solid;" cols="80" rows="20" maxlength="155" v-model="introduce" placeholder="자기소개(160자 이내)"></textarea>
                    </fieldset>                    
                    
                    <div class="button_box" style="margin-bottom: 15px">
                        <v-btn v-show="onLoginBtn"
                        color="light-blue lighten-1 text center" @click="profileSubmit()" class="item" >
                            변경
                        </v-btn>
                        <v-btn  v-show="!onLoginBtn"
                        disabled 
                        depressed
                        color="light-blue lighten-1 text center" @click="profileSubmit()" class="item" >
                            변경
                        </v-btn>
                    </div>
                    <div class="button_box" style="margin-top: 0px;">
                        <router-link to="/">
                        <v-btn color="transparent" class="item" style="color: #29B6F6;">
                            취소
                        </v-btn>
                        </router-link>
                    </div>
                    <!-- <div class="button_box" style="margin-top: 0px;">
                        <v-btn color="transparent" class="item" @click="test()" style="color: #29B6F6;">
                            test
                        </v-btn>
                    </div> -->
                </fieldset>
            </div>
            </v-container>
        </form>
    </v-container>
</template>

<script>

import axios from 'axios'
import { mapActions } from 'vuex'

export default {
    name: 'MemberProfileForm',
    data () {
        return {
            name: this.$store.state.name,
            userId: this.$store.state.email,
            password: '',
            introduce: this.$store.state.introduce,
            //파일전송용
            files: '',
            preview: '', 
            //닉네임 길이 체크용
            toggle4: false,
            count_name: 0,
            toggle2: false,
            toggle_friend2: false,
            toggle_friend_check2: false,
            toggle_friend2_1: false,
            toggle_friend_check2_1: false,
            check1: false,
            check2: false,
            check0: true, // 비밀번호를 한 번이라도 건드렸는지 확인
            onLoginBtn: false, // 제출 버튼 활성화용,
            
        }
    },
    created () {
        this.fetchMyIntroduce(this.userId) 
        setTimeout(() => {
            this.introduce = this.$store.state.introduce // data 갱신용 왜 fetch로 값 받아오면 갱신이 안 되네
            }, 100)
    },
    methods: {
        // test() {
        //     console.log(this.$store.state.introduce)
        //     this.introduce = this.$store.state.introduce
        // },
        profileSubmit () {
                const { userId, name, password, introduce} = this
                this.$emit('submit', { userId, name, password, introduce })
        },
        deleteContent4 () {
            this.toggle4 = false
            this.check1 = false
            this.name = ''
            this.onLoginBtn = false
        },
        deleteContent2 () {
            this.toggle2 = false
            this.password = ''
            this.toggle_friend2 = true
            this.toggle_friend_check2 = false
            this.toggle_friend2_1 = true
            this.toggle_friend_check2_1 = false
        },
        searchChangeFunc2(){
            this.check0 = false
            this.toggle2 = true            
            this.toggle_friend2 = true
            this.toggle_friend2_1 = true
            if (this.password == '') {
                this.toggle2 = false;
            }
            var checkPassword = this.password,
            exp = /[~!@#$%^&*()_+|<>?:{}]/;
            var resultCheckPassword= exp.test(checkPassword);
            console.log(resultCheckPassword)
            if (checkPassword.length >= 8) {
                this.toggle_friend2 = false
                this.toggle_friend_check2 = true                
            }else {
                this.toggle_friend_check2 = false
            }
            
            if (resultCheckPassword) {
                this.toggle_friend2_1 = false
                this.toggle_friend_check2_1 = true
            }else {
                this.toggle_friend_check2_1 = false
            }
            if (this.toggle_friend_check2 & this.toggle_friend_check2_1 == true) {
                this.check02 = true
                console.log('두번째 체크도 통과')
                this.onLoginBtn = true
            }else {
                this.check02 = false
                this.onLoginBtn = false
            }
        },        
        searchChangeFunc4(){
            this.toggle4 = true
            if (this.name == '') {
                this.toggle4 = false;
                this.onLoginBtn = false;
            } else {
                this.onLoginBtn = true
            }
            this.count_name = this.name.length
            console.log(this.name)
        },
        handleFileUpload () {
                this.files = this.$refs.files.files
                this.preview = URL.createObjectURL(this.files[0])
                this.Filesubmit()
        },
        Filesubmit () {
            let formData = new FormData()
            for (var idx = 0; idx < this.files.length; idx++) {
                formData.append('fileList', this.files[idx])                
            }
            let ownerId = this.$store.state.email
            formData.append('id', ownerId)
            axios.post('http://localhost:7777/image/uploadImg_Profile', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
                
            })
            .then (res => {
                this.response = res.data
            })
            .catch (res => {
                this.response = res.message
            }) 
            alert('프로필 변경 완료')
        },
        fileDeleteButton () {
            this.files = '',
            this.preview = ''
        },
        ImgRequest() {
            try {
                var cutId = this.userId.substring(0, this.userId.length-4); // email 뒤 .com 삭제
                console.log(cutId)
                return require(`../../../../backend/khweb/images/profiles/${cutId}.gif`)
            } catch (e) {
                return require(`@/assets/logo.png`)
            }
        },
        // fusion () {
        //     setTimeout(() => {
        //         this.Filesubmit()
        //         }, 1000)
        //     this.profileSubmit()
        // }
        ...mapActions(['fetchMyIntroduce'])
    }
}
</script> 

<style scoped>
.guide_messgage {
    display: block;
    position: relative;
    top: -30px;
    padding: 10px 0 9px;
    font-size: 12px;
    color: #666;
    line-height: 18px;
    word-break: break-all;
    letter-spacing: -.1px;
    clear: both;
}
.guide_messgage2 {
    display: block;
    position: relative;
    top: -10px;
    padding: 10px 0 9px;
    margin-left: 50px;
    margin-bottom: 50px;
    font-size: 12px;
    color: #666;
    line-height: 18px;
    word-break: break-all;
    letter-spacing: -.1px;
    clear: both;
    width: 310px;
}
.row {
    padding-left: 10.5px;
    margin: 0px;
    font-size: 24px;
    line-height: 34px;
    color: red;
    font-weight: normal;
}
.count_name {
    display: inline;
    min-width: 37px;
    text-align: right;
    margin-top: 3px;
    color: #b6b6b6;
    cursor: auto;
}
.register_box_content {
    display: flex;
    justify-content: center;    
    flex-direction: column;
    padding: 20px;
    padding-top: 30px;
}
.box0 {
    text-align: center;
    display: flex;
    justify-content: center;
    padding: 0px;
}
.box1 {
    border: 0px;
}
.box2 {
    height: 43px;
    width: 100%;
    margin: 0px;
    margin-bottom: 30px;
    padding-left: 50px;
    padding-right: 50px;
}
.int {
    display: block; 
    letter-spacing: 2px;
    text-align: center;
}
.validation {
    position: relative;
    top: -50px; left: 46%;
    cursor: pointer;
    height: 2px;
}
.validation2 {
    position: relative;
    top: -45px;
    cursor: pointer;
    height: 2px;
}
.validation_with_length {
    position: absolute;
    right: 109px;
    bottom: -21px;
    width: 65px;
    display: flex;
    justify-content: space-between;
}
.delete_all_btn {
    display: inline-block;
    visibility:inherit;
}
.delete_all_btn2 {
    display: inline-block;
    visibility:inherit;
}
.item {
    font: 12pt;
    color: white;
    font-weight: 800;
    width: 310px;
}
.button_box {
    display: flex;
    width: 310px;
    margin: 50px;
    margin-top: 50px;
    margin-bottom: 30px;
    justify-content:center;
}
.error_message {
    position:relative;
    top: 10px;
    color: rgba(214, 3, 3, 0.856);
    font-size: 8pt;
    font-weight: 500;
}
.pass_message {
    position: relative;
    color: #3CA03C;
    font-size: 8pt;
    font-weight: 500;
}
.error_line {
    position: relative; 
    height: 2px;
    top:-10px;
    border-top: 2px rgba(214, 3, 3, 0.856) solid;
}
.error_line_none {
    position: relative; 
    height: 2px;
    top:-27px;
    border-top: 2px transparent;
}
.profile_row_title {
    position: relative;
    top:-18px;
    color: #757575;
    font-size: 12px;
}
ul {
    list-style:none;
}
a {
    text-decoration: none;
}
.profile_change {
    font-size: 9px;
    color: #757575;
}
.box3 {
    border: 0px solid;
}
</style>