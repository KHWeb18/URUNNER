<template>
    <div align="center">
        <manager-menu/>
        <manager-notice-read v-if="notice" :notice="notice"/>
        <p v-else>로딩중 ...... </p>

        <div class="버튼" style="padding:20px;">
            <router-link style="text-decoration: none; color:black" 
            :to="{ name: 'NoticeModifyPage', params: { noticeNo } }">
                <v-btn v-if="this.$cookies.get('ROLES') !='ROLE_USER'" text color="black" rounded x-large
                        style="padding: 10px; width: 90px;">
                    수정하기
                </v-btn>
            </router-link>


            <v-btn v-if="this.$cookies.get('ROLES') !='ROLE_USER'" text color="black" rounded x-large
                style="padding: 10px; width: 90px;" 
                @click="onDelete">
                    삭제하기
            </v-btn>

            <br>

            <router-link style="text-decoration: none; color:black"
            :to="{ name: 'ManagerNoticeListPage' }">
                <v-btn text color="black" rounded x-large
                        style="padding: 10px; width: 90px;">
                목록으로
                </v-btn>
            </router-link>
        </div>
    </div>
</template>
<script>
import ManagerMenu from '../../components/manager/ManagerMenu.vue'
import ManagerNoticeRead from '@/components/manager/ManagerNoticeRead.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'NoticeReadPage',
    props: {
        noticeNo: {
            type: String,
            required: true
        }
    },
    components: {
        ManagerNoticeRead,
        ManagerMenu,
    },
    computed: {
        ...mapState(['notice'])
    },
    created () {
        this.fetchNotice(this.noticeNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })
    },
    methods: {
        ...mapActions(['fetchNotice']),
        onDelete () {
            var result = confirm('삭제 하시겠습니까?')
            if(result) {
                const { noticeNo } = this.notice
                axios.delete(`http://localhost:7777/notice/${noticeNo}`)
                        .then(() => {
                            alert('삭제 되었습니다.')
                            this.$router.push({ name: 'ManagerNoticeListPage' })
                        })
                        //.catch(err => {
                        //    alert(err.response.data.message)
                        //})
            }
        }
    }
}
</script>