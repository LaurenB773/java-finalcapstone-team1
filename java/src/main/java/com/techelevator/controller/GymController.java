package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.ScheduleDao;
import com.techelevator.model.Schedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.UserProfileDao;
import com.techelevator.model.Equipment;
import com.techelevator.model.UserProfile;

@RestController
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

  @GetMapping("/members")
  public List<UserProfile> getMembers(Principal principal) {
    // check role
    return userProfileDao.getMembers();
  }

  @GetMapping("/schedule")
  public List<Schedule> getAllSchedule() {
    return scheduleDao.getAllSchedules();
  }
}
