<template>
  <div class="api">
    <div>
      <input type="text" v-model="muscle" placeholder="Enter muscle">
      <button @click="fetchWorkouts">Fetch Workouts</button>
      <div v-if="workouts">
        <div v-for="(workout, index) in workouts" :key="index">
          <h3>{{ workout.WorkOut }}</h3>
          <p>Muscle: {{ workout.Muscles }}</p>
          <p>Intensity Level: {{ workout.Intensity_Level }}</p>
          <p v-if="workout['Beginner Sets']">Beginner Sets: {{ workout['Beginner Sets'] }}</p>
          <p v-if="workout['Intermediate Sets']">Intermediate Sets: {{ workout['Intermediate Sets'] }}</p>
          <p v-if="workout['Expert Sets']">Expert Sets: {{ workout['Expert Sets'] }}</p>
          <button @click="showDescription(workout)">Show Description</button>
          <div v-if="selectedWorkout && selectedWorkout.WorkOut === workout.WorkOut">
            <p>Description: {{ selectedWorkout.Explaination }}</p>
            <p>Long Description: {{ selectedWorkout['Long Explanation'] }}</p>
            <p>Video: <a :href="selectedWorkout.Video" target="_blank">{{ selectedWorkout.WorkOut }}</a></p>
          </div>
        </div>
      </div>
    </div>
    <div>
      <h3>Filter by Intensity Level:</h3>
      <button v-for="level in intensityLevels" :key="level" @click="filterByIntensity(level)">
        {{ level }}
      </button>
    </div>
  </div>
</template>

<script>
import WorkoutApiService from '../services/WorkoutApiService';

export default {
  data() {
    return {
      muscle: '',
      workouts: null,
      selectedWorkout: null,
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
        console.log(this.workouts);
      } catch (error) {
        console.error('Error fetching workouts:', error);
      }
    },
    showDescription(workout) {
      this.selectedWorkout = workout;
    },
    filterByIntensity(intensityLevel) {
      if (intensityLevel === 'Beginner') {
        this.workouts = this.workouts.filter(workout => workout.Intensity_Level);
      } else {
        this.workouts = this.workouts.filter(workout => workout.Intensity_Level === intensityLevel);
      }
    }
  }
};
</script>

