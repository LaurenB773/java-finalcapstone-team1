<template>
  <form @submit.prevent="submitExercise">
    <div>
      <label for="duration">Duration:</label>
      <input
        type="number"
        id="duration"
        v-model.number="dto.exercise.exerciseDurationMinutes"
        placeholder="minutes"
        required
      />
    </div>

    <div>
      <label for="equipment">Equipment:</label>
      <select  name="equipment" v-model="dto.equipmentIds" multiple>
        <option
          v-for="equipment in equipments"
          :key="equipment.equipmentId"
          :value="equipment.equipmentId"
        >
          {{ equipment.equipmentName }}
        </option>
      </select>
    </div>

    <div>
      <label for="weight">Weight:</label>
      <input
        type="number"
        id="weight"
        v-model.number="dto.exercise.weightLbs"
        placeholder="Weight (lbs)"
        required
      />
    </div>

    <div>
      <label for="sets">Sets:&nbsp;&nbsp;&nbsp;&nbsp;</label>
      <input
        type="number"
        id="sets"
        v-model.number="dto.exercise.sets"
        placeholder="sets"
        required
      />
    </div>

    <div>
      <label for="reps">Reps:&nbsp;&nbsp;&nbsp;</label>
      <input
        type="number"
        id="reps"
        v-model.number="dto.exercise.reps"
        placeholder="reps"
        required
      />
    </div>

    <button type="submit">Submit</button>
  </form>
</template>

<script>
import ExerciseService from "../services/ExerciseService";

export default {
  props: {
    workoutName: String,
    equipments: Array,
  },

  data() {
    return {
      dto: {
        exercise: {
          exerciseDurationMinutes: 0,
          weightLbs: 0,
          sets: 0,
          reps: 0,
        },
        equipmentIds: [],
      },
    };
  },

  methods: {
    // todo: Hide the form and submit the exercise
    // move this to workoutDetail and pass the func as a prop to this component
    // this way we can hide the form and submit the exercise from the parent component
    submitExercise() {
      this.dto.exercise.exerciseName = this.workoutName;
      ExerciseService.createExercise(this.dto);

      this.dto = {
        exercise: {
          exerciseDurationMinutes: 0,
          weightLbs: 0,
          sets: 0,
          reps: 0,
        },
        equipmentIds: [],
      };
    },
  }

};
</script>

<style scoped>
  form {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  div {
    width: fit-content;
    display: flex;
    justify-content: space-around;
    gap: 50px;
    padding: 10px;
    align-items: center;
  }

  input {
    background-color: var(--color-light-blue);
    outline: none;
    border: 1px solid transparent;
    border-radius: 2px;
    padding: 10px;
  }

  select {
    background-color: var(--color-light-blue);
    outline: none;
  }

  option {
    background-color: var(--color-light-blue);
    color: var(--color-grey);
  }


  button {
    background-color: var(--color-blue);
    color: var(--color-light-blue);
    border: none;
    border-radius: 5px;
    padding: .5rem 3rem;
    cursor: pointer;
    transition: background-color 200ms;
  }

  button:hover {
    background-color: var(--color-blue-o);
  }
</style>