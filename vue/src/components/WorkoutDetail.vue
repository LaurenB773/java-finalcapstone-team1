<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div>
      <input type="text" v-model="muscle" placeholder="Enter muscle">
      <button @click="fetchWorkouts">Fetch Workouts</button>
      <div v-if="workouts">
        <div v-for="(workout, index) in workouts" :key="index">
          <h3>{{ workout.WorkOut }}</h3>
          <p>Muscle: {{ workout.Muscles }}</p>
          <p>Intensity Level: {{ workout.Intensity_Level }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import WorkoutApiService from '../services/WorkoutApiService';

export default {
  data() {
    return {
      muscle: '',
      workouts: null
    };
  },
  methods: {
    async fetchWorkouts() {
      try {
        if (!this.muscle) {
          console.error('Muscle parameter is required');
          return;
        }
        this.workouts = await WorkoutApiService.fetchWorkoutsByMuscle(this.muscle);
        console.log(this.workouts);
      } catch (error) {
        console.error('Error fetching workouts:', error);
      }
    }
  }
};
</script>