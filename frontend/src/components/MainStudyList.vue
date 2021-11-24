<template>
  <div class="ma-2">
    <h2>지금 모집중인 스터디에 지원해보세요! 🙆‍♂️ NOW!! </h2>
    <v-card max-width="550" class="mx-auto">
    <v-list three-line>
      <template v-for="item in noNoticeBoards">
        <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>
        <v-list-item v-else :key="item.title">

          <div class="mr-4 hidden-sm-and-down">
            <v-progress-circular
            :rotate="-90"
            :size="60"
            :width="12"
            :value="item.currentNum / item.fit * 100"
            color="primary"
            >
            {{ item.currentNum }} / {{ item.fit }}
            </v-progress-circular>
          </div>
          <v-list-item-content>
            <div class="temp" :class="{ on : item.complete == 'true'}">
              <v-list-item-title v-html="item.title"></v-list-item-title>
              <v-list-item-subtitle v-html="replaceHtml(item.content)"></v-list-item-subtitle>
            </div>
            <div class="compleImg" v-show="item.complete =='true'"><v-img :src="require(`@/assets/complete.png`)"></v-img></div>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-card>
  </div>
</template>


<script>
  export default {
    props: {
        boards: {
            type: Array
        }
    },
    data () {
      return {
        noNoticeBoards: []
      }
    },
    created() {
        setTimeout(() => {
            var lists = this.boards
            console.log('boards. created()')
            console.log(this.boards)
            var searchingResult = []

            for(var i = 0; i < lists.length; i++){
              const regex = new RegExp('false', "gi");
              const comparison = regex.test(lists[i].notice)
              if(comparison){
                searchingResult.push(lists[i])
              }
            }
            this.noNoticeBoards = searchingResult.slice(0, 5)
        }, 500 )
    },
    methods: {
      replaceHtml(data) {
            var text = data.replace(/(<([^>]+)>)/ig,"");
            return text
      }      
    }
  }
</script>

<style scoped>
.mx-auto.v-card.v-sheet.theme--light {
  box-shadow: none !important;
}
.progress {
  margin: 0px;
}
.v-list-item__title {
  font-weight: bold;
  color: #424242;
}
.compleImg {
  position: absolute;
  width: 100px;
  top: 10px;
  left: 250px;

}.temp {
  opacity: 1;
}
.temp.on {
  opacity: 0.3;
}
</style>