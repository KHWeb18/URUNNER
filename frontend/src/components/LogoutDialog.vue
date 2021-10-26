<template>
  <v-dialog v-model="isLogout" class="mx-auto" max-width="400">
   <v-card>
    <v-card-title>
      <div class="d-flex" id="logo">
          <v-icon color="black">mdi-run-fast</v-icon>
          <p class="d-inline text-h6 ma-0 text-decoration-underline">URUNNER</p>
      </div>
    </v-card-title>
      <v-card-text class="pb-0">
         <v-btn
          class="mb-3 "
          @click="logout"
        >
          로그아웃
        </v-btn>
        <v-spacer></v-spacer>
         <v-btn
          class="mb-3 "
          @click="cancel"
        >
          취소
        </v-btn>
        <v-divider class="my-3"></v-divider>
    </v-card-text>
    <v-card-text class="pb-0">
    </v-card-text>
    </v-card>
  </v-dialog>
</template>


<script>
import { logout } from '../util/APIUtil'
export default {
  props: {
    isLogout: {
      type: Boolean,
      required: true
    }
  },
   data: () => ({

    }),
    computed: {

    },
  methods: {
    cancel() {
      this.$emit("dialogOff2")
    },
    logout() {
      logout(),
      this.$emit("dialogOff2")
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
