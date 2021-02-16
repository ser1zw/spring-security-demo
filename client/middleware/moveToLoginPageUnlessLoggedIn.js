export default function ({ store, redirect }) {
  store.dispatch('auth/isLoggedIn')
    .then((isLoggedIn) => {
      if (!isLoggedIn) {
        redirect('/login')
      }
    })
}
