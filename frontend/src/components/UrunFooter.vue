<template>
  <v-app-bar
    bottom
    fixed
    app
    class="hidden-lg-and-up"
  >
    <div class="d-flex justify-space-around font-weight-bold" style="width:100%;">
      <div class="footer-link">
        <router-link to="/myLecture">
          <v-icon class="d-flex justify-center">mdi-human-male-board-poll</v-icon>
          <span class="d-block">대시보드</span>
        </router-link>
      </div>
      <div class="footer-link">
        <router-link to="/forSale/lectureList">
          <v-icon class="d-flex justify-center">mdi-youtube</v-icon>
          <span class="d-block">강의</span>
        </router-link>
      </div>
      <div class="footer-link">
        <router-link to="/">
          <v-icon class="d-flex justify-center">mdi-home</v-icon>
          <span class="d-block text-center">홈</span>
        </router-link>
      </div>
      <div class="footer-link">
        <router-link to="/boardCol">
          <v-icon class="d-flex justify-center">mdi-message-processing-outline</v-icon>
          <span class="d-block">커뮤니티</span>
        </router-link>
      </div>
      <div class="footer-link">
        <router-link to="/notice">
          <v-icon class="d-flex justify-center">mdi-exclamation-thick</v-icon>
          <span class="d-block">공지사항</span>
        </router-link>
      </div>
    </div>
  </v-app-bar>
</template>

<style scoped>
 .v-application a {
    text-decoration: none;
    color: #5f5f5f;
  }
  .v-application a:hover {
    text-decoration: none;
    color: #1a76d2;
  }
  .v-application a .v-icon:hover {
    color: #1a76d2;
  }
</style>