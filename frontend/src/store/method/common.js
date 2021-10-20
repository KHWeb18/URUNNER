import axios from "axios";

function loginProcess(loginfo) {
    alert("test")
    axios.post("http://localhost:8080/api/login", loginfo)
    .then(res => {
        alert(JSON.stringify(res.data))
        let token = res.data
        this.$cookies.set("tokens",token, 10)
        
    }).catch(err => {
        alert(err.data)
    })
}

export {
    loginProcess
}
