<template>
  <div class="profile-container">
    <h2>
      Name: {{ this.userProfile.firstName }} {{ this.userProfile.lastName }}
    </h2>

    <!-- TODO <img src="" alt="Profile-Picture" /> -->

    <h2>Goals: {{ this.userProfile.goal }}</h2>

    <div class="options-container">
      <button @click="checkInOrOut">
        {{ isCheckedIn ? "Check Out" : "Check In" }}
      </button>

      <button v-if="isCheckedIn" @click="logWorkout">
        {{ isWorkoutStarted ? "End Workout" : "Start Workout" }}
      </button>

      <router-link
        v-bind:to="{ name: 'workouts' }"
        @click="searchWorkouts"
      >
        <button>Search Exercises</button>
      </router-link>

      <button @click="isShowingPreviousExercises = !isShowingPreviousExercises">
        Previous Exercises
      </button>

      <button @click="isShowingSignedUpEvents = !isShowingSignedUpEvents">
        Signed Up Events
      </button>

    </div>

    <PreviousExercises v-if="isShowingPreviousExercises" />
    
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserService from "../services/UserService";
import WorkoutService from "../services/WorkoutService";
import { RouterLink } from "vue-router";
import PreviousExercises from "../components/PreviousExercises.vue";

export default {
  components: {
    RouterLink,
    PreviousExercises
  },
  data() {
    return {
      userProfile: {
        userId: 0,
        firstName: "",
        lastName: "",
        email: "",
        profilePicture: "",
        goal: "",
      },
      isCheckedIn: false,
      isWorkoutStarted: false,
      isShowingPreviousExercises: false,
      isShowingSignedUpEvents: false,
    };
  },

  mounted() {
    UserService.getProfile().then((res) => (this.userProfile = res.data));
    UserService.getLastCheckin().then((response) => (this.isCheckedIn = response.data));
    WorkoutService.getCurrentWorkout().then((response) => (this.isWorkoutStarted = response.data ? true : false));
  },

  computed: {
    ...mapState(["user"]),
  },

  methods: {
    checkInOrOut() {
      if (this.isCheckedIn) {
        UserService.checkOut();
        this.isCheckedIn = false;
      } else {
        UserService.checkIn();
        this.isCheckedIn = true;
      }
    },

    logWorkout() {
      if (this.isWorkoutStarted) {
        WorkoutService.endWorkout();
        this.isWorkoutStarted = false;
      } else {
        WorkoutService.startWorkout();
        this.isWorkoutStarted = true;
      }
    },
  },
};
</script>

<style scoped>
.profile-container {
  color: var(--color-light-blue);
  width: 50%;
  margin: auto;
  margin-top: 50px;
}

.profile-container h2 {
  font-weight: 400;
}

.options-container {
  display: flex;
  gap: 20px;
}

button {
  background-color: var(--color-grey);
  color: var(--color-light-blue);
  border: none;
  border-radius: 5px;
  padding: 10px;
  cursor: pointer;
  transition: background-color 200ms;
}

button:hover {
  background-color: var(--color-light-blue);
  color: var(--color-grey);
}
</style>
