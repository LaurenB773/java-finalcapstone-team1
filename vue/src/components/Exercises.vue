<template>
  <div>
    <header>Exercises</header>
    <div v-for="exercise in exercises" :key="exercise.exerciseId">
      <p>{{ exercise.exerciseName }}</p>
      <p>{{ exercise.exerciseDurationMinutes }}</p>
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
    });
  },
};
</script>

<style scoped></style>