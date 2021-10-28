<template>
  <div>
    <forgot-password-form @submit="onSubmit"/>
  </div>
</template>

<script>
import axios from 'axios'
import ForgotPasswordForm from '@/components/ForgotPasswordForm.vue'
export default {
  components: {
    ForgotPasswordForm
  },
  methods: {
    onSubmit (payload) {
      const { email, name } = payload
      axios.post('http://localhost:7777/memberManagement/findingpw', { email, name })
          .then(res => {
          if (res.data == 'success') {
          alert('고객님의 메일로 관련 링크를 전달 드렸습니다.')
          this.$router.push( { name: 'MainPage' } )
        } else if (res.data == 'NotFindUser' || res.data == 'NotMatchId') {
          alert('일치하는 회원정보가 없습니다.')
        } else {
          alert('이메일 전송이 불가능 합니다.')
        }
      }).catch(() => {
        alert('회원정보를 찾을 수 없습니다.')
      })
    }
  }
}
</script>
