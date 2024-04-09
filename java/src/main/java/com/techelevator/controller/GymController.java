package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  public GymController(UserProfileDao userProfileDao, EquipmentDao equipmentDao, ScheduleDao scheduleDao) {
    this.userProfileDao = userProfileDao;
    this.equipmentDao = equipmentDao;
    this.scheduleDao = scheduleDao;
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

  @GetMapping("/members/{id}")
  public UserProfile getMember(int id) {
    return userProfileDao.getProfileById(id);
  }

  @GetMapping("/schedule")
  public List<Schedule> getAllSchedules() {
    return scheduleDao.getAllSchedules();
  }

  @GetMapping("/schedule/{id}")
  public Schedule getSchedule(int id) {
    return scheduleDao.getSchedule(id);
  }
}
