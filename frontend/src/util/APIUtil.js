import axios from "axios";
import Vue from "vue";
import { 
    // API_BASE_URL,
    API_URL_LOGIN, 
    SAVE_COOKIE_ACCESS, 
    SAVE_COOKIE_REFRESH,
    USER_NAME, 
    ROLES, 
    REFRESH_TOKEN, 
    ACCESS_TOKEN,
    BEARER 
} from '../constants/index'

// 로그인 프로세스
function loginProcess(loginfo) {

    axios.post(API_URL_LOGIN, loginfo)
    .then(res => {
        
        let token = res.data
        PasingInfor(token)
        
    }).catch(err => {
        alert(err)
    })
}

// Jwt payload부분을 base64 디코딩한부분
function PasingInfor(giveMeToken) {

    const accessToken = giveMeToken.access_token.split(".")

     let base64Encoded = atob(accessToken[1])

     let result = JSON.parse(base64Encoded.toString())
     let username = result.sub

//  배열형태로 있어서 가져올때 주의해주세요
//  ROLE_USER, ROLE_MANAGER 여러개 있을수 있습니다.
     let roles = result.roles

     Vue.$cookies.set(USER_NAME, username, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(ROLES, roles, SAVE_COOKIE_ACCESS)

     Vue.$cookies.set(ACCESS_TOKEN, BEARER + giveMeToken.access_token, SAVE_COOKIE_ACCESS)
     Vue.$cookies.set(REFRESH_TOKEN, BEARER + giveMeToken.refresh_token, SAVE_COOKIE_REFRESH)
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
    PasingInfor
    // refreshToken
}
