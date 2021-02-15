<template>
  <v-app>
    <v-app-bar app color="yellow darken-1" dark>
      <v-toolbar-title class="text-capitalize"><img src="../public/bee.png" height="75"></v-toolbar-title>
      <v-spacer></v-spacer>
      <LogoutButton></LogoutButton>
    </v-app-bar>
    <v-main>
      <router-view/> 
    </v-main>
  </v-app>
</template>
<script>
import LogoutButton from '../src/components/LogoutButton.vue'
export default {
  name: 'App',
  components: {
    LogoutButton
  },
  data: () => ({
    //
  }),
};
</script>
<style>
@import '../node_modules/typeface-roboto/index.css'
</style>
