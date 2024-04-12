<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link class="home-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="sign-in-link" v-bind:to="{ name: 'login' }" v-if="this.$store.state.token === ''">Sign In</router-link>
      <router-link class="logout-link" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>&nbsp;&nbsp;
      <router-link class="profile-link" v-bind:to="{ name: 'profile' }" v-if="this.$store.state.token != '' && isUser()">Profile</router-link>
      <router-link class="employee-link" v-bind:to="{ name: 'employee' }"
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

.top-right {
  top: 0;
  right: 0;
}

#nav {
  background-color: hsl(200, 2%, 61.4%, 0.5);
  width: 50%;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  display: flex;
  gap: 10px;
  padding-bottom: 20px;
  justify-content: center;
  margin: auto;
}

#capstone-app {
  background-image: url("../src/assets/login.png");
  background-size: cover;
  height: 100vh;
}

.home-link {
    color: var(--color-blue);
  }
.sign-in-link{
  color: var(--color-blue);
}
</style>
