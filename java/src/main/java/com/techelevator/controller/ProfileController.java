package com.techelevator.controller;

import com.techelevator.dao.CheckinDao;
import com.techelevator.dao.CreateExerciseDto;
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
import org.springframework.http.HttpStatus;

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

  /************************************* User Profile ******************************** */
  @GetMapping
  public UserProfile getUserProfile(Principal principal) {
    return userProfileDao.getProfile(getUserId(principal));
  }

  @PutMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Profile updated")
  public void updateProfile(Principal principal, @Valid @RequestBody UserProfile profileToUpdate) {
    userProfileDao.updateProfile(getUserId(principal), profileToUpdate);
  }

  @DeleteMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Profile deleted")
  public void deleteProfile(Principal principal) {
    userProfileDao.deleteProfile(getUserId(principal));
  }

  /************************************* Workouts ******************************** */
  @GetMapping("/workouts")
  public List<Workout> getWorkouts(Principal principal) {
    return workoutDao.getWorkouts(getUserId(principal));
  }

  @GetMapping("/workouts/current")
  public Workout getCurrentWorkout(Principal principal) {
    return workoutDao.getCurrentWorkout(getUserId(principal));
  }

  @PutMapping("/workouts/start")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Workout started")
  public void startWorkout(Principal principal) {
    workoutDao.startWorkout(getUserId(principal));
  }

  @PutMapping("/workouts/end")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Workout ended")
  public void endWorkout(Principal principal) {
    workoutDao.endWorkout(getUserId(principal));
  }

  /************************************* Exercises ******************************** */
  @GetMapping("/exercises")
  public List<Exercise> getExercises(Principal principal) {
    return exerciseDao.getExercises(getUserId(principal));
  }

  @PostMapping("/exercises")
  public Exercise createExercise(@RequestBody CreateExerciseDto dto, Principal principal){
    return exerciseDao.createExercise(dto, getUserId(principal));
  }

  /************************************* Checkins ******************************** */
  @GetMapping("/checkin")
  public boolean isCheckedIn(Principal principal) {
    return checkinDao.isCheckin(getUserId(principal));
  }

  @PostMapping("/checkin")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User checked in")
  public void checkUserIn(Principal principal) {
    checkinDao.checkin(getUserId(principal));
  }

  @PutMapping("/checkout")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User checked out")
  public void checkUserOut(Principal principal) {
    checkinDao.checkOut(getUserId(principal));
  }

  private int getUserId(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);
    return user.getId();
  }

}
