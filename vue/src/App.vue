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
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['userPermissions'])
  },
  mounted() {
    console.log(this.userPermissions);
  },
  methods: {
    isUser() {
      let authorities = [];
      authorities = this.userPermissions;


      if (authorities.some(authority => authority.name === 'ROLE_USER')) {
        console.log('user is a user')
        return true;
      }
      console.log('user role not found')
      return false;
    },
    isEmployee() {
      let authorities = [];
      authorities = this.userPermissions;


      if (authorities.some(authority => authority.name === 'ROLE_EMPLOYEE')) {

        return true;
      }
      return false;
    },
    isOwner() {
      let authorities = [];
      authorities = this.userPermissions;


      if (authorities.some(authority => authority.name === 'ROLE_ADMIN')) {
        return true;
      }
      return false;
    }
  }
}
</script>

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
