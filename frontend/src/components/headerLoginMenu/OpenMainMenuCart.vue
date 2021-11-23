<template>
  <v-card color="primary" width="500">
    <v-tabs fixed-tabs slider-color="primary">
      <v-tab v-for="(item, index) in items" :key="index">
        {{ item }}
      </v-tab>
        <v-tab-item>
          <v-card>
            <v-list>
              <v-list-item-group color="primary">
                <v-list-item-content v-if="cartList.length == 0">
                    <v-list-item-subtitle><p class="text-center">수강바구니가 비어있습니다.</p></v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item v-else v-for="(cart) in cartList" :key="cart.id" class="mb-2" :to="`/course/${cart.id}`">
                  <v-img :src="`http://localhost:7777/lecture/image/${cart.thumbPath}/${cart.writer}`" max-height="89" max-width="123"/>
                  <v-list-item-content class="ml-2">
                    <v-list-item-title class="text-h7">{{ cart.title }}</v-list-item-title>
                    <v-list-item-subtitle v-text="cart.price" class="text-right pa-3"/>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
            <v-list class="ma-0 pa-0">
              <v-btn color="primary" x-large block to="/cart">수강 바구니로 이동</v-btn>
            </v-list>
        </v-card>
       </v-tab-item>

        <v-tab-item>
          <v-card>
            <v-list>
               <v-list-item-group color="primary">
                <v-list-item-content v-if="wishList.length == 0">
                    <v-list-item-subtitle><p class="text-center">위시리스트가 비어있습니다.</p></v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item v-else v-for="(wish) in wishList" :key="wish.id" class="mb-2" :to="`/course/${wish.id}`">
                  <v-img :src="`http://localhost:7777/lecture/image/${wish.thumbPath}/${wish.writer}`" max-height="89" max-width="123"/>
                  <v-list-item-content class="ml-2">
                     <v-list-item-title class="text-h7">{{ wish.title }}</v-list-item-title>
                    <v-list-item-subtitle v-text="wish.price" class="text-right pa-3"/>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
            <v-list class="ma-0 pa-0">
              <v-btn color="primary" x-large block to="wishlist">위시 리스트로 이동</v-btn>
            </v-list>
        </v-card>
       </v-tab-item>
    </v-tabs>
  </v-card>
</template>

<script>
import axios from 'axios'
import { API_BASE_URL } from '@/constants/index'
  export default {
    data () {
      return {
        tab: null,
        items: [
          '수강바구니', '위시리스트'
        ],
        LectureLists: [
        { title: '스스로 구축하는 AWS 클라우드 네트워크 - 기본편', price: '₩42,000', image: 'https://cdn.inflearn.com/public/courses/327724/cover/e6ee6969-4d60-4b64-8401-11c5ed0ad288/327724-eng.png' },
        { title: '스스로 구축하는 AWS 클라우드 네트워크 - 기본편', price: '₩42,000', image: 'https://cdn.inflearn.com/public/courses/327724/cover/e6ee6969-4d60-4b64-8401-11c5ed0ad288/327724-eng.png' },
        { title: '스스로 구축하는 AWS 클라우드 네트워크 - 기본편', price: '₩42,000', image: 'https://cdn.inflearn.com/public/courses/327724/cover/e6ee6969-4d60-4b64-8401-11c5ed0ad288/327724-eng.png' },
      ],
        cartList: [],
        wishList: [],
      }
    },
    created() {
      this.mainCartList();
      this.mainWishList();
      
    },
    methods: {
      mainCartList() {
        axios.get(`${API_BASE_URL}/manageLecture/mainCartList`)
              .then(({data}) => {
                this.cartList = data.data

              })
      },
      mainWishList() {
        axios.get(`${API_BASE_URL}/manageLecture/mainWishList`)
              .then(({data}) => {
                console.log(data);
                this.wishList = data.data
              })
      }
    }
  }
</script>

<style>

</style>