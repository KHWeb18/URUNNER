<template>
  <v-container style="max-width: 500px;">
      <form style="margin-top: 3rem;">
        <h2>회원가입</h2>
        <v-text-field
          v-model="nickname"
          :error-messages="nicknameErrors"
          :counter="10"
          label="Name"
          required
          @input="$v.nickname.$touch()"
          @blur="$v.nickname.$touch()"
        ></v-text-field>
        <v-text-field
          v-model="email"
          :error-messages="emailErrors"
          label="E-mail"
          required
          @input="$v.email.$touch()"
          @blur="$v.email.$touch()"
        ></v-text-field>
        <v-text-field
          v-model="password"
          :error-messages="passwordErrors"
          label="Password"
          required
          type="password"
          @input="$v.password.$touch()"
          @blur="$v.password.$touch()"
        ></v-text-field>

        <v-text-field
          v-model="confirmPassword"
          :error-messages="passwordConfirmErrors"
          label="Password Confirm"
          required
          type="password"
          @input="$v.confirmPassword.$touch()"
          @blur="$v.confirmPassword.$touch()"
        ></v-text-field>

        <v-checkbox
          v-model="checkbox"
          :error-messages="checkboxErrors"
          label="Do you agree?"
          required
          @change="$v.checkbox.$touch()"
          @blur="$v.checkbox.$touch()"
        ></v-checkbox>

        <v-btn
          class="mb-3"
          @click="submit"
          fluid
        >
          가입하기
        </v-btn>

    </form>
  </v-container>
</template>

<script>
  import { validationMixin } from 'vuelidate'
  import { required, maxLength, email, minLength, sameAs } from 'vuelidate/lib/validators'
  import axios from 'axios'


  export default {
    mixins: [validationMixin],

    validations: {
      nickname: { required, maxLength: maxLength(10) },
      email: { required, email },
      password: {required, maxLength: maxLength(32), minLength: minLength(8)},
      confirmPassword: { sameAsPassword: sameAs('password') },
      checkbox: {
        checked (val) {
          return val
        },
      },
    },

    data: () => ({
      nickname: '',
      email: '',
      password: '',
      confirmPassword: '',
      checkbox: false,
    }),

    computed: {
      checkboxErrors () {
        const errors = []
        if (!this.$v.checkbox.$dirty) return errors
        !this.$v.checkbox.checked && errors.push('You must agree to continue!')
        return errors
      },
      nicknameErrors () {
        const errors = []
        if (!this.$v.nickname.$dirty) return errors
        !this.$v.nickname.maxLength && errors.push('Name must be at most 10 characters long')
        !this.$v.nickname.required && errors.push('Name is required.')
        return errors
      },
      emailErrors () {
        const errors = []
        if (!this.$v.email.$dirty) return errors
        !this.$v.email.email && errors.push('Must be valid e-mail')
        !this.$v.email.required && errors.push('E-mail is required')
        return errors
      },
      passwordErrors () {
        const errors = []
        if (!this.$v.password.$dirty) return errors
        !this.$v.password.maxLength && errors.push('Must be valid Password (8~32)')
        !this.$v.password.minLength && errors.push('Must be valid Password (8~32)')
        !this.$v.password.required && errors.push('Password is required')
        return errors
      },
      passwordConfirmErrors () {
        const errors = []
        if (!this.$v.password.$dirty) return errors
        !this.$v.confirmPassword.sameAsPassword && errors.push('password does not match confirm password')
        !this.$v.password.required && errors.push('Password is required')
        return errors
      },
    },

    methods: {
      submit () {
        this.$v.$touch()
        alert('submit')
        const{nickname,email,password} = this
        axios.post('http://localhost:7777/memberManagement/register-member',{nickname, email,password})
        .then(res =>{
          if(res.data == true){
            alert('가입성공!')
            this.$router.push({
              name: 'MainPage'            
              })
          }
          else{
            alert('이미 가입된 이메일입니다!')
            this.nickname = '',
            this.email = '',
            this.password = '',
            this. confirmPassword = ''
          }

        })
      },
    },
  }
</script>

<style scoped>

.social__button--Kakao {
    background-color: #fae500;
}
.social__button--Google {
    background-color: #f8f8f8;
}
.social__button {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 44px;
    height: 44px;
    border: 0;
    border-radius: 4px;
    box-shadow: 0 1px 2px 0 rgb(0 0 0 / 20%);
    cursor: pointer;
}
.social__signup-buttons {
    display: flex;
    -moz-column-gap: 12px;
    column-gap: 12px;
    margin: auto;
}

</style>>
