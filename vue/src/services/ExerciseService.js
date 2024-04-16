import axios from 'axios'

const url = '/profile/exercises'

export default {
  getExercises() {
    return axios.get(url)
  },

  createExercise(dto) {
    return axios.post(url, dto)
  },

  deleteExercise(id) {
    return axios.delete(`${url}/${id}`)
  }
}