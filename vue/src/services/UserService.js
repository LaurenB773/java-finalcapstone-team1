import axios from "axios";

export default {
  getProfile(userId) {
    return axios.get("/profile", { params: { userId } });
  },
  getAllSchedule() {
    return axios.get("/gym/schedule");
  },
  checkIn(userId) {
    return axios.post("/profile/checkin", userId);
  },
  checkOut(userId) {
    return axios.put("/profile/checkout", userId);
  },
};
