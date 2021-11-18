<template>
    <div>
        <payment-box v-bind:lectureInfo="lectureInfo"></payment-box>
    </div>
</template>

<script>


import PaymentBox from '../../components/payment/PaymentBox.vue'




export default {
    name:'PaymentPage',
    components:{
        PaymentBox
    },
    data(){
        return{
            //데이터담기는 부분
            lectureInfo:{
                //데이터 추가시 이쪽에 추가
                lectureList:['강의5','강의2','강의3','강의4',]
            }
        }
    }
}


</script>