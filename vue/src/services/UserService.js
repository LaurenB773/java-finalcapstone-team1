import axios from "axios";

export default {
  getProfile(userId) {
    return axios.get("/profile", { params: { userId } });
  },

  editProfile(profile) {
    axios.put('/profile', profile)
  },

  deleteProfile() {
    axios.delete('/profile')
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
  getCheckins() {
    return axios.get("/profile/checkins")
  },
  getLastCheckin() {
    return axios.get("/profile/checkin");
  },
  getEventsSignedUpFor() {
    return axios.get("/gym/schedule/me");
  },
  signUpForEvent(id) {
    axios.post(`/gym/schedule/${id}/join`);
  },
  leaveEvent(id) {
    axios.delete(`/gym/schedule/${id}/join`);
  },
  getWorkouts() {
    return axios.get("/profile/workouts");
  },
};
