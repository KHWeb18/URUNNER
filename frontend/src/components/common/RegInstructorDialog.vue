<template>
  <v-dialog v-model="openRegDialog" persistent>
    <v-stepper v-model="e1">
      <v-stepper-header>
        <v-stepper-step
          :complete="e1 > 1"
          step="1"
        >
          Lets get started!
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e1 > 2"
          step="2"
        >
          Describe your lecture
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step step="3">
          Tag #
        </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card
            class="mb-10 text-center font-weight-bold py-5 px-1"
            color="primary white--text"
            height="200px"
          >
         <div>
            <h2 class="mb-7">지금 시작하세요.</h2>
            <p>유러너와 함께라면 단계별로 쉽고 간편하게 지식을 나누고 운영할 수 있습니다.</p>
            <p>지식으로 경제적 보상과 명예를 모두 가져보세요.</p>
         </div>
          
          </v-card>

          <v-btn
            color="primary"
            @click="e1 = 2"
          >
            Continue
          </v-btn>

          <v-btn text @click="closeDialog">
            Cancel
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card
            class="mb-10 text-center font-weight-bold py-5 px-1"
            color="primary white--text"
            height="200px"
          >
            <v-textarea
            v-model="desc"
             outlined
             color="white"
             class="ma-3"
             name="input-7-4"
             label="describe your lecture"
            ></v-textarea>
          </v-card>

          <v-btn
            color="primary"
            @click="e1 = 3"
          >
            Continue
          </v-btn>

          <v-btn text @click="closeDialog">
            Cancel
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card
            class="mb-10 text-center font-weight-bold py-5 px-1"
            color="primary white--text"
            height="200px"
          >
            <v-text-field class="ma-4" outlined color="white" label="# tag about lecture" v-model="tag"></v-text-field>
          </v-card>

          <v-btn
            color="primary"
            @click="joinLecturer"
          >
            Continue
          </v-btn>

          <v-btn text @click="closeDialog">
            Cancel
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-dialog>  
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
export default {
  props: {
    openRegDialog: {
      type: Boolean,
      require: true,
    }
  },
  data() {
    return {
      e1: 1,
      tag: '',
      desc: '',
    }
  },
  methods: {
    closeDialog() {
      this.$emit('closeDialog')
      this.refreshData();
    },
    joinLecturer() {
      axios.post(`${API_BASE_URL}/manageLecture/joinLecturer`, {
        tags: this.tag,
        descriiption: this.desc
      })
      .then((res) => {
        if(res.data) {
          alert('강사 등록이 완료되었습니다!')
          this.$emit('closeDialog')
          this.$emit('logout')
          this.refreshData();
          this.$router.push('/')
          this.$emit('openLoginDialog')
        }
      })
    },
    refreshData() {
      this.tag = '',
      this.desc = '',
      this.e1 = 1
    }
  },

}
</script>

<style>

</style>