<template>
    <div>
        <v-btn
        width="200"
        height="50"
        @click="cancelPayment"
        color="#1dc078"
        dark>
            결제취소하기
        </v-btn>
    </div>
</template>
<script>
export default {
    name:"PaymentCancel",
    methods:{
        cancelPayment(){
            
        }
    }
}
</script>