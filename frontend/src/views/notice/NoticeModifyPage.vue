<template>
    <div align="center">
        <notice-modify-form v-if="notice" :notice="notice" @submit="onSubmit"/>
        <p v-else>로딩중 .......</p>
    </div>
</template>

<script>
import NoticeModifyForm from '@/components/notice/NoticeModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'NoticeModifyPage',
    components: {
        NoticeModifyForm
    },
    props: {
        noticeNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['notice'])
    },
    methods: {
        ...mapActions(['fetchNotice']),
        onSubmit (payload) {
            const { title, writer, content } = payload
            axios.put(`http://localhost:7777/notice/modifyNotice/${this.noticeNo}`, { title, writer, content })
                    .then(res => {
                        alert('수정 되었습니다.')
                        this.$router.push({
                            name: 'NoticeReadPage',
                            params: { noticeNo: res.data.noticeNo.toString() }
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    created () {
        this.fetchNotice(this.noticeNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
    }
}
</script>