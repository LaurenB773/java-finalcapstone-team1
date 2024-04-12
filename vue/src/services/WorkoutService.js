import axios from 'axios'

const url = '/profile/workouts'

export default {

  startWorkout() {
    return axios.put(`${url}/start`)
  },

  endWorkout() {
    return axios.put(`${url}/end`)
  },

  getCurrentWorkout() {
    return axios.get(`${url}/current`)
  },

  getWorkouts() {
    return axios.get(url)
  },

  

}