import axios from "axios";
import { 
    API_BASE_URL,
} from '../constants/index'

function joinLecturer() {
    // 구상한 lecturer 테이블 (id, String description, String tags)
    // 별도로 lecturer table에 정보를 담아두면 나주에 좋을거같아서 몇가지 정보 보냅니다.
    // 그냥 형식적인거라 그냥 string으로 db에 저장만 해주세요~
    axios.get(`${API_BASE_URL}/manageLecture/joinLecturer`)
    .then(res => res)
  }

  function lecturerModifyInfo() {
    axios.post(`${API_BASE_URL}/manageLecture/lecturerModifyInfo`, {
        description: this.description,
        tags: this.tags
      })
  }

  function addToCart(lectureId) {
    // * 해당 강의를 장바구니 테이블에 추가하는 메서드
    // !! 참고 !! 강의를 뿌려주는 메인banner 메서드랑 상세페이지 detailLectureInfo 메서드에서 로그인 없이도 접근이 가능한 페이지들이기 때문에
    // 서버에서 카트에 담겨있는지 유무를 판단할 때 로그인 정보가 없으면 false를 같이 보내주세요! 있으면  확인해서 true or false 필요한 boolean 값 (cart, wish)
    axios.post(`${API_BASE_URL}/manageLecture/addToCart/${lectureId}`)
    .then(res => res)
  }

  function removeToCart(lectureId) {
    // * 해당 강의를 장바구니 테이블에서 제거하는 메서드
    axios.post(`${API_BASE_URL}/manageLecture/removeToCart/${lectureId}`)
  }

  function addToWish(lectureId) {
    // * 해당 강의를 위시 테이블에 추가하는 메서드
    axios.post(`${API_BASE_URL}/manageLecture/addToWish/${lectureId}`)
  }

  function removeToWish(lectureId) {
    // * 해당 강의를 위시 테이블에서 제거하는 메서드
    axios.post(`${API_BASE_URL}/manageLecture/removeToWish/${lectureId}`)
  }

  function getCartList() {
    // 유저의 cart 리스트와 해당 강의별 lecture table 정보 (다는 필요없고 id, title, price, thumbPath 정도인데 다 주셔도 상관 x)
    axios.getLectrueBasicInfo(`${API_BASE_URL}/manageLecture/getCartList`)
  }

  function getWishList() {
    // 유저의 wish 리스트와 해당 강의별 lecture table 정보 (lecture table 모든정보)
    axios.get(`${API_BASE_URL}/manageLecture/getWishList`)
  }

  function mainCartList() {
    // 메인 로고에 뿌릴 것 5개까지만 불러오게
    // 유저의 cart 리스트와 해당 강의별 lecture table 정보 (다는 필요없고 id, title, price, thumbPath 정도인데 다 주셔도 상관 x)
    axios.post(`${API_BASE_URL}/manageLecture/mainCartList`)
  }
  
  function mainWishList() {
    // 메인 로고에 뿌릴 것 5개까지만 불러오게
    // 유저의 wish 리스트와 해당 강의별 lecture table 정보 (id, title, price, thumbPath)
    axios.post(`${API_BASE_URL}/manageLecture/mainWishList`)
  }

export {
    mainCartList,
    mainWishList,
    getWishList,
    joinLecturer,
    addToCart,
    removeToCart,
    addToWish,
    removeToWish,
    getCartList,
    lecturerModifyInfo

}