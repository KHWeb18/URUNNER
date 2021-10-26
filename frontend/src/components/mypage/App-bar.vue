<template>
    <v-app-bar
      color="deep-grey"
      dark
      scroll-target="#scrolling-techniques-6"
      height="50"
    >
      <v-toolbar-title>{{ title }}</v-toolbar-title>

      <v-spacer></v-spacer>
    </v-app-bar>
</template>

<script>
// import EventBus from "@/event"
export default {
    data:() => ({
        title: "Mypage"
    }),
    // created: function() {
    //      EventBus.$on('title', (title) => this.title = title)
    // },

}
</script>

<style>

</style>