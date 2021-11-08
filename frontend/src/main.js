import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store'
import vuetify from './plugins/vuetify'
import VueCookies from 'vue-cookies'
import vueMoment from 'vue-moment' //날짜 출력
import mixin from './mixin'

import { TiptapVuetifyPlugin } from 'tiptap-vuetify'
import 'tiptap-vuetify/dist/main.css'
import 'vuetify/dist/vuetify.min.css'

Vue.mixin(mixin)
Vue.config.productionTip = false

Vue.use(VueCookies)
Vue.use(vueMoment) //날짜 출력
Vue.use(TiptapVuetifyPlugin, {
  vuetify,
  iconsGroup: 'mdi'
})

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
