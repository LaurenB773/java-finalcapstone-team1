import axios from 'axios'

export default {
  getEquipment(id) {
    return axios.get(`/gym/equipment/${id}`)
  },

  getAllEquipment() {
    return axios.get('/gym/equipment')
  }
}