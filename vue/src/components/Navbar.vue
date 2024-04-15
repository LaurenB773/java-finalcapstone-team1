<template>
  <nav>
    <router-link class="link font" v-bind:to="{ name: 'home' }">
      Home
    </router-link>

    <router-link class="link font" v-bind:to="{ name: this.$route.path === '/register' ? 'login' : 'register' }"
      v-if="this.$store.state.token === ''">
      {{ this.$route.path === '/register' ? 'Sign in' : 'Sign up' }}
    </router-link>

    <router-link class="link font" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">
      Logout
    </router-link>

    <router-link class="link font" v-bind:to="{ name: 'profile' }"
      v-if="this.$store.state.token != '' && (isUser() || isEmployee())">
      Profile
    </router-link>

    <router-link class="link font" v-bind:to="{ name: 'employee' }"
      v-if="this.$store.state.token != '' && (isEmployee() || isOwner())">
      Manage Gym
    </router-link>
  </nav>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["userPermissions"]),
  },
  mounted() {
    console.log(this.userPermissions);
  },
  methods: {
    isUser() {
      let authorities = [];
      authorities = this.userPermissions;

      if (authorities.some((authority) => authority.name === "ROLE_USER")) {
        console.log("user is a user");
        return true;
      }
      console.log("user role not found");
      return false;
    },
    isEmployee() {
      let authorities = [];
      authorities = this.userPermissions;

      if (authorities.some((authority) => authority.name === "ROLE_EMPLOYEE")) {
        return true;
      }
      return false;
    },
    isOwner() {
      let authorities = [];
      authorities = this.userPermissions;

      if (authorities.some((authority) => authority.name === "ROLE_ADMIN")) {
        return true;
      }
      return false;
    },
  },
};
</script>

<style scoped>
nav {
  width: 50%;
  margin: auto;

  display: flex;
  justify-content: space-around;

  background-color: var(--color-grey);
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;

  padding: 10px;
  text-align: center;
}

.link {
  color: var(--color-light-blue);
  text-decoration: none;
  font-size: 18px;
}

.link:hover {
  color: var(--color-blue);
}
</style>
