<template>
  <div>
    <v-card class="mb-3 mx-5" color="#F9F9F9">
       <v-card-title>
        <v-rating :value="comment.rating / 2"  dense color="yellow" readonly class="d-inline" half-increments/>
        <p class="pa-0 ma-0 mt-1 ml-1">{{ comment.rating / 2 }}</p>
      </v-card-title>
      <v-card-subtitle>
        <p class="text-h6">{{ comment.writer }}</p>
      </v-card-subtitle>
      <v-card-text>
        {{ comment.content }}
      </v-card-text>
   </v-card>
  </div>  
</template>

<script>
export default {
  props: {
    comment: {
      type: Object,
      required: true
    }
  }

}
</script>

<style>

</style>