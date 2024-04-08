<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <home-page />
  </div>
</template>

<script>

import HomePage from '../components/HomePage.vue';

export default {
  components: {
    HomePage
  }
};
</script>
