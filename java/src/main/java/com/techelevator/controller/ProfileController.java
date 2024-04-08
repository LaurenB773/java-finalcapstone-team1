package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.JdbcUserProfileDao;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

  private JdbcUserProfileDao userProfileDao;

  public ProfileController(JdbcUserProfileDao userProfileDao) {
    this.userProfileDao = userProfileDao;
  }

  @GetMapping
  public UserProfile getUserProfile(int userId) {
    return userProfileDao.getProfile(userId);
  }

  @GetMapping("/workouts")
  public List<Workout> getWorkouts(int userId) {
    return userProfileDao.getWorkouts(userId);
  }
}
