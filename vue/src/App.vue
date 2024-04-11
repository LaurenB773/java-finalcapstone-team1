<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'profile' }" v-if="this.$store.state.token != ''">Profile</router-link>

    </div>
    <router-view />
  </div>
</template>

<style>
html {
  background-color: rgb(50, 50, 57);
}
#nav {
  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>
