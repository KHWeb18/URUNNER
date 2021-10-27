<template>
<div>
      <v-row align="end">
          <v-col class="font-weight-black mt-5" align="left" cols="6">
              내 쿠폰함
          </v-col>
          <v-col cols="6"  align="right">
              <v-btn outlined class="font-weight-black">
                  쿠폰 및 할인 이용안내
              </v-btn>
          </v-col>
      </v-row>
      <v-row outlined>
        <v-col>
          <v-card outlined height="210">
              <v-row>
                <v-col class="mt-3 ml-3">
                      <v-icon>mdi-checkbox-marked-circle-outline</v-icon>
                      사용가능한 쿠폰
                      
                </v-col>
              <v-divider vertical ></v-divider>
                  <v-col class="mt-3">
                      <v-icon>mdi-alarm</v-icon>
                만료임박 쿠폰
          </v-col>
              </v-row>
              <v-row>
                  <v-col cols="5" align="right" class="mr-2">
                      <h2>{{}}개</h2>
                  </v-col>
                  <v-col cols="6" align="right" class="ml-2">
                       <h2>{{}}개</h2>
                  </v-col>
              </v-row>
              <v-divider class="mt-10"></v-divider>
              <v-row class="mt-4 ml-3" no-gutters>
                  <v-col class="font-weight-bold mt-2" cols="3">
                  할인쿠폰 코드 입력란
                  </v-col>
                  <v-col cols="7" class="mt-1" align="right">
                      <v-text-field
                        label="보유하신 쿠폰 코드를 입력해주세요"
                        dense
                      ></v-text-field>
                  </v-col>
                  <v-col >
                      <v-btn color="teal accent-4" dark>
                          등록
                      </v-btn>
                  </v-col>
              </v-row>
          </v-card>
          </v-col>
      </v-row>
      <v-row>
           <v-tabs
      v-model="tab"
      background-color="transparent"
      color="basil"
      grow
    >
      <v-tab
        v-for="item in items"
        :key="item"
      >
        {{ item }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-card
          color="basil"
          flat
        >
        <v-row height="100">
        <v-col cols="3">
          <MainLecture />
        </v-col>
        <v-col cols="3">
          <MainLecture />
        </v-col>
        </v-row>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
      </v-row>
</div>
</template>

<script>
  export default {
    components: {
      MainLecture: () => import("../MainLectureCard.vue")
    },
    data () {
      return {
        tab: null,
        // 나중에 db랑 연동구현하기로
        items: [
          '사용가능', '만료임박', '사용완료 만료임박', '전체 쿠폰',
        ],
      }
    }
}
</script>

<style>

</style>