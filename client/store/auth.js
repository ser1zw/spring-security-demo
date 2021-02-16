export const state = () => ({
  isLoggedIn: false
})

export const mutations = {
  setLoggedInStatus(state, isLoggedIn) {
    state.isLoggedIn = isLoggedIn
  }
}

export const actions = {
  async isLoggedIn({ commit }) {
    let isLoggedIn = false
    try {
      await this.$axios.$get('http://localhost:8080/api/restricted')
      isLoggedIn = true
    } catch (error) {
    }
    commit('setLoggedInStatus', isLoggedIn)
    return isLoggedIn
  },

  async logout({ commit }) {
    await this.$axios.$post('http://localhost:8080/api/logout')
    commit('setLoggedInStatus', false)
  }
}
