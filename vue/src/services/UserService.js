import axios from "axios";

export default {
  getProfile(username) {
    return axios.get("/profile", { params: { username } });
  },
  getAllSchedule() {
    return axios.get("/gym/schedule");
  },
};
