<template>
<v-container >
    <v-card class="ma-10" flat>
        <span class="font-weight-black">내 구매 내역</span>
    <template>
  <v-data-table
    dense
    :headers="headers"
    :items="orders"
    item-key="name"
    class="mt-5"
    show-expand
    items-per-page="5"
    expanded="orderitem.text"
    hide-default-footer
  ></v-data-table>
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
  export default {
    data: () => ({
        page: 1,
        pageCount: 0,
      orders: [
        {
          orderNum: '1150294',
          date: "2021-10-26",
          status: "결제완료",
          productName: "스프링 배치 - Spring Boot 기반 개발",
          price: 69300,
        },
      ],
      headers: [
        {
          text: '주문번호',
          align: 'start',
          value: 'orderNum',
        },
        { text: '주문날짜', value: 'date' },
        { text: '상태', value: 'status' },
        { text: '주문명', value: 'productName' },
        { text: '금액', value: 'price' },
      ],
    }),
  }
</script>

<style>

</style>