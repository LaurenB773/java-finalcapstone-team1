package com.techelevator.controller;

import com.techelevator.dao.UserProfileDao;
import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;
import com.techelevator.security.dao.UserDao;
import com.techelevator.security.model.User;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

  private UserDao userDao;
  private UserProfileDao userProfileDao;
  private WorkoutDao workoutDao;

  public ProfileController(
    UserDao userDao,
    UserProfileDao userProfileDao,
    WorkoutDao workoutDao
  ) {
    this.userDao = userDao;
    this.userProfileDao = userProfileDao;
    this.workoutDao = workoutDao;
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

    return userProfileDao(user.getId());
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
  public void updateProfile(
    Principal principal,
    @Valid @RequestBody UserProfile profileToUpdate
  ) {
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
