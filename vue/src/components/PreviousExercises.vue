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
</style>
