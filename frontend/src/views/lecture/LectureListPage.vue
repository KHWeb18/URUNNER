<template>
  <lecture-list-form></lecture-list-form>
</template>

<script>
import LectureListForm from '@/components/lecture/LectureListForm.vue'
import { getLectureList } from '@/util/AxiosMethod'

export default {
  components: {
    LectureListForm,
  },
  created() {
    getLectureList()
  },
}
</script>

<style>

</style>