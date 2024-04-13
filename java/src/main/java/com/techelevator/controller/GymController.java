package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.CheckinDao;
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

  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @GetMapping("/members/{id}")
  public UserProfile getMember(int id) {
    return userProfileDao.getProfile(id);
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
  @PutMapping("/members/{id}/ban")
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
  public void deleteSchedule(@PathVariable int id) {
    scheduleDao.deleteSchedule(id);

  }



}
