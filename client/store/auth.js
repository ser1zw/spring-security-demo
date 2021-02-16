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
    let response = await this.$axios.$get('http://localhost:8080/api/loginstatus')
    commit('setLoggedInStatus', response.isLoggedIn)
    return response.isLoggedIn
  },

  async logout({ commit }) {
    await this.$axios.$post('http://localhost:8080/api/logout')
    commit('setLoggedInStatus', false)
  }
}
