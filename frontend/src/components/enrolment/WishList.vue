<template>
<div>
  <v-row class="mt-10 ml-1">
     <v-menu
      rounded="rounded"
      offset-y
    >
      <template v-slot:activator="{ attrs, on }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          outlined
          color="green"
        >
        정렬기준
        </v-btn>
      </template>
      <v-list flat outlined>
        <v-list-item
          v-for="item in items"
          :key="item"
          link
        >
          <v-list-item-title v-text="item"></v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-row>
  <v-row>
    <v-col cols="12" md="4" sm="6" xs="12" v-for="wish in wishList" :key="wish.id">
     <v-card
        class="mx-auto"
        max-width="400"
        max-height="380"
        :to="`course/${wish.id}`"
      >
        <v-img :src="`http://localhost:7777/lecture/image/${wish.thumbPath}/${wish.writer}`"></v-img>

        <v-card-text class="pa-1">
          <h2 class="text-h6 primary--text">
            {{ wish.title }}
          </h2>
          <p class="mb-0">{{ wish.writer }}</p>
        </v-card-text>

        <v-card-title class="pa-1 d-flex">
          <v-rating
            :value="4"
            dense
            color="yellow"
            background-color="yellow"
            hover
            class="pa-0"
          ></v-rating>
          <span class="primary--text text-subtitle-2 pb-0">(64)</span>
        </v-card-title>
        <v-card-text class="ma-1 pa-1 text-center">
           <p class="text-h6 warning--text mb-1">{{ getCurrencyFormat(wish.price) }} 원</p>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
export default {
    data: () => ({
        wishList: [],
        items: ["제목순","최신순", "학생수순", "평점순"],
    }),
    created() {
      this.getWishList()
    },
    methods: {
      getWishList() {
        axios.get(`${API_BASE_URL}/manageLecture/getWishList`)
              .then(({data}) => {
                this.wishList = data.data
                console.log(this.wishList);
              });
      },
      getCurrencyFormat(value) {
      return this.$currencyFormat(value);
    },
    },
}
</script>

<style>
</style>