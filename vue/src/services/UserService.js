import axios from "axios";

export default {
  getProfile(username) {
    return axios.get("/profile", { params: { username } });
  },
};
