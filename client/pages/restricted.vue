<template>
  <div>
    <v-container>
      <v-row justify="center" align="center">
        <v-col cols="12" sm="8" md="6">
          Hello, restricted area!
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
    }
  },
  mounted: function () {
  },
  methods: {
  }
}
</script>
