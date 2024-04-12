package com.techelevator.controller;

import com.techelevator.dao.CheckinDao;
import com.techelevator.dao.ExerciseDao;
import com.techelevator.dao.UserProfileDao;
import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.Exercise;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;
import com.techelevator.security.dao.UserDao;
import com.techelevator.security.model.User;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

  private UserDao userDao;
  private UserProfileDao userProfileDao;
  private WorkoutDao workoutDao;

  private CheckinDao checkinDao;
  private ExerciseDao exerciseDao;

  public ProfileController(
    UserDao userDao,
    UserProfileDao userProfileDao,
    WorkoutDao workoutDao,
    CheckinDao checkinDao,
    ExerciseDao exerciseDao
  ) {
    this.userDao = userDao;
    this.userProfileDao = userProfileDao;
    this.workoutDao = workoutDao;
    this.checkinDao = checkinDao;
    this.exerciseDao = exerciseDao;
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

    return workoutDao.getWorkouts(user.getId());
  }
  @PostMapping("/workouts")
  public Exercise addExercise(@RequestBody Exercise exercise){
    return  exerciseDao.createExercise(exercise);
  }

  @GetMapping("/workouts/current")
  public Workout getCurrentWorkout(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);
    int userId = user.getId();

    return workoutDao.getCurrentWorkout(userId);
  }

  @PutMapping("/workouts/start")
  public void startWorkout(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);
    int userId = user.getId();

    workoutDao.startWorkout(userId);
  }

  @PutMapping("/workouts/end")
  public void endWorkout(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);
    int userId = user.getId();

    workoutDao.endWorkout(userId);
  }

  @GetMapping("/checkin")
  public boolean isCheckedIn(Principal principal) {
    User user = userDao.getUserByUsername(principal.getName());
    int userId = user.getId();
    return checkinDao.isCheckin(userId);
  }

  @PostMapping("/checkin")
  public void checkUserIn(Principal principal) {
    User user = userDao.getUserByUsername(principal.getName());
    int userId = user.getId();

    checkinDao.checkin(userId);
  }

  @PutMapping("/checkout")
  public void checkUserOut(Principal principal) {
    User user = userDao.getUserByUsername(principal.getName());
    int userId = user.getId();
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
