<template>
    <div>
        <p>결제 성공!</p>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'PaymentSuccess',
    
    data(){
        return{
            amount:'',
            oderId:'',
            paymentKey:'',
            orderName:'',
            customerName:''
        }
    },
    mounted(){
        this.amount = this.$route.query.amount
        this.orderId= this.$route.query.orderId
        this.paymentKey= this.$route.query.paymentKey
        const { amount,orderId,paymentKey } = this
  
        axios.post('http://localhost:7777/payment/success',{paymentKey,orderId,amount})
        .then((res)=>{
            if(res.data =="success"){
                alert('결제성공!')
                this.$router.push('/')
            }else{
                alert('결제실패!')
                this.$router.push('/')
            }
        }

        )
    }
    
}
</script>