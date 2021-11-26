<template>
<v-container >
    <v-card class="ma-10" flat>
        <span class="font-weight-black">내 구매 내역</span>
    <template>
  <v-data-table
    dense
    :headers="headers"
    :items="this.$store.state.orders"
    item-key="name"
    class="mt-5"
    show-expand
    items-per-page="5"
    expanded="orderitem.text"
    hide-default-footer
  >
  <template v-slot:[`item.orderDate`]="{ item }">
         {{ $moment(item.orderDate).add(-0, 'hours').format('YY-MM-DD HH:mm') }}
  </template>
  
  </v-data-table>
   <div class="text-center pt-2">
      <v-pagination
        v-model="page"
        :length="pageCount"
      ></v-pagination>
   </div>
</template>
    </v-card>
</v-container>
</template>

<script>
import { API_BASE_URL } from '@/constants/index'
import axios from 'axios'
  export default {
    methods: {
      getPurchasedLecture() {
        axios({
          method: 'get',
          url: API_BASE_URL + "/payment/getPurchasedLecture"
        })
        .then(res => {
          console.log(res.data.data)
          this.$store.state.orders = res.data.data
        })
        .catch(err => {
          console.log(err)
        })
        
      }
    },
    created() {
      this.getPurchasedLecture()
    },
    data: () => ({
        page: 1,
        pageCount: 0,
      // orders: [
      //   {
      //     orderNum: '1150294',
      //     date: "2021-10-26",
      //     status: "결제완료",
      //     productName: "스프링 배치 - Spring Boot 기반 개발",
      //     price: 69300,
      //   },
      // ],
      headers: [
        {
          text: '주문번호',
          align: 'start',
          value: 'id',
        },
        { text: '주문날짜', value: 'orderDate' },
        { text: '상태', value: 'status' },
        { text: '주문명', value: 'title' },
        { text: '금액', value: 'price' },
      ],
    }),
  }
</script>

<style>

</style>