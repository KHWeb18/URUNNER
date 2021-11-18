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
            orderId:''
        }
    },
    mounted(){
        
        this.orderId = createdPaymentRandomNumber();
        alert(this.orderId);
        this.lectureList = this.lectureInfo.lectureList
        alert(this.lectureList)
        if(this.lectureList.length>1){
            this.orderName = this.lectureList[0] + " 외 "+(this.lectureList.length-1) +" 건"
        }
        alert(this.orderName)
    },
    methods:{
        openPayment(){
            this.open = !this.open
        },
        onPaymentCard(){ 
            const{lectureList} =this    
            const {orderName} = this
            const {orderId} = this
            axios.post('http://localhost:7777/payment/pay-ready',{lectureList})
            const tossPayments = window.TossPayments(this.clientKey)
            tossPayments.requestPayment('카드', {
                amount: 100,  //lectureInfo의 price
                orderId: orderId,   //프론트에서 처리할 메소드 구현
                orderName: orderName,    //lectureInfo의 title
                customerName: '박토스', //cookies의 userId ( 백엔드에서 토큰으로 처리)
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
            const{lectureList} =this    
            const {orderName} = this
            const {orderId} = this
            axios.post('http://localhost:7777/payment/pay-ready',{lectureList})
            const tossPayments = window.TossPayments(this.clientKey)
            tossPayments.requestPayment('토스결제', {
                amount: 100,
                orderId: orderId,
                orderName: orderName,
                customerName: '박토스',
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