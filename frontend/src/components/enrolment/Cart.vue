<template>
<v-container>
    <v-card class="ma-15" flat>
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
  <div v-if="lecture ? false : true">
  <v-row justify="center" align="center" class="justify-center" style="height:100vh">
        <v-card flat width="350" height="600" class="justify-center font-weight-bold" align="center" >
            <div class="justify-center" align="center">
                <img src="@/img/cart.png" alt="cartImage" width="100" height="100">
            </div>
        <!-- <v-icon>mdi-cart-outline</v-icon><br> -->
            바구니에 담긴 강의가 없습니다.<br>
            나를 성장시켜 줄 좋은 지식들을 찾아보세요.
        <v-btn class="mr-5 ma-2 font-weight-bold" dark color="teal accent-4">
            강의 리스트 보기
        </v-btn>
         <v-btn color="light-blue darken-1" dark class="font-weight-bold">
            내 위시 리스트 보기
        </v-btn>
        </v-card>
  </v-row>
  </div>
    </v-card>
</v-container>
</template>

<script>
export default {
    data: () => ({
        lecture: null
    })
}
</script>

<style>

</style>