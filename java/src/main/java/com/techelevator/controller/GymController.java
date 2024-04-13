package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.CheckinDao;
import com.techelevator.security.dao.UserDao;
import com.techelevator.security.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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

  @GetMapping("/equipment")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.OK, reason = "Equipment retrieved")
  public List<Equipment> getAllEquipment() {
    return equipmentDao.getAllEquipment();
  }

  @GetMapping("/equipment/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.OK, reason = "Equipment by id retrieved")
  public Equipment getEquipment(int id) {
    return equipmentDao.getEquipmentById(id);
  }

  @GetMapping("/members")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.OK, reason = "Members retrieved")
  public List<UserProfile> getMembers(Principal principal) {
    return userProfileDao.getMembers();
  }

  @PostMapping("/members/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Member checked in")
  public void checkMemberIn(@PathVariable int id) {
    checkinDao.checkin(id);
  }

  @PutMapping("/members/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Member checked out")
  public void checkMemberOut(@PathVariable int id) {
    checkinDao.checkOut(id);
  }

  @GetMapping("/members/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.OK, reason = "Member checked in")
  public boolean isMemberCheckedIn(@PathVariable int id) {
    return checkinDao.isCheckin(id);
  }

  @PutMapping("/members/{id}/hire")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.OK, reason = "Member hired as employee")
  public User hireMemberAsEmployee(@PathVariable int id) {
    return userDao.makeUserEmployee(id);
  }

  @PutMapping("/members/{id}/fire")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Employee fired")
  public void fireEmployee(@PathVariable int id) {
    userDao.fireEmployee(id);
  }

  @GetMapping("/employees")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserProfile> getEmployees() {
    return userProfileDao.getEmployees();
  }

  @PutMapping("/members/{id}/ban")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Member banned")
  public void banMember(@PathVariable int id) {
    userDao.banMember(id);
  }

  @GetMapping("/schedule")
  @ResponseStatus(value = HttpStatus.OK, reason = "Schedules retrieved")
  public List<Schedule> getAllSchedules() {
    return scheduleDao.getAllSchedules();
  }

  @GetMapping("/schedule/{id}")
  @ResponseStatus(value = HttpStatus.OK, reason = "Schedule by id retrieved")
  public Schedule getSchedule(int id) {
    return scheduleDao.getSchedule(id);
  }

  @PostMapping("/schedule")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.CREATED, reason = "Schedule created")
  public void createSchedule(@RequestBody Schedule scheduleToCreate) {
    scheduleDao.createSchedule(scheduleToCreate);
  }

  @PutMapping("/schedule/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Schedule updated")
  public void updateSchedule(@RequestBody Schedule scheduleToUpdate, @PathVariable int id) {
    scheduleDao.updateSchedule(scheduleToUpdate, id);
  }

  @DeleteMapping("/schedule/{id}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Schedule deleted")
  public void deleteSchedule(@PathVariable int id) {
    scheduleDao.deleteSchedule(id);
  }
}
