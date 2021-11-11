import axios from "axios";
import { 
    API_BASE_URL,
} from '../constants/index'
import state from '../store/states'

function getLectrueBasicInfo(lectureId) { // lecture table의 특정 id 값 가져오기 
  axios.get(`${API_BASE_URL}/lecture/getBasicInfo/${lectureId}`) //get으로 수정
        .then(res => {
          console.log(res.data)
          state.lectureBasicInfo = res.data;
        })
        .catch(err => {console.log(err);})
}

function deleteThumbImg(lectureId) {
  axios.delete(`${API_BASE_URL}/lecture/deleteThumbImg/${lectureId}`)
        .then(() => {
         // lecture table에서 thumbPath 값 삭제 
        })
        .catch(err => {console.log(err);})
}

function deleteDetailImg(lectureId) {
  axios.delete(`${API_BASE_URL}/lecture/deleteDetailImg/${lectureId}`)
        .then(() => {
         // lecture table에서 DetailPath 값 삭제 
        })
        .catch(err => {console.log(err);})
}

function deleteLecture(lectureId) { 
  // lecture table의 lectureId값과 연관 되어 있는 모든 테이블의 필드를 삭제해야함  lecture_list / lecture_video
  axios.delete(`${API_BASE_URL}/lecture/deleteLecture/${lectureId}`)
        .then(() => {
        })
        .catch(err => {console.log(err);})
}

function modifyLecture() {
  // const lectureInfo = {
  //   id: "",
  //   title:"",
  //   price:"",
  //   desc: "",
  // } 
  // 지금은 임의로 한곳에 나열하기 위함임 이렇게 데이터를 보낼 것이라는 의미
  axios.put(`${API_BASE_URL}/lecture/modifyLecture`, 
   {  

      id: "",
      title: "",
      price: "",
      desc: "", 
    }
  ).then(() => {})
  .catch(err => {console.log(err);})
}

function getSectionTopic(lectureId) {
  axios.get(`${API_BASE_URL}/lecture/getSectionTopic/${lectureId}`)
  .then((res) => {
    console.log(res.data)
    state.sectionTopic = res.data
  })
  .catch(err => {console.log(err);})
}

function deleteSection(lectureListId) {
   // lecture_list table의 lecture_list_id 값 삭제
   axios.delete(`${API_BASE_URL}/lecture/deleteSection/${lectureListId}`)
   .then(() => {
   })
   .catch(err => {console.log(err);})
}

function modifySectionTopic() {
   // 지금은 임의로 한곳에 나열하기 위함임 이렇게 데이터를 보낼 것이라는 의미
  axios.put(`${API_BASE_URL}/lecture/modifySectionTopic`, 
   { 

     lectureList_id: "",
     topic: ""
   }
  )
  .then(() => {
  })
  .catch(err => {console.log(err);})
}

function getLectureVideoInfo(id) {
  axios.get(`${API_BASE_URL}/lecture/getLectureVideoInfo/${id}`)
  .then((res) => {
    console.log(res.data)
    state.lectureVideoInfo = res.data
  })
  .catch(err => {console.log(err);})
}

function deleteLectureVideo(id) { //lecture_video table의 해당 id 삭제 
  axios.delete(`${API_BASE_URL}/lecture/deleteLectureVideo/${id}`)
  .then(() => {
  })
  .catch(err => {console.log(err);})
}

function modifyLectureVideo() {
  // 이 부분은 등록이 videoPath upload가 함께 연결되어 있어서 기존 등록 메서드에다가 append로 (id) 값을 추가해서 
  // 해당 아이디 값의 필드 수정이 가능할지..
}


export {
  getLectrueBasicInfo,
  deleteThumbImg,
  deleteDetailImg,
  getSectionTopic,
  deleteSection,
  modifySectionTopic,
  deleteLecture,
  modifyLecture,
  getLectureVideoInfo,
  deleteLectureVideo,
  modifyLectureVideo    
}
