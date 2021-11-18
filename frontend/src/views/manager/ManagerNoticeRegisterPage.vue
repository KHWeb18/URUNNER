<template>
    <div>
        <manager-menu/>
        <notice-register-page/>
    </div>
</template>

<script>
import ManagerMenu from '../../components/manager/ManagerMenu.vue'
import NoticeRegisterPage from '../../views/notice/NoticeRegisterPage.vue'

export default {
    components: {ManagerMenu, NoticeRegisterPage}
    
}
</script>