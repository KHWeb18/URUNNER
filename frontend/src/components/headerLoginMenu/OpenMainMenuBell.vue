<template>
  <v-card class="mx-auto" width="500">
    <v-list two-line>
      <v-list-item-group active-class="primary--text" multiple>
        <template v-for="notice in notices">
          <v-list-item :key="notice.noticeNo">
              <v-list-item-content>
                <v-list-item-title>- {{ notice.title }}</v-list-item-title>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text v-text="notice.regDate"></v-list-item-action-text>
              </v-list-item-action>
          </v-list-item>

          <v-divider
            v-if="notice.noticeNo < notices.length - 1"
            :key="notice.noticeNo"
          ></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
    <v-list class="ma-0 pa-0">
      <v-btn color="primary" x-large block @click.prevent="handleClick">더 많은 알림 보기</v-btn>
    </v-list>
  </v-card>
</template>

<script>

import { mapState, mapActions } from 'vuex'

  export default {
    computed: {
        ...mapState(['notices'])
    },
    mounted () {
        this.fetchNoticeList()
    },
    methods: {
      ...mapActions(['fetchNoticeList']),
      handleClick() {
          this.$router.push({
                          name: 'NoticeListPage',
                        })
      },
  },
}
</script>

<style>

</style>