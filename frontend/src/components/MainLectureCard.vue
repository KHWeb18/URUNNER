<template>
    <v-hover>
    <template v-slot:default="{ hover }">
      <v-card
        class="mx-auto"
        max-width="400"
      >
        <v-img src="https://cdn.inflearn.com/public/files/courses/324504/9d9292e5-a1d3-47dc-9410-aa2a9ecc9cd7/Promotion-01.jpg"></v-img>

        <v-card-text class="pa-1">
          <h2 class="text-h6 primary--text">
            일주일 완성! 3dsmax 입문 (자동차 및 캐릭터 만들기)
          </h2>
          <p class="mb-0">목마하임(MokmaHeim)</p>
        </v-card-text>

        <v-card-title class="pa-1 d-flex">
          <v-rating
            :value="4"
            dense
            color="yellow"
            background-color="yellow"
            hover
            class="pa-0"
          ></v-rating>
          <span class="primary--text text-subtitle-2 pb-0">(64)</span>
        </v-card-title>
        <v-card-text class="ma-1 pa-1 text-center">
           <p class="text-h6 warning--text mb-1">55,000원</p>
        </v-card-text>

        <v-fade-transition>
          <v-overlay
            v-if="hover"
            absolute
            opacity="0.85"
            color="black"
            class="white--text"
          >
            <strong>입문자를 위해 준비한[3D 모델링] 강의</strong>
            <p>3dsmax를 처음 접하시는 분들을 위한 막연한 두려움을 해소하고 반드시 알아야할 내용을 선별한 필수 입문강좌!</p> 
          </v-overlay>
        </v-fade-transition>
      </v-card>
    </template>
  </v-hover>
</template>