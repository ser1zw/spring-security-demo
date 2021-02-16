export default function ({ store, redirect }) {
  store.dispatch('auth/isLoggedIn')
    .then((isLoggedIn) => {
      console.log(`isLoggedIn? = ${isLoggedIn}`)
      if (!isLoggedIn) {
        redirect('/login')
      }
    })
}
