<template>
  <div>
    <v-container>
      <v-row justify="center" align="center">
        <v-col cols="12" sm="8" md="6">
          Hello, free area! message = {{ this.message }}
          <v-btn
            @click="logout">
            Logout
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
      message: ''
    }
  },
  mounted: function () {
    this.getFreeMessage()
  },
  methods: {
    getFreeMessage: async function () {
      let ret = await this.$axios.$get('http://localhost:8080/api/free')
      this.message = ret.message
    },
    logout: function () {
      this.$axios.$post('http://localhost:8080/api/logout')
    },
  }
}
</script>
