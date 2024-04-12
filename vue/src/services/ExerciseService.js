import axios from 'axios'

const url = '/profile/exercises'

export default {
  createExercise(dto) {
    return axios.post(url, dto)
  }
}