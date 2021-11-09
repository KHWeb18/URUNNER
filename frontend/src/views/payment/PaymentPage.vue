<template>
    <div>
        <payment-card></payment-card>
        <payment-toss></payment-toss>
    </div>
</template>

<script>


import PaymentCard from'@/components/payment/PaymentCard.vue'
import PaymentToss from '../../components/payment/PaymentToss.vue'



export default {
    name:'PaymentPage',
    components:{
        PaymentCard,
        PaymentToss
    },
}


</script>