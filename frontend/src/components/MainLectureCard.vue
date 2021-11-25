<template>
    <v-hover>
    <template v-slot:default="{ hover }">
      <v-card
        class="mx-auto"
        max-width="400"
        max-height="380"
        :to="`course/${lecture.id}`"
      >
        <v-img :src="`http://localhost:7777/lecture/image/${lecture.thumbPath}/${lecture.writer}`" max-height="200"></v-img>

        <v-card-text class="pa-1">
          <h2 class="card_title text-h6 primary--text px-1">
            {{ lecture.title }}
          </h2>
          <p class="mb-0">{{ lecture.writer }}</p>
        </v-card-text>

        <v-card-title class="pa-1 d-flex">
          <v-rating
            :value="lecture.getReviewDto.avg"
            dense
            color="yellow"
            background-color="yellow"
            readonly
            half-increments
            class="pa-0"
          ></v-rating>
          <span class="primary--text text-subtitle-2 pb-0">{{ lecture.getReviewDto.count - 1}}명</span>
        </v-card-title>
        <v-card-text class="ma-1 pa-1 text-center">
           <p class="text-h6 warning--text mb-1">{{ getCurrencyFormat(lecture.price) }} 원</p>
        </v-card-text>

        <v-fade-transition>
          <v-overlay
            v-if="hover"
            absolute
            opacity="0.85"
            color="black"
            class="white--text"
          >
            <div class="ma-1">
              <strong>{{ lecture.desc }}</strong>
              <br>
              <br>
              <span v-for="category in lecture.category" :key="category.category_id">
                 # {{ category.categoryName }}
              </span>
              <div class="ma-2 align-right" >
                <v-icon :color="wish ? 'red' : null" class="d-block pa-1" @click.prevent="toggleHeartBtn(lecture.id)">
                  mdi-cards-heart
                </v-icon>
                <v-icon :color="cart ? 'primary' : null" class="d-block pa-1" @click.prevent="toggleCartBtn(lecture.id)">
                  mdi-cart
                </v-icon>
              </div>
            </div>
          </v-overlay>
        </v-fade-transition>
      </v-card>
    </template>
  </v-hover>
</template>

<script>
import axios from 'axios';
import { API_BASE_URL } from '@/constants/index'
export default {
  props: {
    lecture: {
      type: Object,
      require: true,
    }
  },
  data() {
    return {
      wish: null,
      cart: null,
    }
  },
  created() {
    this.wish = this.lecture.wishList
    console.log('this.lecture')
    console.log(this.lecture)
    this.cart = this.lecture.cart
  },
  methods: {
    getCurrencyFormat(value) {
      return this.$currencyFormat(value);
    },
    toggleHeartBtn(lectureId) {
     axios.get(`${API_BASE_URL}/manageLecture/addToWish/${lectureId}`)
            .then(({ data }) => {
               this.wish = data
            })
            .then(() => {
               axios.get(`${API_BASE_URL}/manageLecture/mainWishList`)
                .then(({data}) => {
                  console.log(data);
                  this.$store.state.wishList = data.data
                })
              })
    },
    toggleCartBtn(lectureId) {
      axios.get(`${API_BASE_URL}/manageLecture/addToCart/${lectureId}`)
            .then(({ data }) => {
               this.cart = data
            })
            .then(() => {
               axios.get(`${API_BASE_URL}/manageLecture/mainCartList`)
                .then(({data}) => {
                  this.$store.state.cartList = data.data

              })
            })
    }
  },
}
</script>

<style scoped>
.card_title {
  overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1; /* 표시하고자 하는 라인 수 */
    -webkit-box-orient: vertical;
}
</style>