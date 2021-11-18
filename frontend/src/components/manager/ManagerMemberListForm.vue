<template>
        <div>
            <v-simple-table style="padding:20px 100px 100px 100px">
              <v-data-table
                  :headers="headers"
                  :items="memberLists"
                  :search="search"
                  :page.sync="page"
                  :items-per-page="10"
                  hide-default-footer
                  class="elevation-1"                   
                  @page-count="pageCount = $event"
              ></v-data-table>

              <div class="search-box" style="padding:20px 350px 20px 350px">
                  <v-text-field
                      v-model="search"
                      append-icon="mdi-magnify"
                      label="Search"
                      single-line
                      hide-details
                  ></v-text-field>
              </div>

              <div class="text-center pt-2">
                <v-pagination
                    v-model="page"
                    :length="pageCount"
                    dark
                ></v-pagination>
              </div>
            </v-simple-table>
        </div>
</template>

<script>

export default {

    name: 'ManagerMemberListForm',
    props: {
        memberLists: {
            type: Array
        }
    },
    data() {
        return {
                page: 1,
                pageCount: 0,
                itemsPerPage: 10,
                search: '',
                headers:[
                          { text: '회원번호', value: 'memberNo', width: "70px" },
                          { text: '이메일', value: 'email', width: "200px" },
                          { text: '닉네임', value: 'nickname', width: "80px" },
                          { text: 'Provider', value: 'provider', width: "60px" }
                        ],
                icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],    
              }
    },
    function: {
    },
    methods: {
    
    },
    mounted () {
        //console.log(this.notices.regDate)
        //console.log(this.notices.noticeNo)
    }
}
</script> 

<style scoped>

</style>