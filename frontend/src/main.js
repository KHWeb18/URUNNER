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

// bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// 관리자페이지 라이브러리
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/ko'

Vue.mixin(mixin)
Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(VueCookies)
Vue.use(vueMoment) //날짜 출력
Vue.use(TiptapVuetifyPlugin, {
  vuetify,
  iconsGroup: 'mdi'
})
Vue.use(ElementUI, { locale })
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
