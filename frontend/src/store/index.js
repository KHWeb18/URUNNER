import Vue from 'vue'
import Vuex from 'vuex'

import state from './states'
import actions from './actions'
import mutations from './mutations'

import createPersistedState from 'vuex-persistedstate'
import moduleA from './webState'

Vue.use(Vuex)

export default new Vuex.Store({
  state,
  mutations,
  actions,
  moduleA,
  modules: {
    moduleA: moduleA
  },
  plugins: [createPersistedState({
    paths: ['moduleA'],
    // storage: window.sessionStorage,//탭 닫으면 사라짐
  })]
})
