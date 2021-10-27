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
      const { email } = payload
      axios.post('http://locahost:7777/memberManagement/send', { email })
          .then(res => {
            alert('고객님의 이메일로 임시 비밀번호를 발송했습니다!' + res)
            //axios.post('http://locahost:7777/memberManagement/check/send', data)
            //console.log(res)
            })
          .catch(res => {
            alert(res.response.data.message)
            alert('잘못된 접근! 로그인부터 해주세요')
                    })
        }
    }
}
</script>
