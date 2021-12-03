<template>
    <div style="display:flex;justify-content:center;margin:0px;">     
        <v-container v-if="isAdmin('user')" style="padding:0px;">
            <inq-board-for-user-list :boards="boards"/>
        </v-container>
        <v-container v-if="isAdmin('admin')" style="padding:0px;">
            <inq-board-list :boards="boards"/>
        </v-container>
        <!-- 모바일 사이즈 때 나타나는 글쓰기 버튼 -->
        <router-link :to="{ name: 'InqBoardRegisterPage' }">
            <v-btn fab dark color="primary" fixed right class="hidden-md-and-up" style="top:80vh;">
                <v-icon dark>mdi-plus</v-icon>
            </v-btn>
        </router-link>
    </div>
</template>

<script>

import InqBoardForUserList from '@/components/board/inq/InqBoardForUserList.vue'
import InqBoardList from '@/components/board/inq/InqBoardList.vue'
import { mapState, mapActions } from 'vuex'
import Vue from 'vue'

export default {
    name: 'InqBoardListForUserPage',    
    data() {
        return {
            role: Vue.$cookies.get("ROLES")
        }
    },
    components: {
        InqBoardForUserList,
        InqBoardList
    },
    computed: {
        ...mapState(['boards'])
    },
    mounted () {
        if (this.$cookies.get('ROLES').includes("ROLE_MANAGER")) {
            this.fetchInqBoardList()
        } else {
            this.fetchInqBoardForUserList(Vue.$cookies.get("USER_NAME"))
        }
    },
    methods: {
        isAdmin(data) {
            var dda = this.$cookies.get('ROLES')
            console.log('elt : ' + dda)
            switch(data) {
                case 'user':
                    if (this.$cookies.get('ROLES').includes("ROLE_MANAGER")) {
                        return false
                    } else {
                        return true
                    }
                case 'admin':
                    if (this.$cookies.get('ROLES').includes("ROLE_MANAGER")) {
                        return true
                    } else {
                        return false
                    }
                default:
                    break;                   
            }
        },
        ...mapActions(['fetchInqBoardList']),
        ...mapActions(['fetchInqBoardForUserList'])
        
    }
}
</script>