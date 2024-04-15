import axios from "axios";

export default {
  getProfile(userId) {
    return axios.get("/profile", { params: { userId } });
  },

  getSchedule() {
    return axios.get("/gym/schedule");
  },

  checkIn() {
    return axios.post("/profile/checkin");
  },
  checkOut() {
    return axios.put("/profile/checkout");
  },
  getLastCheckin() {
    return axios.get("/profile/checkin");
  },
};
