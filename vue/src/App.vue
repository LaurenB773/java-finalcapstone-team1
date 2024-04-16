<template>
  <img class="logo-pic" src="./assets/OfficialLogo(1).png" alt="logo">
  <navbar />
  <router-view />
</template>

<script>
import Navbar from "./components/Navbar.vue";

export default {
  components: { Navbar },
};
</script>

<style>
body {
  background-image: url("../src/assets/login.png");
  background-size: cover;
}

.font {
  font-family: "M PLUS 1 Code", monospace;
  font-optical-sizing: auto;
  font-weight: 600;
  font-style: normal;

  color: var(--color-light-blue);
  text-align: center;
}

.logo-pic {
  margin-top: -15px;
  position: absolute;
  left: 0;
  top: 0;
  width: 150px;
  height: 170px;
}
</style>
