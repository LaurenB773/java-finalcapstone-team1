import axios from 'axios'

const url = '/profile/workouts'

export default {
  
  startWorkout() {
    return axios.put(`${url}/start`)
  },

  endWorkout(workoutId) {
    return axios.put(`${url}/end`, { workoutId })
  },

  getCurrentWorkout() {
    return axios.get(`${url}/current`)
  }
}