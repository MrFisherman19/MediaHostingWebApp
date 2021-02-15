import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    basic:''
  },
  mutations: {
    ADD_USER_DATA: (state, data) => {
      state.basic = Object.assign({}, data);
    }
  },
  actions: {
  },
  modules: {
  }
})
