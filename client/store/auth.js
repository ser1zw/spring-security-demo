export const actions = {
  async isLoggedIn() {
    let isLoggedIn = false
    try {
      await this.$axios.$get('http://localhost:8080/api/restricted')
      isLoggedIn = true
    } catch (error) {
    }
    return isLoggedIn
  },
}
