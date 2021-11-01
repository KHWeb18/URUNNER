<template>
  <div>
      <div class="column is-10">
        <div class="columns post_header">
          <div class="column is-full">
              <h1 class="제목" style="padding: 50px 0px 20px 0px;">{{notice.title}}</h1>
          </div>
          <hr width="80%">
          <div class="본문" align="left">
              <div class="날짜" style="padding: 10px;">
                <strong>{{new Date(notice.upDate).toLocaleString("ko-KR", {timeZone: "Asia/Seoul"}).toString().substr(0, 11)}}</strong>
              </div>      
              <div class="작성자" style="padding: 10px;">
                <strong>작성자 : {{notice.writer}}</strong>
              </div>  
              <div class="내용" style="padding: 10px;" v-html="content">
                <p>{{ notice.content }}</p>
              </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
    name: 'NoticeRead',
    props: {
        notice: {
            type: Object,
            required: true
        }
    },
    computed: {
      content() {
        return this.notice.content.split('\n').join('<br>');
      }
    }    
}
</script> 

<style scoped>

.본문 {
  padding:0px 230px 0px 230px;
}
</style>