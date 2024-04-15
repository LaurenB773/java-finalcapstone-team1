package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.CheckinDao;
import com.techelevator.model.Checkin;
import com.techelevator.security.dao.UserDao;
import com.techelevator.security.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.ScheduleDao;
import com.techelevator.dao.UserProfileDao;

import com.techelevator.model.Equipment;
import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;

@RestController
@CrossOrigin
@RequestMapping("/gym")
public class GymController {

  private UserProfileDao userProfileDao;
  private EquipmentDao equipmentDao;
  private ScheduleDao scheduleDao;

  private CheckinDao checkinDao;

  private UserDao userDao;

  public GymController(UserProfileDao userProfileDao, EquipmentDao equipmentDao, ScheduleDao scheduleDao, CheckinDao checkinDao, UserDao userDao) {
    this.userProfileDao = userProfileDao;
    this.equipmentDao = equipmentDao;
    this.scheduleDao = scheduleDao;
    this.checkinDao = checkinDao;
    this.userDao = userDao;
  }
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @GetMapping("/equipment")
  public List<Equipment> getAllEquipment() {
    return equipmentDao.getAllEquipment();
  }
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @GetMapping("/equipment/{id}")
  public Equipment getEquipment(int id) {
    return equipmentDao.getEquipmentById(id);
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @GetMapping("/members")
  public List<UserProfile> getMembers(Principal principal) {
    return userProfileDao.getMembers();
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @PostMapping("/members/{id}")
  public void checkMemberIn(@PathVariable int id) {
    checkinDao.checkin(id);
  }
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @PutMapping("/members/{id}")
  public void checkMemberOut(@PathVariable int id) {
    checkinDao.checkOut(id);
  }
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @GetMapping("/members/{id}")
  public boolean isMemberCheckedIn(@PathVariable int id) {
    return checkinDao.isCheckin(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/members/{id}/hire")
  public User hireMemberAsEmployee(@PathVariable int id) {
    return userDao.makeUserEmployee(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/members/{id}/fire")
  public void fireEmployee(@PathVariable int id) {
    userDao.fireEmployee(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/employees")
  public List<UserProfile> getEmployees() {
    return userProfileDao.getEmployees();
  }

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
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @PostMapping("/schedule")
  public void createSchedule(@RequestBody Schedule scheduleToCreate) {
    scheduleDao.createSchedule(scheduleToCreate);
  }
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @DeleteMapping("/schedule/{id}")
  public void deleteSchedule(@PathVariable int id) {
    scheduleDao.deleteSchedule(id);

  }
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @PutMapping("/schedule/{id}")
  public void updateSchedule(@RequestBody Schedule scheduleToUpdate, @PathVariable int id) {
    scheduleDao.updateSchedule(scheduleToUpdate, id);
  }
}
