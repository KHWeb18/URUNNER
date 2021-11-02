<template>
    <div>
        <v-container>
        <h2 class="board_title">글쓰기</h2>
        <study-board-register-form/>
        </v-container>
    </div>
</template>

<script>
import StudyBoardRegisterForm from '@/components/board/study/StudyBoardRegisterForm.vue'

export default {
    name: 'StudyBoardRegisterPage',
    components: {
        StudyBoardRegisterForm
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