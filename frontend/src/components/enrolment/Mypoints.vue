<template>
  <div class="font-weight-medium text-h5">
      현재 사용 가능한 포인트 {{ point }} 잎 
      <v-btn class="text-subtitle-1 font-weight-bold" plain>
          (<v-icon>mdi-help-circle-outline</v-icon>
          포인트 받는법)</v-btn>
  </div>
</template>

<script>
import { API_BASE_URL } from '@/constants/index'
import axios from 'axios'
export default {
    data:() => ({
        point: 0
    }),
    computed: {
        
    },
    methods: {
        getPoint() {
            axios.get(`${API_BASE_URL}/my-page/getPoint`)
            .then(res => {
                this.point = res.data
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        }
    },
    created() {
        this.getPoint()
    }

}
</script>

<style>

</style>