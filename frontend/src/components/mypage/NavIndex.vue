<template>
  <v-row>
    <v-col cols="3" class="hidden-md-and-down">
      <v-list
        dense
      >
         <nav-list />
      </v-list>
    </v-col>
    <v-col cols="12" lg="9">
      <defaultView/>
    </v-col>
  </v-row>
</template>

<script>
  export default {
      components: {
        NavList: () => import('./NavList.vue'),
        DefaultView: () => import("./View.vue")
      },
  }
</script>

<style scoped>
</style>