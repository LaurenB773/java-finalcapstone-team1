import axios from 'axios'

export default {
  getEquipment(id) {
    return axios.get(`/gym/equipment/${id}`)
  },

  getAllEquipment() {
    return axios.get('/gym/equipment')
  },
  addEquipment(equipment){
    return axios.post(`/gym/equipment`, equipment)
  },
  deleteEquipment(id){
    return axios.delete(`/gym/equipment/${id}`)
  }
}