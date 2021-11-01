<template>
<div>
    <div class="column is-10">
            <div class="columns post_header">
              <div class="column is-full">
                  <h1 class="post-title">{{notice.title}}</h1>
              </div>
            </div>
            <div><strong>작성자 : {{notice.writer}}</strong></div>
            <span class="date">{{new Date(notice.regDate).toLocaleString("ko-KR", {timeZone: "Asia/Seoul"}).toString().substr(0, 11)}}</span>
            <div class="post-body" v-html="content">
				<p>{{ notice.content }}</p>
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

.글작성 {
  padding:0px 200px 0px 200px;
}
</style>