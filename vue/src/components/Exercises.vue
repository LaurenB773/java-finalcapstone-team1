<template>
  <div>
    <header v-if="exercises">Exercises</header>
    <div v-for="exercise in exercises" :key="exercise.exerciseId">
      <p>{{ exercise.exerciseName }}</p>
      <p>{{ exercise.exerciseDurationMinutes }} minutes</p>
      <p>{{ exercise.sets }} Sets</p>
      <p>{{ exercise.reps }} Reps</p>
    </div>
  </div>
</template>

<script>
import ExerciseService from "../services/ExerciseService";

export default {

  data() {
    return {
      exercises: []
    };
  },

  mounted() {
    ExerciseService.getExercises().then((response) => {
      this.exercises = response.data;
      console.log(this.exercises)
    });
  },
};
</script>

<style scoped></style>