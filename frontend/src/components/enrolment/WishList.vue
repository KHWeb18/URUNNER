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
    <v-col cols="3">
    <MainLecture/>
    </v-col>
    <v-col cols="3">
    <MainLecture/>
    </v-col>
    <v-col cols="3">
    <MainLecture/>
    </v-col>
    <v-col cols="3">
    <MainLecture/>
    </v-col>

  </v-row>
  </div>
</template>
<script>
  export default {
    data: () => ({
      items: ["제목순","최신순", "학생수순", "평점순"],
    }),
    components: {
      MainLecture: () => import("../MainLectureCard.vue")
    }
  }
</script>

<style>

</style>