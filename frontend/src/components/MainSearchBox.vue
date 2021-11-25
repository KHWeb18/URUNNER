<template>
  <div>
    <v-container class="my-5">
      <div class="text-center mb-3 text-h6 text-sm-h4">유러너에서 새로운 도전을 시작해보세요</div>
      <div class="search-box d-flex justify-center">
         <v-text-field
            label="search"
            append-icon="mdi-magnify"
            background-color=""
            style="max-width:720px;"
            filled
            rounded
            v-model="searchName"
            @keyup.enter="searchWithName(searchName)"
          ></v-text-field>
      </div>
      <div style="text-align: center">
            <v-row
              justify="center"
              align="center"
             >
              <v-col>
                <v-chip class="ml-5 ma-2 font-weight-bold"
                  dark
                  color="primary"
                   v-for="tag in tags"
                   :key="tag"
                   @click="searchWithCategory(tag)"
                >
                {{ tag }}
                </v-chip>
              </v-col>
            </v-row>
        </div>
    </v-container>
  </div>
</template>
<script>
export default {
  data:() => ({
    searchName: null,
     tags: [
        '#Vue',
        '#Swift',
        '#React',
        '#Html Css',
        '#JavaScript',
        '#MongoDB',
        '#Golang',
      ],
  }),
  methods: {
    searchWithName(searchName) {
      this.$store.state.searchName = searchName
      this.$router.push(`/forSale/lectureList`)
    },
    searchWithCategory(tag){
      console.log(tag)
      this.$store.state.cateName = tag.substr(1,tag.length)
      this.$router.push(`/forSale/lectureList`)
      
    }
  }

  
}
</script>