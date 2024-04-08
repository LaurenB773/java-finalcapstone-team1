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

<!-- <template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div>
      <button @click="fetchChestWorkouts">Fetch Chest Workouts</button>
      <div v-if="chestWorkouts">
        <div v-for="(workout, index) in chestWorkouts" :key="index">
          <h3>{{ workout.WorkOut }}</h3>
          <p>Muscle: {{ workout.Muscles }}</p>
          <p>Intensity Level: {{ workout.Intensity_Level }}</p>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      chestWorkouts: null
    };
  },
  methods: {
    async fetchChestWorkouts() {
      try {
        const response = await axios.get('https://work-out-api1.p.rapidapi.com/search', {
          params: { Muscles: 'chest' }, 
          headers: {
            'X-RapidAPI-Key': '4270d1472cmshb8fe5d0cc5b24bep1f6ad5jsn96cddf245e81',
            'X-RapidAPI-Host': 'work-out-api1.p.rapidapi.com'
          }
        });
        this.chestWorkouts = response.data;
        console.log(this.chestWorkouts);
      } catch (error) {
        console.error('Error fetching chest workouts:', error);
      }
    }
  }
};
</script> -->

