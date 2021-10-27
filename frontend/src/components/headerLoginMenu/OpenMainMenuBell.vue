<template>
  <v-card class="mx-auto" max-width="500">
    <v-list two-line>
      <v-list-item-group active-class="primary--text" multiple>
        <template v-for="(item, index) in items">
          <v-list-item :key="index">
              <v-list-item-content>
                <v-list-item-title>- {{ item.title }}</v-list-item-title>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text v-text="item.action"></v-list-item-action-text>
              </v-list-item-action>
          </v-list-item>

          <v-divider
            v-if="index < items.length - 1"
            :key="index"
          ></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
    <v-list class="ma-0 pa-0">
      <v-btn color="primary" x-large block>더 많은 알림 보기</v-btn>
    </v-list>
  </v-card>
</template>

<script>
  export default {
    data: () => ({
      items: [
        {
          action: '3일 전',
          title: '[업데이트 소식 및 드리는글] 컨텐츠 DRM 보안 기술 도입',
        },
        {
          action: '1달 전',
          title: '[업데이트 소식] 8월 3주차 - 블로그 리뉴얼 🏡',
        },
        {
          action: '1달 전',
          title: '[공지] 개인정보 취급방침 개정 안내',
        },
        {
          action: '2달 전',
          title: '[서비스 장애 발생 공지] 🚨 2021년 6월 4일 서비스 오류',
        },
        {
          action: '3달 전',
          title: '[서비스 장애 발생 공지] 🚨 2021년 4월 7일 서비스 오류 원인 - 죄송합니다. 🙇🏻‍♀️ 🙇🏻',
        },
      ],
    }),
  }
</script>

<style>

</style>