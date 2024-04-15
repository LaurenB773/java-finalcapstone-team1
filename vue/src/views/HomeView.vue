<template>
  <div class="home">
    <home-page />
  </div>
</template>

<script>
import HomePage from "../components/HomePage.vue";

export default {
  beforeCreate() {
    document.body.classList.add("home");
  },

  beforeUnmount() {
    document.body.classList.remove("home");
  },

  components: {
    HomePage,
  },
};
</script>

<style>
body.home {
  background-image: url("../assets/home.jpg");
  background-size: cover;
}
</style>

<style></style>
