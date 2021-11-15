<template>
    <v-hover>
    <template v-slot:default="{ hover }">
      <v-card
        class="mx-auto"
        max-width="400"
        max-height="380"
        :to="`course/${lecture.id}`"
      >
        <v-img :src="`http://localhost:7777/lecture/image/${lecture.thumbPath}/${lecture.writer}`"></v-img>

        <v-card-text class="pa-1">
          <h2 class="text-h6 primary--text">
            {{ lecture.title }}
          </h2>
          <p class="mb-0">{{ lecture.writer }}</p>
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
              <span v-for="category in lecture.category" :key="category.category_id">
                 # {{ category.categoryName }}
              </span>
            </div>
          </v-overlay>
        </v-fade-transition>
      </v-card>
    </template>
  </v-hover>
</template>

<script>
export default {
  props: {
    lecture: {
      type: Object,
      require: true,
    }
  },
  methods: {
    getCurrencyFormat(value) {
      return this.$currencyFormat(value);
    },
  },
}
</script>