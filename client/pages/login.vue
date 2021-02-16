<template>
  <div>
    <v-container>
      <v-row>
        <v-col cols="4">
          <v-text-field
            v-model="loginId"
            label="Login ID"
          ></v-text-field>
          <v-text-field
            v-model="password"
            label="Password"
            type="password"
          ></v-text-field>
          <v-btn
            @click="login">
            Login
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loginId: 'admin',
      password: 'password'
    }
  },
  methods: {
    login: async function() {
      try {
        await this.$store.dispatch('auth/login', { loginId: this.loginId, password: this.password })
        this.$router.push('/restricted')
      } catch (error) {
        console.log(error)
      }
    }
  }
}
</script>
