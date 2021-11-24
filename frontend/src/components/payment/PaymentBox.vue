<template>
    <div>
        <v-btn
        width="200"
        height="50"
        @click="openPayment"
        color="#1dc078"
        dark>
            결제선택
        </v-btn>

        <v-dialog
        v-model="open"
        width="400"
        >
            <v-card>
                <v-card-title>
                    결제수단 선택
                </v-card-title>
                <v-divider class="mx-5"></v-divider>
                <v-card-actions>
                    <v-btn
                    width="180"
                    height="100"
                    @click="onPaymentCard">
                        카드결제
                    </v-btn>
                    <v-btn
                    width="180"
                    height="100"
                    @click="onPaymentToss">
                        토스결제
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import axios from 'axios'
import {createdPaymentRandomNumber} from '@/util/APIUtil.js'
//import Vue from 'vue'

export default {
    name:'PaymentBox',
    props:{
        lectureInfo:{
            type : Object,
            required : true
        }
    },
    data(){
        return{
            open:false,
            clientKey:'test_ck_JQbgMGZzorzQGM6Nxm7Vl5E1em4d',
            lectureList:'',
            orderName:'',
            orderId:'',
            customerName:'',
            amount:''
        }
    },
    mounted(){
        this.orderId = createdPaymentRandomNumber();
        alert(this.orderId);
        this.customerName = this.$cookies.get("USER_NAME")
    },
    methods:{
        openPayment(){
            this.open = !this.open
            this.lectureList = this.lectureInfo.lectureList
            this.amount = this.lectureInfo.totalPrice
            alert("렉쳐이름"+this.lectureList)
            if(this.lectureList.length>1){
                this.orderName = this.lectureList[0] + " 외 "+(this.lectureList.length-1) +" 건"
            }
            else{
                this.orderName = this.lectureList[0];
            }
            alert("주문이름"+this.orderName)
            alert("손님이름"+this.customerName)
            alert("가격"+this.amount)
        
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
        }
    }

}
</script>