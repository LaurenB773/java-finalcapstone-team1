package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.CheckinDao;
import com.techelevator.model.Checkin;
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

  public GymController(UserProfileDao userProfileDao, EquipmentDao equipmentDao, ScheduleDao scheduleDao, CheckinDao checkinDao) {
    this.userProfileDao = userProfileDao;
    this.equipmentDao = equipmentDao;
    this.scheduleDao = scheduleDao;
    this.checkinDao = checkinDao;
  }

  @GetMapping("/equipment")
  public List<Equipment> getAllEquipment() {
    return equipmentDao.getAllEquipment();
  }

  @GetMapping("/equipment/{id}")
  public Equipment getEquipment(int id) {
    return equipmentDao.getEquipmentById(id);
  }

  @GetMapping("/members")
  public List<UserProfile> getMembers(Principal principal) {
    return userProfileDao.getMembers();
  }

  @PostMapping("/members/{id}")
  public void checkMemberIn(@PathVariable int id) {
    checkinDao.checkin(id);
  }
  @PutMapping("/members/{id}")
  public void checkMemberOut(@PathVariable int id) {
    checkinDao.checkOut(id);
  }
  @GetMapping("/members/{id}")
  public UserProfile getMember(int id) {
    return userProfileDao.getProfile(id);
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
  public void createSchedule(@RequestBody Schedule scheduleToCreate) {
    scheduleDao.createSchedule(scheduleToCreate);
  }

  @DeleteMapping("/schedule/{id}")
  public void deleteSchedule(@PathVariable int id) {
    scheduleDao.deleteSchedule(id);

  }

  @PutMapping("/schedule/{id}")
  public void updateSchedule(@RequestBody Schedule scheduleToUpdate, @PathVariable int id) {
    scheduleDao.updateSchedule(scheduleToUpdate, id);
  }
}
