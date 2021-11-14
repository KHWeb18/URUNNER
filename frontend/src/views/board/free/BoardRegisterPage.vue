<template>
    <div>
        <v-container>
        <h2 class="board_title">글쓰기</h2>
        <free-board-register-form/>
        </v-container>
    </div>
</template>

<script>
import FreeBoardRegisterForm from '@/components/board/free/FreeBoardRegisterForm.vue'

export default {
    name: 'FreeBoardRegisterPage',
    components: {
        FreeBoardRegisterForm
    }
}
</script>

<style scoped>
.board_title {
    font-size: 32px;
    font-weight: 700;
    line-height: 32px;
    color: #333d4b;
    margin-bottom: 60px;
}
</style>