<template>
  <h1>Search Workouts</h1>
  <div class="api">
    <div>
      <input type="text" v-model="muscle" placeholder="Enter muscle">
      <button @click="fetchWorkouts">Fetch Workouts</button> 
      <div>
      <h3>Filter by Intensity Level:</h3>
      <button v-for="level in intensityLevels" :key="level" @click="filterByIntensity(level)">
        {{ level }}
      </button>
    </div>
      <div v-if="copyOfWorkouts" class="workout-container">
        <div v-for="(workout, index) in copyOfWorkouts" :key="index">
          <h3>{{ workout.WorkOut }}</h3>
          <p>Muscle: {{ workout.Muscles }}</p>
          <p>Intensity Level: {{ workout.Intensity_Level }}</p>
          <p v-if="workout['Beginner Sets']">Beginner Sets: {{ workout['Beginner Sets'] }}</p>
          <p v-if="workout['Intermediate Sets']">Intermediate Sets: {{ workout['Intermediate Sets'] }}</p>
          <p v-if="workout['Expert Sets']">Expert Sets: {{ workout['Expert Sets'] }}</p>
          <button @click="showDescription">Show Description</button>
          <div v-if="isDescriptionShown">
            <p>Description: {{ workout['Long Explanation'] }}</p>
            <p>Video: <a :href="workout.Video" target="_blank">{{ workout.WorkOut }}</a></p>
          </div>
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
      workouts: [],
      copyOfWorkouts: [],
      selectedWorkout: {},
      isDescriptionShown: false,
      intensityLevels: ['Beginner', 'Intermediate', 'Expert'] 
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
        this.copyOfWorkouts = [...this.workouts]
      } catch (error) {
        console.error('Error fetching workouts:', error);
      }
    },
    showDescription() {
      this.isDescriptionShown = !this.isDescriptionShown
    },
    filterByIntensity(intensityLevel) {
      this.copyOfWorkouts = this.workouts.filter(workout => workout.Intensity_Level === intensityLevel);
    },

  }
};
</script>

<style>
.workout-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>
