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
        const params = new URLSearchParams();
        params.append('username', this.loginId);
        params.append('password', this.password);
        let response = await this.$axios.$post('http://localhost:8080/api/login', params)
        console.log(response)

        this.$router.push('/restricted')
      } catch (error) {
        console.log(error)
      }
    }
  }
}
</script>
