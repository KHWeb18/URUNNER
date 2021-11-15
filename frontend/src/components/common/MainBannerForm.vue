<template>
  <v-banner :color="bannerInfo.color" max-height="480">
    <v-row no-gutters class="align-center">
      <v-col cols="12" md="7">
        <div>
          <v-img :src="bannerInfo.img" width="100%"></v-img>
        </div>
      </v-col>
      <v-col cols="12" md="5" class="order-md-first">
         <div>
            <v-btn
                :class="bannerInfo.badge.fontColor"
                :color="bannerInfo.badge.color"
                depressed small
                style="margin-bottom:1rem; font-weight: bold"
              >
                {{ bannerInfo.badge.text }}
            </v-btn>
           <h2 class="text-lg-h3 text-md-h4 font-weight-bold mb-4 pa-2" :style="bannerInfo.fontColor">{{ bannerInfo.title }}</h2>
           <div class="text-h6 hidden-sm-and-down" :style="bannerInfo.fontColor">
             {{ bannerInfo.desc }}
           </div>
         </div>
      </v-col>
    </v-row>
  </v-banner>
</template>

<script>
export default {
  props: {
    bannerInfo: {
      type: Object,
      require: true
    }
  },
}
</script>