<template>
  <div>
    <button 
      @click="onPayment"
      color="green"
      x-large>
      카드결제
    </button>
  </div> 
</template>

<script>

export default {
    methods:{      
        onPayment(){
            alert(window.location.origin)
            //var clientKey = 'test_ck_OEP59LybZ8Bdv6A1JxkV6GYo7pRe'
            //var tossPayments = window.TossPayments(clientKey)
            const tossPayments = window.TossPayments('test_ck_JQbgMGZzorzQGM6Nxm7Vl5E1em4d')
            tossPayments.requestPayment('카드', {
                amount: 100,
                orderId: 'abceded',
                orderName: '토스 티셔츠 외 2건',
                customerName: '박토스',
                successUrl: window.location.origin + '/payment/success',
                failUrl: window.location.origin + '/payment/fail',
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