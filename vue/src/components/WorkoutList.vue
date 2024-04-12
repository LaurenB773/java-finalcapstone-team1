<template>
  <header>Workout List</header>
  <div v-for="workout in workouts" :key="workout.workoutId">
    <p>Start Time :{{ formatTimestamp(workout.startTime) }}</p>
    <p>End Time :{{ formatTimestamp(workout.endTime) }}</p>
  </div>
</template>
<script>
import workoutService from "../services/WorkoutService";
import { formatTimestamp } from "../utils/formatter";

export default {
  data() {
    return {
      workouts: [],
    };
  },

  methods: {
    formatTimestamp,
  },

  mounted() {
    workoutService.getWorkouts().then((response) => {
      this.workouts = response.data;
    });
  },
};
</script>
