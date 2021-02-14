<template>
  <div>
    <v-container>
      <v-row justify="center" align="center">
        <v-col cols="12" sm="8" md="6">
          <v-btn
            @click="getFreeMessage">
            getFreeMessage
          </v-btn>
          <v-btn
            @click="getRestrictedMessage">
            getRestrictedMessage
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
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
      loginId: '',
      password: ''
    }
  },
  methods: {
    getFreeMessage: async function () {
      let ret = await this.$axios.$get('http://localhost:8080/api/free')
      console.log(ret)
    },
    getRestrictedMessage: async function () {
      try {
        let ret = await this.$axios.$get('http://localhost:8080/api/restricted')
        console.log(ret)
      } catch (error) {
        console.log(error)
      }
    },
    login: async function() {
      try {
        const params = new URLSearchParams();
        params.append('username', this.loginId);
        params.append('password', this.password);
        let ret = await this.$axios.$post('http://localhost:8080/api/login', params)
        console.log(ret)
      } catch (error) {
        console.log(error)
      }
    }
  }
}
</script>
