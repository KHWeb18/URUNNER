<template>
    <div>
        <v-container>
        <h2 class="board_title">글쓰기</h2>
        <qna-board-register-form/>
        </v-container>
    </div>
</template>

<script>
import QnaBoardRegisterForm from '@/components/board/qna/QnABoardRegisterForm.vue'

export default {
    name: 'QnABoardRegisterPage',
    components: {
        QnaBoardRegisterForm
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