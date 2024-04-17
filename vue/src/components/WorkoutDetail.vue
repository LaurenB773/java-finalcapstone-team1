<template>
  <main>
    <h1>Search Exercises</h1>

    <!-- <div class="search-container">
      <input type="text" v-model="exerciseName" placeholder="Enter Exercise Name" />
      <button @click="fetchWorkouts">Fetch Exercises</button>
    </div> -->
    <div class="exercise-container">
      <img @click="fetchWorkouts('Biceps')" src="../../public/CAPSTONE MUSCLE ICONS/1.png" alt="">
      <img @click="fetchWorkouts('Triceps')" src="../../public/CAPSTONE MUSCLE ICONS/2.png" alt="">
      <img @click="fetchWorkouts('Trapezius')" src="../../public/CAPSTONE MUSCLE ICONS/3.png" alt="">

      <img @click="fetchWorkouts('Lats')" src="../../public/CAPSTONE MUSCLE ICONS/5.png" alt="">
      <img @click="fetchWorkouts('Chest')" src="../../public/CAPSTONE MUSCLE ICONS/8.png" alt="">
      <img @click="fetchWorkouts('Quadriceps')" src="../../public/CAPSTONE MUSCLE ICONS/4.png" alt="">
      <img @click="fetchWorkouts('Hamstring')" src="../../public/CAPSTONE MUSCLE ICONS/6.png" alt="">


      <img @click="fetchWorkouts('Glutes')" src="../../public/CAPSTONE MUSCLE ICONS/10.png" alt="">
      <img @click="fetchWorkouts('Abs')" src="../../public/CAPSTONE MUSCLE ICONS/9.png" alt="">

      <img @click="fetchWorkouts('Calves')" src="../../public/CAPSTONE MUSCLE ICONS/7.png" alt="">
      <img @click="fetchWorkouts('Warm up')" src="../../public/CAPSTONE MUSCLE ICONS/11.png" alt="">
      <img @click="fetchWorkouts('Stretching')" src="../../public/CAPSTONE MUSCLE ICONS/12.png" alt="">
    </div>
    <div v-if="copyOfWorkouts.length > 0">
      <h3>Filter by Intensity Level</h3>
      <div class="filter-button-container">
        <button v-for="level in intensityLevels" :key="level" @click="filterByIntensity(level)">
          {{ level }}
        </button>
      </div>
    </div>

    <!-- todo: put this into a new component -->
    <div v-if="copyOfWorkouts" class="workout-container">
      <div v-for="(workout, index) in copyOfWorkouts" :key="index" class="workout-card">
        <h3>{{ workout.WorkOut }}</h3>
        <p v-if="workout.Equipment"><strong>Equipment:</strong> {{ workout.Equipment }}</p>
        <p><strong>Muscle:</strong> {{ workout.Muscles }}</p>
        <p><strong>Intensity Level:</strong> {{ workout.Intensity_Level }}</p>
        <p v-if="workout[intensityLevel + ' Sets']">
          {{ intensityLevel }} Sets: {{ workout[intensityLevel + " Sets"] }}
        </p>

        <button @click="toggleDescription(index)">Show Description</button>
        <div v-if="isDescriptionShown[index]">
          <p>Description: {{ workout["Long Explanation"] }}</p>
          <p>
            Video:
            <a :href="workout.Video" target="_blank">
              {{ workout.WorkOut }}
            </a>
          </p>
        </div>

        <button @click="toggleForm(index)">Add Exercise</button>

        <div v-if="showForm[index]">
          <CreateExerciseForm :workoutName="workout.WorkOut" :equipments="equipments" />
        </div>
      </div>
    </div>

  </main>
</template>

<script>
import WorkoutApiService from "../services/WorkoutApiService";
import EquipmentService from "../services/EquipmentService";
import CreateExerciseForm from "./CreateExerciseForm.vue";

export default {
  components: {
    CreateExerciseForm,
  },

  data() {
    return {
      exerciseName: "",
      workouts: [],
      copyOfWorkouts: [],
      intensityLevels: ["Beginner", "Intermediate", "Expert"],
      intensityLevel: "",
      isDescriptionShown: [],
      showForm: [],
      exercise: [],
      equipments: [],
      selectedEquipment: {},
    };
  },

  mounted() {
    EquipmentService.getAllEquipment().then((response) => {
      this.equipments = response.data;
    });
  },

  methods: {
    async fetchWorkouts(muscle) {
      try {

        this.workouts = await WorkoutApiService.fetchWorkoutsByMuscle(
          muscle
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
  },
};
</script>

<style>
main {
  color: var(--color-light-blue);
  width: 50%;
  margin: auto;
  margin-top: 50px;
  text-align: center;
}

.search-container {
  display: flex;
  flex-direction: column;
  width: fit-content;
  margin: auto;
  gap: 10px;
}

.search-container input {
  background-color: var(--color-light-blue);
  padding: 10px;
  border: 1px solid transparent;
  border-radius: 5px;
  outline: none;
}

.search-container button {
  background-color: var(--color-blue);
  color: var(--color-light-blue);
  padding: 5px 10px;
  border: 1px solid transparent;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 200ms;
}

.search-container button:hover {
  background-color: var(--color-blue-o);
}

.filter-button-container {
  display: flex;
  gap: 10px;
  justify-content: center;
}

.filter-button-container button {
  background-color: var(--color-grey);
  color: var(--color-light-blue);
  padding: 5px 10px;
  border: 1px solid transparent;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 200ms;
}

.filter-button-container button:hover {
  background-color: var(--color-grey-o);
}

.workout-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 20px;
}

.workout-card {
  border: 1px solid var(--color-grey);
  border-radius: 5px;
  padding: 10px;
  width: 50%;
  margin: auto;
}

.workout-card h3 {
  font-size: 20px;
}

.workout-card p {
  font-size: 18px;
}

.workout-card a {
  color: var(--color-blue);
  text-decoration: none;
}

.workout-card a:hover {
  color: var(--color-blue-o);
}

.workout-card button {
  background-color: var(--color-light-blue);
  color: var(--color-grey);
  font-size: 14px;
  font-weight: 600;
  padding: 5px 10px;
  border: 1px solid transparent;
  border-radius: 5px;
  cursor: pointer;
  margin: 10px;
  transition: background-color 200ms;
}

.workout-card button:hover {
  background-color: var(--color-light-blue-o);
}

@media screen and (max-width: 720px) {
  .workout-card {
    width: 100%;
  }
}

.exercise-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(3, auto);
  gap: 10px;
  max-width: 800px;
  margin: 0 auto;
}

.exercise-container img {
  width: 100%;
  height: 100%;

}

.exercise-container img:hover {
  filter: invert(100%) grayscale(100%);
  cursor: pointer;

}
</style>
