export const state = () => ({
  isLoggedIn: false
})

export const mutations = {
  setLoginStatus(state, isLoggedIn) {
    state.isLoggedIn = isLoggedIn
  }
}

export const actions = {
  async isLoggedIn({ commit }) {
    let response = await this.$axios.$get('http://localhost:8080/api/loginstatus')
    commit('setLoginStatus', response.isLoggedIn)
    return response.isLoggedIn
  },
  async login({ commit }, { loginId, password }) {
    const params = new URLSearchParams()
    params.append('username', loginId)
    params.append('password', password)
    let response = await this.$axios.$post('http://localhost:8080/api/login', params)
    commit('setLoginStatus', true)
    return response
  },
  async logout({ commit }) {
    await this.$axios.$post('http://localhost:8080/api/logout')
    commit('setLoginStatus', false)
  }
}
