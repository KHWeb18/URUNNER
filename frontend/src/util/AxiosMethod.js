import axios from "axios";
import { 
    API_BASE_URL,
} from '../constants/index'
import state from '../store/states'

function getLectureList() {
    axios({
        method:'get',
        url: API_BASE_URL + "/lecture/getLectureList",
        data: {
        }
    })
    .then(res => {
        state.getLectureList = res.data 
        console.log(JSON.stringify(res.data))
    })
    .catch(err => {
        alert(err)
    })
}

function getImage(path) {
    axios({
        method:'get',
        url: API_BASE_URL + "/lecture/image/" + path,
        data: {
        }
    })
    .then(res => {
        state.getLectureList = res.data 
        console.log(JSON.stringify(res.data))
    })
    .catch(err => {
        alert(err)
    })
}

export {
    getLectureList,
    getImage
}