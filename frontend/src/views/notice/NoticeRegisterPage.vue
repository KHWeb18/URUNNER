<template>
    <div align="center">
        <notice-register-form @submit="onSubmit"/>
    </div>
</template>

<script>

import NoticeRegisterForm from '@/components/notice/NoticeRegisterForm.vue'
import axios from 'axios'

export default {
    
    name: 'NoticeRegisterPage',
    components: {
        NoticeRegisterForm
    },
    methods: {
        onSubmit (payload) {
            const { title, content, writer, regDate } = payload
            axios.post('http://localhost:7777/notice/register', { title, writer, content, regDate })
                    .then(() => {
                        alert('등록되었습니다.')

                        this.$router.push({ name: 'NoticeListPage' })
                    })
                    // .catch(res => {
                    //     alert(res.response.data.message)
                    // })
        }
    }
}
</script>