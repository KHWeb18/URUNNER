<template>
        <div>
            <v-simple-table style="padding:20px 100px 100px 100px">
              <v-data-table
                  :headers="headers"
                  :items="notices"
                  :search="search"
                  :page.sync="page"
                  :items-per-page="10"
                  hide-default-footer
                  class="elevation-1" 
                  @click:row="handleClick"
                  @page-count="pageCount = $event"
              ></v-data-table>

              <div class="search-box" style="padding:20px 200px 20px 200px">
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
    name: 'NoticeList',
    props: {
        notices: {
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
                          { text: '번호', value: 'noticeNo', width: "70px" },
                          { text: '제목', value: 'title', width: "200px" },
                          { text: '작성자', value: 'writer', width: "50px" },
                          { text: '등록일자', value: 'regDate', width: "60px" }
                        ],
                icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],    
              }
    },
    function: {
    },
    methods: {
        handleClick(event, idx) {
            this.$router.push({
                            name: 'NoticeReadPage',
                            params: { noticeNo: String(idx.item.noticeNo) }
                         })
        }
    },
    mounted () {
        console.log(this.notices.regDate)
        console.log(this.notices.noticeNo)
    }
}
</script> 

<style scoped>

</style>