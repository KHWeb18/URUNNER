<template>
  <v-row>
    <v-col cols="auto">
    <v-navigation-drawer >
      <v-list
        dense
      >
         <nav-list />
      </v-list>
    </v-navigation-drawer>
    </v-col>
    <v-col cols="9">
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