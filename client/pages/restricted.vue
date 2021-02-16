<template>
  <div>
    <v-container>
      <v-row justify="center" align="center">
        <v-col cols="12" sm="8" md="6">
          Hello, restricted area! message = {{ this.message }}
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  middleware: 'moveToLoginPageUnlessLoggedIn',
  data () {
    return {
      message: ''
    }
  },
  mounted: function () {
    this.getRestrictedMessage()
  },
  methods: {
    getRestrictedMessage: async function () {
      try {
        let ret = await this.$axios.$get('http://localhost:8080/api/restricted')
        this.message = ret.message
      } catch (error) {
        console.log(error)
      }
    },
  }
}
</script>
