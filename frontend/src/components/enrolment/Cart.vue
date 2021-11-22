<template>
<v-container>
    <v-card flat>
  <v-card 
    class="font-weight-bold text-h6 mt-10 ml-10"
    flat
    >
      수강 바구니
  </v-card>
  <v-row>
      <v-col cols="auto" class="ml-10">
          <v-card flat class="font-weight-bold">
          내쿠폰함 
           <v-icon class="mb-1">
              mdi-ticket-percent-outline
          </v-icon>
           <v-icon class="mb-1">
              mdi-greater-than
          </v-icon>
          </v-card>
         
      </v-col>
      <v-col class="mr-10" cols="auto">
          <v-card flat  class="font-weight-bold">
          내 위시리스트
           <v-icon class="mb-1">
              mdi-cards-heart-outline
          </v-icon>
            <v-icon class="mb-1">
              mdi-greater-than
          </v-icon>
          </v-card>
      </v-col>
      <v-spacer/>
      <v-spacer/>
      <v-col cols="auto" class="ml-15">
          <v-card flat>
          포인트 적립
          <v-icon class="mb-1">
              mdi-piggy-bank-outline
          </v-icon>
          </v-card>
      </v-col>
      <v-col>
          <v-card flat>
          쿠폰이용 안내
            <v-icon class="mb-1">
              mdi-help-circle-outline
          </v-icon>
          </v-card>
      </v-col>
  </v-row>
  <v-row justify="end" no-gutters class="mt-5">
      <v-col cols="3">
        <v-text-field
        v-model="coupon"
        label="보유한 쿠폰코드를 입력하세요"
        color="black"
      ></v-text-field>
      </v-col>
      <v-col class="mt-3 mr-10" cols="auto">
          <v-btn color="teal accent-4" dark>쿠폰</v-btn>
      </v-col>
  </v-row>
  <div v-if="cartList.length  == 0">
  <v-row justify="center" align="center" class="justify-center" style="height:100vh">
        <v-card flat width="350" height="600" class="justify-center font-weight-bold" align="center" >
            <div class="justify-center" align="center">
                <v-icon size="100">mdi-cart-outline</v-icon>
            </div>
        <!-- <v-icon>mdi-cart-outline</v-icon><br> -->
            바구니에 담긴 강의가 없습니다.<br>
            나를 성장시켜 줄 좋은 지식들을 찾아보세요.
        <v-btn class="mr-5 ma-2 font-weight-bold" dark color="teal accent-4" to="/">
            강의 리스트 보기
        </v-btn>
         <v-btn color="light-blue darken-1" dark class="font-weight-bold" to="/wishlist">
            내 위시 리스트 보기
        </v-btn>
      </v-card>
  </v-row>
  </div>
  <div v-else>
    <v-row>
      <v-col cols="12" md="4">
        <v-container>
          <v-card class="mx-auto" shaped>
            <v-card-title class="d-flex justify-space-between">
              <h2 class="secondary--text">총계</h2>
              <h2 class="secondary--text">
                ₩ {{ getCurrencyFormat(lectureInfo.totalPrice) }}
              </h2>
            </v-card-title>
              <div> 
                <payment-box v-bind:lectureInfo="lectureInfo"></payment-box>
              </div>
              <!--<v-card-actions class="pa-3">
              <v-btn class="primary font-weight-bold" x-large block>결제하기</v-btn>
            </v-card-actions>-->
          </v-card>
        </v-container>
      </v-col>
      <v-col cols="12" md="8" class="order-md-first">
        <v-list style="width: 100%">
          <v-list-item-group color="primary">
            <v-list-item v-for="(cart) in cartList" :key="cart.id" class="mb-2" :to="`/course/${cart.id}`">
              <v-img :src="`http://localhost:7777/lecture/image/${cart.thumbPath}/${cart.writer}`" max-height="100" max-width="150"/>
              <v-list-item-content class="ml-2">
                <v-list-item-title><h4>{{ cart.title }}</h4></v-list-item-title>
                <v-list-item-subtitle v-text="getCurrencyFormat(cart.price)" class="text-right pa-3"/>
              </v-list-item-content>              
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-col>
    </v-row>
  </div>
    </v-card>
</v-container>
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
import PaymentBox from '../../components/payment/PaymentBox.vue'
export default {
  
    components:{
        PaymentBox
    },
    data: () => ({
        lecture: null,
        cartList: [],
        lectureInfo:{
                lectureList:[],
                totalPrice: 0,    
            }
    }),
    created() {
      this.getCartList()
    },
    /*
    mounted(){
      this.cartList.filter(c=>{
        this.lectureInfo.lectureList.push(c.title)
        alert(this.lectureInfo.lectureList)
      })

    },*/
    methods: {
      getCartList() {
        axios.get(`${API_BASE_URL}/manageLecture/getCartList`)
              .then(({data}) => {
                this.cartList = data.data
              }).then(() => {
                this.cartList.filter(c => {
                  this.lectureInfo.totalPrice += c.price
                  this.lectureInfo.lectureList.push(c.title)
                })
              }) 
      },
       getCurrencyFormat(value) {
      return this.$currencyFormat(value);
    },
    },
}
</script>

<style>
</style>