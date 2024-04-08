package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Equipment;
import com.techelevator.model.UserProfile;

@RestController
@RequestMapping("/gym")
public class GymController {

  @GetMapping("/equipment")
  public List<Equipment> getEquipment() {
    return equipmentDao.getEquipment();
  }

  @GetMapping("/members")
  public List<UserProfile> getMembers(Principal principal) {
    // check role
    return userProfile.getMembers();
  }

  @GetMapping("/schedule")
  public List<Schedule> getSchedule() {
    return scheduleDao.getSchedule();
  }
}
