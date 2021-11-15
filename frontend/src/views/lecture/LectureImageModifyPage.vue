<template>
 <div>
   <lecture-image-modify-form :lectureInfo="lectureInfo">
  </lecture-image-modify-form>
 </div> 
</template>

<script>
import LectureImageModifyForm from '@/components/lecture/LectureImageModifyForm.vue'
import { API_BASE_URL } from '@/constants/index'
import axios from 'axios'
export default {
  components:{
    LectureImageModifyForm
  },
  data() {
    return {
      lectureId: this.$route.params.lectureId,
      lectureInfo: {}
    }
  },
  computed: {
  },
  created() {
    this.getLectrueBasicInfo();
  },
  methods: {
    getLectrueBasicInfo() { // lecture table의 특정 id 값 가져오기 
      axios.get(`${API_BASE_URL}/lecture/getBasicInfo/${this.lectureId}`)
            .then(res => {
              this.$store.state.lectureBasicInfo = res.data.opData;
              this.lectureInfo = res.data.opData;
            })
            .catch(err => {console.log(err);})
    }
  },
}
</script>

<style>

</style>