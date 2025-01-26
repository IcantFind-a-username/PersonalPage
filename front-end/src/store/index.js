import { createStore } from 'vuex'

export default createStore({
  state: {
    locale: 'zh',
    messages: [],
    user: null
  },
  mutations: {
    setLocale(state, locale) {
      state.locale = locale
    },
    addMessage(state, message) {
      state.messages.unshift(message)
    },
    setMessages(state, messages) {
      state.messages = messages
    }
  },
  actions: {
    async addMessage({ commit }, message) {
      // 这里可以添加实际的API调用
      commit('addMessage', {
        id: Date.now(),
        ...message
      })
    },
    async fetchMessages({ commit }) {
      // 这里可以添加实际的API调用
      const messages = [] // 从API获取数据
      commit('setMessages', messages)
    }
  }
})