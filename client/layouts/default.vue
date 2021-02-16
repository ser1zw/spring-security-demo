<template>
  <v-app>
    <v-main>
      <v-container>
        <nuxt-link to="/">[Top page]</nuxt-link>
        <nuxt-link to="/login">[Login page]</nuxt-link>
        <nuxt-link to="/restricted">[Restricted page]</nuxt-link>
        <h1 v-if="$store.state.auth.isLoggedIn">Status = Login</h1>
        <h1 v-else>Status = Logout</h1>
        <nuxt />
      </v-container>
    </v-main>
  </v-app>
</template>
