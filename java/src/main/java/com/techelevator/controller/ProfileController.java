package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.JdbcUserProfileDao;
import com.techelevator.dao.JdbcWorkoutDao;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

  private JdbcUserProfileDao userProfileDao;
  private JdbcWorkoutDao workoutDao;

  public ProfileController(JdbcUserProfileDao userProfileDao, JdbcWorkoutDao workoutDao) {
    this.userProfileDao = userProfileDao;
    this.workoutDao = workoutDao;
  }

  @GetMapping
  public UserProfile getUserProfile(String username) {
    return userProfileDao.getProfile(username);
  }

  @GetMapping("/workouts")
  public List<Workout> getWorkouts(int userId) {
    return userProfileDao.getWorkouts(userId);
  }

  @PutMapping("/workouts/start")
  public void startWorkout(int userProfileId, int workoutId) {
    workoutDao.startWorkout(userProfileId, workoutId);
  }

  @PutMapping("/workouts/end")
  public void endWorkout(int userProfileId, int workoutId) {
    workoutDao.endWorkout(userProfileId, workoutId);
  }

  // userProfileDao.createProfile() is called in the AuthenticationController

  @PutMapping
  public void updateProfile(int userId, @RequestBody UserProfile profileToUpdate) {
    userProfileDao.updateProfile(userId, profileToUpdate);
  }

  @DeleteMapping
  public void deleteProfile(int userId) {
    userProfileDao.deleteProfile(userId);
  }

}
