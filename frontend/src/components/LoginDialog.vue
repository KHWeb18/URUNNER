<template>
  <v-dialog v-model="isLogin" class="mx-auto" max-width="400">
   <v-card>
    <v-card-title>
      <div class="d-flex" id="logo">
          <v-icon color="black">mdi-run-fast</v-icon>
          <p class="d-inline text-h6 ma-0 text-decoration-underline">URUNNER</p>
      </div>
    </v-card-title>
    <v-card-text class="pb-0">
        <v-text-field
          label="E-mail"
          required
          v-model="email"
        ></v-text-field>
        <v-text-field
          label="Password"
          required
          type="password"
          v-model="password"
        >
        </v-text-field>
         <v-btn
          class="mb-3 "
          @click="submit"
        >
          로그인
        </v-btn>
        <p class="forgot-password text-right">
          <router-link to="forgot">Forgot password?</router-link>
        </p>
        <v-divider class="my-3"></v-divider>
        <p class="text-center">간편 로그인</p>
    </v-card-text>
    <v-card-actions class="pb-3">
        <div class="social__signup-buttons">
         <button class="social__button social__button--Kakao e-social-sign-up" data-provider="Kakao" data-link="https://kauth.kakao.com/oauth/authorize?response_type=code&amp;client_id=0de1184063abadc9cef9dbf417c567a5&amp;redirect_uri=https%3A%2F%2Fwww.inflearn.com%2Fauth%2Fkakao&amp;scope=profile%2Caccount_email%2Cbirthday%2Cbirthyear%2Cphone_number%2Cage_range%2Cgender">
          <svg width="18px" xmlns="http://www.w3.org/2000/svg" height="17" viewBox="0 0 18 17"><g transform="translate(0.000000,17.000000) scale(0.100000,-0.100000)" stroke="none"><path fill="#212529" d="M38 154 c-15 -8 -30 -25 -34 -38 -6 -26 10 -66 27 -66 7 0 9 -10 5 -26 -7 -25 -6 -25 16 -10 12 9 31 16 41 16 29 0 75 28 82 50 10 31 -3 59 -35 75 -36 19 -67 18 -102 -1z"></path></g></svg>
         </button>
         <button class="social__button social__button--Google e-social-sign-up" data-provider="Google" data-link="https://accounts.google.com/o/oauth2/v2/auth?access_type=offline&amp;prompt=consent&amp;scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&amp;response_type=code&amp;client_id=887875630717-ror9t8ig4obhvokdij07eoochpqbu5kf.apps.googleusercontent.com&amp;redirect_uri=https%3A%2F%2Fwww.inflearn.com%2Fauth%2Fgoogle">
           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="none" viewBox="0 0 18 18"><path fill="#4285F4" d="M17.785 9.169c0-.738-.06-1.276-.189-1.834h-8.42v3.328h4.942c-.1.828-.638 2.073-1.834 2.91l-.016.112 2.662 2.063.185.018c1.694-1.565 2.67-3.867 2.67-6.597z"></path><path fill="#34A853" d="M9.175 17.938c2.422 0 4.455-.797 5.94-2.172l-2.83-2.193c-.758.528-1.774.897-3.11.897-2.372 0-4.385-1.564-5.102-3.727l-.105.01-2.769 2.142-.036.1c1.475 2.93 4.504 4.943 8.012 4.943z"></path><path fill="#FBBC05" d="M4.073 10.743c-.19-.558-.3-1.156-.3-1.774 0-.618.11-1.216.29-1.774l-.005-.119L1.254 4.9l-.091.044C.555 6.159.206 7.524.206 8.969c0 1.445.349 2.81.957 4.026l2.91-2.252z"></path><path fill="#EB4335" d="M9.175 3.468c1.684 0 2.82.728 3.468 1.335l2.531-2.471C13.62.887 11.598 0 9.175 0 5.667 0 2.638 2.013 1.163 4.943l2.9 2.252c.727-2.162 2.74-3.727 5.112-3.727z"></path></svg>
         </button>
        </div>
    </v-card-actions>
  </v-card>
  </v-dialog>
</template>


<script>
import { loginProcess } from '../util/APIUtil'
// import Vue from "vue";

export default {
  name:'LoginDialog',
  props: {
    isLogin: {
      type: Boolean,
      required: true
    }
  },
   data: () => ({
      email: '',
      password: '',
    }),
    computed: {
      

    },
  methods: {
      submit () {
        this.$emit("dialogOff")
        loginProcess({ email: this.email, password:this.password})

      }
  }
       
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

body {
  width: 100vw;
  height: 100vh;
}
</style>>
