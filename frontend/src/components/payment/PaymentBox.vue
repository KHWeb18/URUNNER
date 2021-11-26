<template>
    <div>
      <v-dialog
        v-model="open"
        width="400"
        persistent
       >
            <v-card class="pa-3">
                <v-card-subtitle class="text-center">
                    결제수단 선택
                </v-card-subtitle>
                <v-divider></v-divider>
                <v-card-actions class="pa-1">
                    <v-btn color="warning" block
                    @click="onPaymentCard">
                        카드결제
                    </v-btn>
                </v-card-actions>
                <v-card-actinos class="pa-1">
                  <v-btn color="primary" block
                    @click="onPaymentToss">
                        토스결제
                  </v-btn>
                </v-card-actinos>
                <v-card-actinos class="pa-1">
                  <v-btn color="secondary" block
                    @click="cancel">
                        취소
                  </v-btn>
                </v-card-actinos>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import axios from 'axios'
import {createdPaymentRandomNumber} from '@/util/APIUtil.js'
import EventBus from '@/event'
//import Vue from 'vue'

export default {
    name:'PaymentBox',
    props:{
        lectureList:{
            type : Array,
            required : true
        },
        open:{
            type : Boolean,
            required : true
        },
        amount: {
          type: Number,
          required: true
        }
    },
    data(){
        return{
            clientKey:'test_ck_JQbgMGZzorzQGM6Nxm7Vl5E1em4d',
            orderName:'',
            orderId:'',
            customerName:'',
        }
    },
    created(){
        this.orderId = createdPaymentRandomNumber();
        this.customerName = this.$cookies.get("USER_NAME")
        EventBus.$on('getData', () => {
          this.openPayment();
        })
    },
    methods:{
        openPayment(){
            this.open = !this.open
            if(this.lectureList.length>1){
                this.orderName = this.lectureList[0] + " 외 "+(this.lectureList.length-1) +" 건"
            }
            else{
                this.orderName = this.lectureList[0];
            }
        },
        onPaymentCard(){ 
            const{lectureList,orderName,orderId,customerName,amount} =this    
            axios.post('http://localhost:7777/payment/pay-ready',{lectureList})
            const tossPayments = window.TossPayments(this.clientKey)
            tossPayments.requestPayment('카드', {
                amount: amount, 
                orderId: orderId,   
                orderName: orderName,    
                customerName: customerName, 
                successUrl: window.location.origin + '/payment/success/',
                failUrl: window.location.origin + '/payment/fail',
            })
            .catch(function(error){
              if(error.code === 'USER_CANCEL'){
                alert('결제취소!')
              }
            })
        },
        onPaymentToss(){
            const{lectureList,orderName,orderId,customerName,amount} =this   
            axios.post('http://localhost:7777/payment/pay-ready',{lectureList})
            const tossPayments = window.TossPayments(this.clientKey)
            tossPayments.requestPayment('토스결제', {
                amount: amount,
                orderId: orderId,
                orderName: orderName,
                customerName: customerName,
                successUrl: window.location.origin+'/payment/success',
                failUrl: window.location.origin+'/payment/fail',
            })
            .catch(function(error){
              if(error.code === 'USER_CANCEL'){
                alert('결제취소!')
              }
            })
        },
        cancel() {
          this.$emit('cancel')
        }
    }

}
</script>