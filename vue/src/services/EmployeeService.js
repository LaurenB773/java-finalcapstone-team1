import axios from "axios";

export default {
  createEvent(event) {
    axios.post("/gym/schedule", event);
  },
  removeEvent(id) {
    axios.delete(`/gym/schedule/${id}`);
  },
  updateEvent(event, id) {
    axios.put(`/gym/schedule/${id}`, event);
  },
  getAllMembers() {
    return axios.get("/gym/members");
  },
  checkMemberIn(id) {
    axios.post(`/gym/members/${id}`);
  },
  checkMemberOut(id) {
    axios.put(`/gym/members/${id}`);
  },
  makeMemberEmployee(id) {
    axios.put(`/gym/members/${id}/hire`);
  },
  fireEmployee(id) {
    axios.put(`/gym/members/${id}/fire`);
  },
  banMember(id) {
    axios.put(`/gym/members/${id}/ban`);
  },
  getEmployees() {
    return axios.get(`/gym/employees`);
  },
  getMembersInEvent(id) {
    return axios.get(`/schedule/${id}/members`);
  },
  getAllEquipment() {
    return axios.get(`/gym/equipment`);
  },
};
