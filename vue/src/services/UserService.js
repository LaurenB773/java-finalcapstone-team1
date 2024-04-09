import axios from "axios"

export default {
  getProfile(userId) {
    return axios.get("/profile", { params: { userId } })
  },
  getAllSchedule() {
    return axios.get("/gym/schedule")
  },
}
