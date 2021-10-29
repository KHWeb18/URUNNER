<template>
  <v-container>
    <v-card class="pa-5">
      <v-card-title>비밀번호 재설정</v-card-title>
      <v-text-field label="비밀번호" v-model="password" type="password" :rules="pwRules"></v-text-field>
      <v-text-field label="비밀번호 확인" v-model="checkPassword" type="password" :rules="matchPwRules"></v-text-field>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn @click="changePW" @keydown.enter="changePW">확인</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>


<script>
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  data () {
    return {
      password: null,
      checkPassword: null,
      name: null,
      matchPwRules: [
        pw => !!pw || '비밀번호를 입력해주세요!',
        pw => pw === this.password || '비밀번호가 일치하지 않습니다!'
      ],
    }
  },
  computed: {
    ...mapState(['pwRules'])
  },
  created() {
    this.name = this.$route.query.name
    console.log(this.name)
  },
  methods: {
    changePW () {
      const password = this.checkPassword
      axios.patch(`http://localhost:7777/memberManagement/changePw/${this.name}`, { password }).then(() => {
        alert('비밀번호가 성공적으로 변경되었습니다. 다시 로그인 해주세요.')
        this.$router.push( { name: 'MainPage' } )
      })
    }
  }
}
</script>