import axios from "axios";
import Vue from "vue";
import EventBus from "../event";
import { 
    // API_BASE_URL,
    API_URL_LOGIN, 
    SAVE_COOKIE_ACCESS, 
    SAVE_COOKIE_REFRESH,
    USER_NAME, 
    ROLES, 
    REFRESH_TOKEN, 
    ACCESS_TOKEN,
    BEARER,
    NAME,
    NICKNAME
} from '../constants/index'
import { Base64 } from 'js-base64';

function createdPaymentRandomNumber(){
    var result = ''
    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < 12; i++ ) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    
    return result;
}

// 로그인 프로세스
function loginProcess(loginfo) {

    axios.post(API_URL_LOGIN, loginfo)
    .then(res => {
        
        let token = res.data
         
        EventBus.$emit('isLogin', "isLogin");
        PasingInfor(token)
        
    }).catch(err => {
        alert(err)
    })
}

// Jwt payload부분을 base64 디코딩한부분
function PasingInfor(giveMeToken) {
    
    const accessToken = giveMeToken.access_token.split(".")

     let base64Encoded = Base64.decode(accessToken[1])

     let result = JSON.parse(base64Encoded.toString())
     let username = result.sub

//  배열형태로 있어서 가져올때 주의해주세요
//  ROLE_USER, ROLE_MANAGER 여러개 있을수 있습니다.
     let roles = result.roles

     let name = result.name

     console.log(name)

     let nickname = result.nickname

     Vue.$cookies.set(USER_NAME, username, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(ROLES, roles, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(NAME, name, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(NICKNAME, nickname, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(ACCESS_TOKEN, BEARER + giveMeToken.access_token, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(REFRESH_TOKEN, BEARER + giveMeToken.refresh_token, SAVE_COOKIE_REFRESH)
     
}


// 로그아웃
function logout() {
    Vue.$cookies.remove(ACCESS_TOKEN)
    Vue.$cookies.remove(REFRESH_TOKEN)
    Vue.$cookies.remove(ROLES)
    Vue.$cookies.remove(USER_NAME)
    Vue.$cookies.remove(NAME)
    Vue.$cookies.remove(NICKNAME)
    sessionStorage.clear();
    localStorage.clear();

    EventBus.$emit('isLogin', null);
  }

// 토큰 재발급요청
// function refreshToken() {
//     // let exp = payload.includes("The Token has expired")
//     alert(Vue.$cookies.get("REFRESH_TOKEN"))

//     axios.get(API_BASE_URL)
//     .then(res => {
//         alert("재발급중")
//         PasingInfor(res.data)
//     })
//     .catch(err => {
//         alert("재발급오류")
//         alert(err)
//     })
  
// }



export {
    loginProcess,
    PasingInfor,
    logout,
    createdPaymentRandomNumber
    // refreshToken
}
