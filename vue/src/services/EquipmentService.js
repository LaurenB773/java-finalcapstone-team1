import axios from 'axios'

export default {
  getEquipment(id) {
    return axios.get(`/api/equipment/${id}`)
  },

  getAllEquipment() {
    return axios.get('/api/equipment')
  }
}