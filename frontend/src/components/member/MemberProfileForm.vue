<template>
    <v-container class="box0">
        <form @submit.prevent="onSubmit">
            <v-container class="box0">
                <div class="register_box_content">
                    <!-- 당신의 userID : {{userId}}<br>
                    당신의 토큰 id : {{userIdInToken}}<br>
                    당신의 thumb_path : {{thumb_path}}<br>
                    당신의 memberNo : {{memberNo}} -->
                    <!-- 프로필 사진 -->
                    <div>                        
                        <v-avatar color="black" size="100" v-if="preview == ''">
                            <div>
                                <v-img :src="test00()" height="110px" width="110px"></v-img>
                            </div>
                        </v-avatar>
                    </div>                    
                    <label style="cursor:pointer" class="profile_change">프로필 변경
                        <v-file-input v-show="false" label="upload tumbnail image" id="thumb" @change="thumbFile" 
                        accept="image/png, image/jpeg, image/jpg" v-on:change="Filesubmit()"></v-file-input>
                    </label>  
                    <fieldset class="box1">
                        <!-- 닉네임 -->
                        <div class="box2">
                            <v-text-field class="int" v-model="nickname" 
                            placeholder="닉네임" maxlength="10" @input="searchChangeFunc4($event)"></v-text-field>
                            <span class="validation2" style="left: 85%">
                                <div v-show='toggle4' class="validation_with_length">
                                    <div class="count_nickname">
                                    {{ count_nickname }}/10
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
                            <div class="profile_row_title">자기 소개</div>
                        <editor-for-profile placeholder="Write something …" :introduceForChild="introduceForChild" @fromEditor="contentRegist"/>
                        
                        <div class="button_box" style="margin-bottom: 15px;">
                            <v-btn @click="certEmail" color="transparent" class="item" style="color: #29B6F6;">
                                이메일인증하기
                            </v-btn>
                            <v-dialog
                            v-model="certOpen"
                            width="400"
                            height="300"
                            >
                                <v-card>
                                    <v-card-title>
                                        인증번호
                                    </v-card-title>
                                    <v-card-text>
                                        <v-text-field
                                        label="인증번호"
                                        v-model="checkCode">
                                        </v-text-field>
                                        <v-btn
                                        class="mb-3 "
                                        @click="certCheck"
                                        >
                                            인증번호 확인
                                        </v-btn>
                                    </v-card-text>

                                </v-card>
                            </v-dialog>
                        </div>
                        
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
                        <div class="button_box" style="margin-bottom: 15px">
                            <router-link to="/">
                            <v-btn color="transparent" class="item" style="color: #29B6F6;">
                                취소
                            </v-btn>
                            </router-link>
                        </div>
                        
                    </fieldset>
                </div>
            </v-container>

        </form>

    </v-container>
</template>

<script>

import axios from 'axios'
import { mapState, mapActions } from 'vuex'
import Vue from 'vue'
import EditorForProfile from '@/components/board/EditorForProfile.vue'

export default {
    name: 'MemberProfileForm',
    components: {
        EditorForProfile
    },
    computed: {
        ...mapState(['profile'])
    },
    created() {
        this.nickname= Vue.$cookies.get("NICKNAME"),
        this.userId= Vue.$cookies.get("USER_NAME"),
        this.userIdInToken= Vue.$cookies.get("USER_NAME")
        this.introduce = this.$store.state.profile.introduce
    },
    mounted () {
        this.fetchMyIntroduce(this.userIdInToken)
        console.log('Vue.$cookies.get("NICKNAME") : ' + Vue.$cookies.get("NICKNAME"))
    },
    data () {
        return {
            name: '',
            nickname: Vue.$cookies.get("NICKNAME"),
            userId: Vue.$cookies.get("USER_NAME"),
            userIdInToken: Vue.$cookies.get("USER_NAME"),
            password: '',
            introduce: '',
            introduceForChild: this.$store.state.profile.introduce,
            memberNo: this.$store.state.profile.member_no,
            thumb_path: this.$store.state.profile.thumb_path,
            profileNo: this.$store.state.profile.profile_no,
            //파일전송용
            files: '',
            preview: '', 
            //닉네임 길이 체크용
            toggle4: false,
            count_nickname: 0,
            toggle2: false,
            toggle_friend2: false,
            toggle_friend_check2: false,
            toggle_friend2_1: false,
            toggle_friend_check2_1: false,
            check1: false,
            check2: false,
            check0: true, // 비밀번호를 한 번이라도 건드렸는지 확인
            onLoginBtn: false, // 제출 버튼 활성화용,

            thumbnailImage: "",
            show: null,
            refrechCheck: 1,

            //이메일 인증부분
            certOpen:false,
            checkCode:'',
        }
    },
    watch: {
        introduce() {            
            this.onLoginBtn = true
        }
    },
    methods: {
        test() {
            console.log('this.profile : ' + this.profile)
            console.log('this.$store.state.profile : ' + this.$store.state.profile)
            this.profile.email = 'test'
            console.log('this.thumb_path : ' + this.thumb_path)
        },
        test00() {
            var path = this.$store.state.profile.thumb_path
            var id = Vue.$cookies.get("USER_NAME")
                var temp = `http://localhost:7777/lecture/image/${path}/${id}`
                return temp
        },
        profileSubmit () {
                const { userIdInToken, nickname, password, introduce } = this
                this.$emit('submit', { userIdInToken, nickname, password, introduce })
        },
        deleteContent4 () {
            this.toggle4 = false
            this.check1 = false
            this.nickname = ''
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
                this.onLoginBtn = true
            }else {
                this.check02 = false
                this.onLoginBtn = false
            }
        },        
        searchChangeFunc4(){
            this.toggle4 = true
            if (this.nickname == '') {
                this.toggle4 = false;
                this.onLoginBtn = false;
            } else {
                this.onLoginBtn = true
            }
            this.count_nickname = this.nickname.length
        },
        Filesubmit () {
            const lectureId = this.memberNo
            const formData = new FormData();
            formData.append("thumbnailImage", this.thumbnailImage);
            formData.append("lectureId", lectureId);

            axios.delete(`http://localhost:7777/profile/upload/image/thumbnail/delete/${this.$store.state.profile.profile_no}`)

            axios.post("http://localhost:7777/profile/upload/image/thumbnail", formData,
            {
                headers: {
                "Content-Type": "multipart/form-data",
                },
            }
            )
            .then(() => {
                this.fetchMyIntroduce(this.userIdInToken)
                this.$router.push('/memberProfile')
                alert('프로필 사진 변경 완료')
                this.$router.go(this.$router.currentRoute)
            })
            .catch(err => {
            alert(err);
            })
        },
        // 파일 변경 시 이벤트 핸들러
        thumbFile(file) {
        this.thumbnailImage = file;
        },
        detailFile(file) {
        this.detailImage = file;
        },
        fileDeleteButton () {
            this.files = '',
            this.preview = ''
        },
        contentRegist(data) {
            console.log('받은 data값은')
            console.log(data.introduce)
            this.introduce = data.introduce
            console.log('this,content')
            console.log(this.introduce)

        },
        certEmail(){
            this.certOpen = !this.certOpen
            axios.get('http://localhost:7777/my-page/mailcert')
        },
        certCheck(){
            const {checkCode} = this
            axios.post('http://localhost:7777/my-page/mailcert',{checkCode})
            .then((res)=>{
                if(res.data == "success"){
                    alert("성공")
                }else{
                    alert("실패")
                }
            })
        },
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
    width: 270px;
}
.row {
    padding-left: 10.5px;
    margin: 0px;
    font-size: 24px;
    line-height: 34px;
    color: red;
    font-weight: normal;
}
.count_nickname {
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
    max-width: 400px;
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
    width: 300px;
}
.button_box {
    display: flex;
    justify-content: center;
    margin-top: 20px;
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
    font-size: 11px;
    color: #757575;
}
.box3 {
    border: 0px solid;
}
</style>