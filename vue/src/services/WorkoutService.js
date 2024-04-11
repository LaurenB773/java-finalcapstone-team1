import axios from 'axios'

const url = '/profile/workouts'

export default {
  
  startWorkout() {
    return axios.put(`${url}/start`)
  },

  endWorkout(workout) {
    return axios.put(`${url}/end`, { body: { workout }})
  },

  getCurrentWorkout() {
    return axios.get(`${url}/current`)
  }
}