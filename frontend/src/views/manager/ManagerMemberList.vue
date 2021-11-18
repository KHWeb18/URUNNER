<template>
    <div align=center>
        <manager-menu/>
        <h3 style="padding:30px 0px 0px 0px; font-weight:bold">회원 정보</h3>
        <div class="회원목록">
        <manager-member-list-form :memberLists="memberLists"/>
        </div>
    </div>
</template>

<script>
import {mapState,mapActions} from 'vuex'
import ManagerMenu from '@/components/manager/ManagerMenu.vue'
import ManagerMemberListForm from '@/components/manager/ManagerMemberListForm.vue'

export default {
    name:'ManagerMemberList',
  components: { ManagerMenu, ManagerMemberListForm },

  computed: {
      ...mapState(['memberLists'])
  },
  methods: {
      ...mapActions(['fetchMemberList'])
  },
  mounted() {
      this.fetchMemberList()
  }
    
}
</script>

<style scoped>

.회원목록 {
  padding:0px 50px 0px 50px;
}
</style>