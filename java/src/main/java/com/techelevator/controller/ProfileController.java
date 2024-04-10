package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import java.security.Principal;

import com.techelevator.dao.CheckinDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.UserProfileDao;
import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;
import com.techelevator.security.dao.UserDao;
import com.techelevator.security.model.User;

@RestController
@CrossOrigin
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

  private UserDao userDao;
  private UserProfileDao userProfileDao;
  private WorkoutDao workoutDao;

  private CheckinDao checkinDao;

  public ProfileController(UserDao userDao, UserProfileDao userProfileDao, WorkoutDao workoutDao, CheckinDao checkinDao) {
    this.userDao = userDao;
    this.userProfileDao = userProfileDao;
    this.workoutDao = workoutDao;
    this.checkinDao = checkinDao;
  }

  @GetMapping
  public UserProfile getUserProfile(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);

    return userProfileDao.getProfile(user.getId());
  }

  @GetMapping("/workouts")
  public List<Workout> getWorkouts(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);

    return userProfileDao.getWorkouts(user.getId());
  }

  @PutMapping("/workouts/start")
  public void startWorkout(int userProfileId, int workoutId) {
    workoutDao.startWorkout(userProfileId, workoutId);
  }

  @PutMapping("/workouts/end")
  public void endWorkout(int userProfileId, int workoutId) {
    workoutDao.endWorkout(userProfileId, workoutId);
  }
  // request body for these because it is not in path
  @PostMapping("/checkin")
  public void checkUserIn(@RequestBody int userId) {
    checkinDao.checkin(userId);
  }
  @PutMapping("/checkout")
  public void checkUserOut(@RequestBody int userId) {
    checkinDao.checkOut(userId);
  }

  // userProfileDao.createProfile() is called in the AuthenticationController

  @PutMapping
  public void updateProfile(Principal principal, @Valid @RequestBody UserProfile profileToUpdate) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);

    userProfileDao.updateProfile(user.getId(), profileToUpdate);
  }

  @DeleteMapping
  public void deleteProfile(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);

    userProfileDao.deleteProfile(user.getId());
  }



}
