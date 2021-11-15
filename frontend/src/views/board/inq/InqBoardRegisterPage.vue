<template>
    <div>
        <v-container>
        <h2 class="board_title">글쓰기</h2>
        <inq-board-register-form/>
        </v-container>
    </div>
</template>

<script>
import InqBoardRegisterForm from '@/components/board/inq/InqBoardRegisterForm.vue'

export default {
    name: 'QnABoardRegisterPage',
    components: {
        InqBoardRegisterForm
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