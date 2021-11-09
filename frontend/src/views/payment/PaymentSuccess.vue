<template>
    <div>
        <p>성공!</p>
        <h2>{{amount}}</h2>
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
            paymentKey:''
        }
    },
    mounted(){
        this.amount = this.$route.query.amount
        this.orderId= this.$route.query.orderId
        this.paymentKey= this.$route.query.paymentKey
        const { amount,orderId,paymentKey } = this
  
        axios.post('http://localhost:7777/payment/success',{paymentKey,orderId,amount})
        .then((res)=>{
            alert(res.data)
            this.$router.push('/')
        }

        )
    }
    
}
</script>