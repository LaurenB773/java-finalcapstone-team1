<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token === ''">Sign In</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'profile' }" v-if="this.$store.state.token != '' && isUser()">Profile</router-link>
      <router-link v-bind:to="{ name: 'employee' }"
        v-if="this.$store.state.token != '' && (isEmployee() || isOwner())">Manage Gym</router-link>

    </div>
    <router-view />
  </div>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&display=swap"
    rel="stylesheet">
</template>

<style>
/* html {
  background-color: black;
} */
#nav {
  background-color: hsl(218, 10%, 34.9%);

  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>
