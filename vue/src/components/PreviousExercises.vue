<template>
	<main v-if="exercises.length > 0">
		<h1>Previous Exercises</h1>
		<div
			v-for="exercise in exercises"
			:key="exercise.exerciseId"
			class="card"
		>
			<h3 class="custom-font">{{ exercise.exerciseName }}</h3>

			<div class="custom-font card-content">
				<p>Duration:</p>
				<p>{{ exercise.exerciseDurationMinutes }} Minutes</p>
			</div>

			<div class="custom-font card-content">
				<p>Sets:</p>
				<p>{{ exercise.sets }}</p>
			</div>

			<div class="custom-font card-content">
				<p>Reps:</p>
				<p>{{ exercise.reps }}</p>
			</div>

			<div class="custom-font card-content">
				<p>Weight (LBS):</p>
				<p>{{ exercise.weightLbs }}</p>
			</div>

      <svg @click="deleteExercise(exercise.exerciseId)" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash trash-icon"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path></svg>
		</div>
	</main>
</template>

<script>
import ExerciseService from '../services/ExerciseService'

export default {
	data() {
		return {
			exercises: [],
		}
	},

	mounted() {
		ExerciseService.getExercises().then(response => {
			this.exercises = response.data.reverse()
		})
	},

  methods: {
    deleteExercise(exerciseId) {
      ExerciseService.deleteExercise(exerciseId).then(() => {
        this.exercises = this.exercises.filter(exercise => exercise.exerciseId !== exerciseId)
      })
    }
  }
}
</script>

<style scoped>
.custom-font {
	font-family: 'M PLUS 1 Code', monospace;
	font-optical-sizing: auto;
	font-weight: 600;
	font-style: normal;
  font-size: 18px;
}

h3 {
  font-size: 24px !important;
}

.card {
	display: flex;
	flex-direction: column;
	align-items: center;
  position: relative;

  margin-top: 10px;
  margin-bottom: 10px;

	background-color: var(--color-light-blue);
	color: black;
  border-radius: 5px;
}

.card-content {
	width: 200px;
	display: flex;
	justify-content: space-between;
}

.trash-icon {
  cursor: pointer;
  position: absolute;
  top: 10px;
  right: 10px;
}

.trash-icon:hover {
  color: var(--color-red);
}
</style>
