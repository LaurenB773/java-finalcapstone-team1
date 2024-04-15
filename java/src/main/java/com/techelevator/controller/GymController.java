package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.CheckinDao;
import com.techelevator.security.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.ScheduleDao;
import com.techelevator.dao.UserProfileDao;

import com.techelevator.model.Equipment;
import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;
import com.techelevator.security.dao.UserDao;

@RestController
@CrossOrigin
@RequestMapping("/gym")
public class GymController {

  private UserDao userDao;
  private UserProfileDao userProfileDao;
  private EquipmentDao equipmentDao;
  private ScheduleDao scheduleDao;
  private CheckinDao checkinDao;

  public GymController(UserProfileDao userProfileDao, EquipmentDao equipmentDao, ScheduleDao scheduleDao, CheckinDao checkinDao, UserDao userDao) {
    this.userProfileDao = userProfileDao;
    this.equipmentDao = equipmentDao;
    this.scheduleDao = scheduleDao;
    this.checkinDao = checkinDao;
    this.userDao = userDao;
  }

  // todo: talk about this
  @GetMapping("/equipment")
  // @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public List<Equipment> getAllEquipment() {
    return equipmentDao.getAllEquipment();
  }

  @GetMapping("/equipment/{id}")
  // @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public Equipment getEquipment(int id) {
    return equipmentDao.getEquipmentById(id);
  }

  @GetMapping("/members")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public List<UserProfile> getMembers(Principal principal) {
    return userProfileDao.getMembers();
  }

  @PostMapping("/members/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void checkMemberIn(@PathVariable int id) {
    checkinDao.checkin(id);
  }
  @PutMapping("/members/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void checkMemberOut(@PathVariable int id) {
    checkinDao.checkOut(id);
  }

//  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
//  @GetMapping("/members/{id}")
//  public UserProfile getMember(int id) {
//    return userProfileDao.getProfile(id);
//  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @PutMapping("/members/{id}/ban")
  public void banMember(@PathVariable int id) {
    userDao.banMember(id);
  }
  @GetMapping("/schedule")
  public List<Schedule> getAllSchedules() {
    return scheduleDao.getAllSchedules();
  }

  @GetMapping("/schedule/{id}")
  public Schedule getSchedule(int id) {
    return scheduleDao.getSchedule(id);
  }

  @PostMapping("/schedule")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void createSchedule(@RequestBody Schedule scheduleToCreate) {
    scheduleDao.createSchedule(scheduleToCreate);
  }

  @PutMapping("/schedule/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void updateSchedule(@RequestBody Schedule scheduleToUpdate, @PathVariable int id) {
    scheduleDao.updateSchedule(scheduleToUpdate, id);
  }

  @DeleteMapping("/schedule/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void deleteSchedule(@PathVariable int id) {
    scheduleDao.deleteSchedule(id);

  }

  @GetMapping("/schedule/{id}/members")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public List<UserProfile> getMembersInEvent(@PathVariable int id) {
    return scheduleDao.getSignedUpMembers(id);
  }

  @PostMapping("/schedule/{id}/join")
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void signUpForEvent(Principal principal, @PathVariable int id) {
    User signedIn = userDao.getUserById(getUserId(principal));
    scheduleDao.addMemberToScheduledEvent(signedIn, id);
  }

  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @GetMapping("/schedule/me")
  public List<Schedule> getSignedUpEvents(Principal principal) {
    int userId = getUserId(principal);
    return scheduleDao.getSignedUpEvents(userId);
  }

  @DeleteMapping("/schedule/{id}/join")
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public void removeMemberFromEvent(Principal principal, @PathVariable int id) {
    int userId = getUserId(principal);
    scheduleDao.removeMemberFromEvent(userId, id);
  }

  // employee commands
  @GetMapping("/employees")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserProfile> getEmployees() {
    return userProfileDao.getEmployees();
  }

  @GetMapping("/members/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  public boolean isMemberCheckedIn(@PathVariable int id) {
    return checkinDao.isCheckin(id);
  }

  @PutMapping("/members/{id}/hire")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public User hireMemberAsEmployee(@PathVariable int id) {
    return userDao.makeUserEmployee(id);
  }

  @PutMapping("/members/{id}/fire")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Employee fired")
  public void fireEmployee(@PathVariable int id) {
    userDao.fireEmployee(id);
  }

  private int getUserId(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);
    return user.getId();
  }
}
