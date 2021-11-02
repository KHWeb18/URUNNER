<template>
 <v-container>
    <h2>강의 커리큘럼 관리</h2>
    <hr>
    <v-row>
      <v-col cols="3">
        <add-section-form @goToUploadLecture="goToUploadLecture"></add-section-form>
      </v-col>
      <v-col cols="9">
        <upload-lecture :sectionId="sectionId"></upload-lecture>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AddSectionForm from '@/components/lecture/AddSectionForm'
import UploadLecture from '@/components/lecture/UploadLecture'
export default {
  components: {
    AddSectionForm,
    UploadLecture
  },
  data() {
    return {
      sectionId: null,
    }
  },
  methods: {
    goToUploadLecture(id) {
       this.sectionId = id;
    }
  }
}
</script>

<style>

</style>