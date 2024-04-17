<template>
  <div class="home">
    <home-page />
    <about />
  </div>
</template>

<script>
import HomePage from "../components/HomePage.vue";
import About from "../components/About.vue"

export default {
  beforeCreate() {
    document.body.classList.add("home");
  },

  beforeUnmount() {
    document.body.classList.remove("home");
  },

  components: {
    HomePage,
    About
  },
};
</script>

<style>
body.home {
  background-image: url("../assets/home.jpg");
  background-size: cover;

  min-height: 100vh;
  display: flex;
}

.home {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>

<style></style>
