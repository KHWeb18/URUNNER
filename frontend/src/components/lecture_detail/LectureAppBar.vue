<template>
  <v-app-bar
    prominent
    bottom
    fixed
    class="hidden-md-and-up"
  >
    <v-container>
      <v-row>
      <v-col cols="1.5" class="px-0" xs="2">
        <div style="border:1px solid;border-radius:5px;height:3rem;" 
           align="center" class="mr-1">
          <p class="text-h9">문의</p>
        </div>
      </v-col>
      <v-col cols="1.5" class="px-0" xs="2">
         <div style="border:1px solid black;border-radius:5px;height:3rem" align="center" class="mr-1">
          <v-icon>mdi-cards-heart-outline</v-icon>
        </div>
      </v-col>
      <v-col cols="9" class="px-0" xs="8">
         <v-btn color="primary" large block>수강신청</v-btn>
      </v-col>
    </v-row>
    </v-container>
  </v-app-bar>
</template>

<script>
export default {

}
</script>

<style>

</style>