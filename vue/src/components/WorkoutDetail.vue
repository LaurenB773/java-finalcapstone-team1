<template>
  <h1>Search Workouts</h1>
  <div class="api">
    <div>
      <input type="text" v-model="muscle" placeholder="Enter muscle" />
      <button @click="fetchWorkouts">Fetch Workouts</button>
      <div>
        <h3>Filter by Intensity Level:</h3>
        <button
          v-for="level in intensityLevels"
          :key="level"
          @click="filterByIntensity(level)"
        >
          {{ level }}
        </button>
      </div>
      <div v-if="copyOfWorkouts" class="workout-container">
        <div v-for="(workout, index) in copyOfWorkouts" :key="index">
          <h3>Workouts: {{ workout.WorkOut }}</h3>
          <p>Equipment: {{ workout.Equipment }}</p>
          <p>Muscle: {{ workout.Muscles }}</p>
          <p>Intensity Level: {{ workout.Intensity_Level }}</p>
          <p v-if="workout[intensityLevel + ' Sets']">
            {{ intensityLevel }} Sets: {{ workout[intensityLevel + " Sets"] }}
          </p>
          <button @click="toggleDescription(index)">Show Description</button>
          <div v-if="isDescriptionShown[index]">
            <p>Description: {{ workout["Long Explanation"] }}</p>
            <p>
              Video:
              <a :href="workout.Video" target="_blank">{{ workout.WorkOut }}</a>
            </p>
          </div>
          <button @click="toggleForm(index)">Add Exercise</button>

          <div v-if="showForm[index]">
            <h2>Add Exercise Details</h2>
            <form @submit.prevent="submitExercise(index)">
              <label for="equipment">Equipment:</label>
              <select name="equipment">
                <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">
                  {{ equipment.equipmentName }}
                </option>
              </select>

              <label for="weight">Weight:</label>
              <input
                type="number"
                id="weight"
                v-model.number="exercise[index].weight"
                placeholder="Weight (lbs)"
                required
              />

              <label for="sets">Sets:</label>
              <input
                type="number"
                id="sets"
                v-model.number="exercise[index].sets"
                placeholder="sets"
                required
              />

              <label for="reps">Reps:</label>
              <input
                type="number"
                id="reps"
                v-model.number="exercise[index].reps"
                placeholder="reps"
                required
              />

              <button type="submit">Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService";
import WorkoutApiService from "../services/WorkoutApiService";
import EquipmentService from "../services/EquipmentService";
import ExerciseService from "../services/ExerciseService";

export default {
  data() {
    return {
      muscle: "",
      workouts: [],
      copyOfWorkouts: [],
      intensityLevels: ["Beginner", "Intermediate", "Expert"],
      intensityLevel: "",
      isDescriptionShown: [],
      showForm: [],
      exercise: [],
      equipments: [],
    };
  },
  mounted() {
    EquipmentService.getAllEquipment().then(response => {
      this.equipments = response.data
    })
  },
  methods: {
    async fetchWorkouts() {
      try {
        if (!this.muscle) {
          console.error("Muscle parameter is required");
          return;
        }
        this.workouts = await WorkoutApiService.fetchWorkoutsByMuscle(
          this.muscle
        );
        this.copyOfWorkouts = [...this.workouts];

        this.isDescriptionShown = new Array(this.workouts.length).fill(false);
        this.showForm = new Array(this.workouts.length).fill(false);
        this.exercise = new Array(this.workouts.length).fill({
          weight: "",
          reps: "",
          sets: "",
        });
      } catch (error) {
        console.error("Error fetching workouts:", error);
      }
    },
    filterByIntensity(intensityLevel) {
      this.intensityLevel = intensityLevel;
      this.copyOfWorkouts = this.workouts.filter(
        (workout) => workout.Intensity_Level === intensityLevel
      );
    },
    toggleDescription(index) {
      this.isDescriptionShown[index] = !this.isDescriptionShown[index];
    },
    toggleForm(index) {
      this.showForm[index] = !this.showForm[index];
    },
    submitExercise(index) {
    },
  },
};
</script>

<style>
.workout-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>
